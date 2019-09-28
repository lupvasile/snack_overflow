package ro.utcn.sd.vasi.SnackOverflow.repository.jpa;

import ro.utcn.sd.vasi.SnackOverflow.model.User;
import ro.utcn.sd.vasi.SnackOverflow.repository.api.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

public class HibernateUserRepository extends HibernateGeneralRepository<User> implements UserRepository {
    public HibernateUserRepository(EntityManager entityManager) {
        super(entityManager, User.class);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> userRoot = query.from(User.class);
        List<User> users = entityManager.createQuery(query.select(userRoot).where(builder.equal(userRoot.get("username"), username))).getResultList();
        if (users.isEmpty()) return Optional.empty();
        return Optional.of(users.get(0));
    }
}
