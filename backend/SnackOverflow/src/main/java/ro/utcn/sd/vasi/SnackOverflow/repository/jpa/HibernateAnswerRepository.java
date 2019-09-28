package ro.utcn.sd.vasi.SnackOverflow.repository.jpa;

import ro.utcn.sd.vasi.SnackOverflow.model.Answer;
import ro.utcn.sd.vasi.SnackOverflow.repository.api.AnswerRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class HibernateAnswerRepository extends HibernateGeneralRepository<Answer> implements AnswerRepository {
    public HibernateAnswerRepository(EntityManager entityManager) {
        super(entityManager, Answer.class);
    }

    @Override
    public List<Answer> findAllbyQuestionId(int questionId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Answer> query = builder.createQuery(Answer.class);
        Root<Answer> answerRoot = query.from(Answer.class);
        return entityManager.createQuery(query.select(answerRoot).where(builder.equal(answerRoot.get("questionId"), questionId))).getResultList();
    }
}
