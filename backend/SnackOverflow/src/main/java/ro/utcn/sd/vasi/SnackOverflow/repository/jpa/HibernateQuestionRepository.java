package ro.utcn.sd.vasi.SnackOverflow.repository.jpa;

import ro.utcn.sd.vasi.SnackOverflow.model.Question;
import ro.utcn.sd.vasi.SnackOverflow.repository.api.QuestionRepository;

import javax.persistence.EntityManager;

public class HibernateQuestionRepository extends HibernateGeneralRepository<Question> implements QuestionRepository {
    public HibernateQuestionRepository(EntityManager entityManager) {
        super(entityManager, Question.class);
    }
}
