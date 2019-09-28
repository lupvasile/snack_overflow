package ro.utcn.sd.vasi.SnackOverflow.repository.jpa;

import lombok.RequiredArgsConstructor;
import ro.utcn.sd.vasi.SnackOverflow.model.HasIntId;
import ro.utcn.sd.vasi.SnackOverflow.repository.api.GeneralRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class HibernateGeneralRepository<T extends HasIntId> implements GeneralRepository<T> {
    protected final EntityManager entityManager;
    protected final Class<T> currClass;

    @Override
    public List<T> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(currClass);

        query.select(query.from(currClass));
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public T save(T element) {
        if (element.getId() == null) {
            entityManager.persist(element);
            return element;
        } else {
            return entityManager.merge(element);
        }
    }

    @Override
    public void remove(T element) {
        entityManager.remove(element);
    }

    @Override
    public Optional<T> findById(int id) {
        return Optional.ofNullable(entityManager.find(currClass, id));
    }
}
