package ro.utcn.sd.vasi.SnackOverflow;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.sd.vasi.SnackOverflow.model.Answer;
import ro.utcn.sd.vasi.SnackOverflow.repository.api.RepositoryFactory;
import ro.utcn.sd.vasi.SnackOverflow.repository.jpa.HibernateAnswerRepository;
import ro.utcn.sd.vasi.SnackOverflow.repository.jpa.HibernateQuestionRepository;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
public class tests implements CommandLineRunner {
    private final EntityManager entityManager;
    private final RepositoryFactory repositoryFactory;

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        //functie();

        /*Answer ans = new Answer(2,"asdf",ZonedDateTime.now(),5,0);
        Map<String,Object> ff = getElementMap(ans,Answer.class);
        System.out.println(ff);
        System.out.println(camelCaseToUnderscore("anaAreMere"));
        while(true) {
            Thread.sleep(200);
        }*/
    }

    Map<String, Object> getElementMap(Answer element, Class<?> currClass) {
        Map<String, Object> map = new HashMap<>();
        Method[] methods = currClass.getMethods();
        for (Method m : methods) {
            String methodName = m.getName();
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

                map.put(fieldName, m.invoke(element));
            } catch (NoSuchFieldException e) {
                System.out.println("field " + fieldName + "is not present on object");
            } catch (IllegalAccessException | InvocationTargetException e) {
                System.out.println("getter for " + fieldName + " is not accessible");
            }
        }

        return map;
    }

    private String camelCaseToUnderscore(String name) {
        String regex = "([a-z])([A-Z]+)";
        String replacement = "$1_$2";
        return name.replaceAll(regex, replacement).toLowerCase();
    }

    @Transactional
    void functie() {
        HibernateAnswerRepository hibernateAnswerRepository = new HibernateAnswerRepository(entityManager);
        //System.out.println(hibernateAnswerRepository.findAllbyQuestionId(1));

        HibernateQuestionRepository questionRepository = new HibernateQuestionRepository(entityManager);
        //questionRepository.save(new Question(1,"added q3","textas",ZonedDateTime.now(),new TreeSet<Tag>(Arrays.asList(new Tag(12,"tag1"))),0));
        //System.out.println(questionRepository.findAll());

        int id = 1;
        repositoryFactory.createQuestionVoteRepository().findAllVotesTowardsUserId(1);
        repositoryFactory.createAnswerVoteRepository().findAllVotesTowardsUserId(1);
        repositoryFactory.createQuestionVoteRepository().findAllVotesFromUserId(1);
        repositoryFactory.createAnswerVoteRepository().findAllVotesFromUserId(1);
    }
}
