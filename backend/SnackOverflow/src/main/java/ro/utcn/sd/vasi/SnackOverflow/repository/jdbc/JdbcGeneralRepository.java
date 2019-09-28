package ro.utcn.sd.vasi.SnackOverflow.repository.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ro.utcn.sd.vasi.SnackOverflow.model.HasIntId;
import ro.utcn.sd.vasi.SnackOverflow.repository.api.GeneralRepository;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

//is it wrong to use same rowmapper?
public class JdbcGeneralRepository<T extends HasIntId> implements GeneralRepository<T> {
    protected final JdbcTemplate template;
    protected final RowMapper<T> rowMapper;
    private final Class<T> currClass;
    protected String tableName;

    public JdbcGeneralRepository(JdbcTemplate template, Class<T> currClass, RowMapper<T> rowMapper) {
        this.template = template;
        this.currClass = currClass;
        this.rowMapper = rowMapper;

        if (currClass.isAnnotationPresent(Table.class)) {
            tableName = currClass.getAnnotation(Table.class).name();
        } else {
            tableName = camelCaseToUnderscore(currClass.getSimpleName());
        }
    }

    @Override
    public List<T> findAll() {
        List<T> list = template.query("SELECT * FROM " + tableName, rowMapper);
        return list;
    }

    @Override
    public T save(T element) {
        if (element.getId() == null) {
            element.setId(insert(element));
        } else {
            update(element);
        }

        return element;
    }

    @Override
    public void remove(T element) {
        template.update("DELETE FROM " + tableName + " WHERE id = ?", element.getId());
    }

    @Override
    public Optional<T> findById(int id) {
        List<T> elements = template.query("SELECT * FROM " + tableName + " WHERE id = ?", rowMapper, id);
        return elements.isEmpty() ? Optional.empty() : Optional.of(elements.get(0));
    }

    protected int insert(T element) {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(template);
        insert.setTableName(tableName);
        insert.usingGeneratedKeyColumns("id");
        Map<String, Object> map = getElementMap(element);
        map.remove("id");
        return insert.executeAndReturnKey(map).intValue();
    }

    protected void update(T element) {
        Map<String, Object> map = getElementMap(element);
        int id = (Integer) map.get("id");
        map.remove("id");

        Object[] statementArgs = new Object[map.size()];

        StringBuilder statementBuild = new StringBuilder();
        statementBuild.append("UPDATE " + tableName + " SET ");

        int i = 0;
        for (Map.Entry<String, Object> e : map.entrySet()) {
            statementBuild.append(e.getKey() + " = ?,");
            statementArgs[i++] = e.getValue();
        }

        statementBuild.deleteCharAt(statementBuild.length() - 1);
        statementBuild.append(" WHERE id = " + id);

        template.update(statementBuild.toString(), statementArgs);
    }

    private String camelCaseToUnderscore(String name) {
        String regex = "([a-z])([A-Z]+)";
        String replacement = "$1_$2";
        return name.replaceAll(regex, replacement).toLowerCase();
    }

    Map<String, Object> getElementMap(T element) {
        Map<String, Object> map = new HashMap<>();
        Method[] methods = currClass.getMethods();
        for (Method m : methods) {
            String methodName = m.getName();
            if (methodName.equals("getClass")) continue;
            if (!(methodName.startsWith("is") || methodName.startsWith("get"))) continue;

            String fieldName;
            if (methodName.startsWith("get")) fieldName = methodName.replaceFirst("get", "");
            else fieldName = methodName.replaceFirst("is", "");
            fieldName = fieldName.substring(0, 1).toLowerCase() + fieldName.substring(1);

            try {
                Field field = currClass.getDeclaredField(fieldName);

                if (field.isAnnotationPresent(Transient.class) || field.isAnnotationPresent(OneToMany.class) || field.isAnnotationPresent(ManyToOne.class) ||
                        field.isAnnotationPresent(ManyToMany.class)) continue;

                if (field.isAnnotationPresent(Column.class)) {
                    fieldName = field.getAnnotation(Column.class).name();
                } else {
                    fieldName = camelCaseToUnderscore(fieldName);
                }

                if (field.getType().equals(ZonedDateTime.class)) map.put(fieldName, m.invoke(element).toString());
                else map.put(fieldName, m.invoke(element));

            } catch (NoSuchFieldException e) {
                System.out.println("field " + fieldName + " is not present on object");
            } catch (IllegalAccessException | InvocationTargetException e) {
                System.out.println("getter for " + fieldName + " is not accessible");
            }
        }

        return map;
    }
}
