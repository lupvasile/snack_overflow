package ro.utcn.sd.vasi.SnackOverflow.repository.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import ro.utcn.sd.vasi.SnackOverflow.model.User;
import ro.utcn.sd.vasi.SnackOverflow.repository.api.UserRepository;

import java.util.List;
import java.util.Optional;


public class JdbcUserRepository extends JdbcGeneralRepository<User> implements UserRepository {

    public JdbcUserRepository(JdbcTemplate template) {
        super(template, User.class, new UserMapper());
    }

    @Override
    public Optional<User> findByUsername(String username) {
        List<User> users = template.query("SELECT * FROM " + tableName + " WHERE username = ?", rowMapper, username);
        if (users.isEmpty()) return Optional.empty();
        return Optional.of(users.get(0));
    }
}
