package ro.utcn.sd.vasi.SnackOverflow.repository.jpa;

import ro.utcn.sd.vasi.SnackOverflow.model.VoteQuestion;
import ro.utcn.sd.vasi.SnackOverflow.repository.api.VoteQuestionRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HibernateVoteQuestionRepository extends HibernateGeneralRepository<VoteQuestion> implements VoteQuestionRepository {
    public HibernateVoteQuestionRepository(EntityManager entityManager) {
        super(entityManager, VoteQuestion.class);
    }

    @Override
    public List<VoteQuestion> findAllVotesWithPostId(int id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<VoteQuestion> query = builder.createQuery(currClass);
        Root<VoteQuestion> answerRoot = query.from(currClass);
        return entityManager.createQuery(query.select(answerRoot).where(builder.equal(answerRoot.get("postId"), id))).getResultList();
    }

    /**
     * Find all votes which a user has voted.
     *
     * @param id the id of the user
     * @return
     */
    @Override
    public List<VoteQuestion> findAllVotesFromUserId(int id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<VoteQuestion> query = builder.createQuery(currClass);
        Root<VoteQuestion> answerRoot = query.from(currClass);
        return entityManager.createQuery(query.select(answerRoot).where(builder.equal(answerRoot.get("cineDaId"), id))).getResultList();
    }

    @Override
    public List<VoteQuestion> findAllVotesTowardsUserId(int userId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<VoteQuestion> query = builder.createQuery(currClass);
        Root<VoteQuestion> answerRoot = query.from(currClass);
        List<VoteQuestion> res = null;
        try {
            res = entityManager.createQuery(query.select(answerRoot).where(builder.equal(answerRoot.get("cinePrimesteId"), userId))).getResultList();
        } catch (Exception e) {
            int xxxx = 33;
        }
        return res == null ? new ArrayList<>() : res;
    }

    @Override
    public Optional<VoteQuestion> findVoteFromUserForPost(int userId, int postId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<VoteQuestion> query = builder.createQuery(currClass);
        Root<VoteQuestion> answerRoot = query.from(currClass);
        List<VoteQuestion> res = entityManager.createQuery(query.select(answerRoot)
                .where(builder.equal(answerRoot.get("cineDaId"), userId))
                .where(builder.equal(answerRoot.get("postId"), postId))).getResultList();
        if (res.isEmpty()) return Optional.empty();
        else return Optional.of(res.get(0));
    }
}
