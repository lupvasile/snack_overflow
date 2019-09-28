package ro.utcn.sd.vasi.SnackOverflow.repository.jpa;

import ro.utcn.sd.vasi.SnackOverflow.model.Tag;
import ro.utcn.sd.vasi.SnackOverflow.repository.api.TagRepository;

import javax.persistence.EntityManager;

public class HibernateTagRepository extends HibernateGeneralRepository<Tag> implements TagRepository {
    public HibernateTagRepository(EntityManager entityManager) {
        super(entityManager, Tag.class);
    }
}
