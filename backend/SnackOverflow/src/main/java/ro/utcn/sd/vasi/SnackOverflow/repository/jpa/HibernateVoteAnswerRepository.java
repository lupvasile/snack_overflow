package ro.utcn.sd.vasi.SnackOverflow.repository.jpa;

import ro.utcn.sd.vasi.SnackOverflow.model.VoteAnswer;
import ro.utcn.sd.vasi.SnackOverflow.repository.api.VoteAnswerRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

public class HibernateVoteAnswerRepository extends HibernateGeneralRepository<VoteAnswer> implements VoteAnswerRepository {
    public HibernateVoteAnswerRepository(EntityManager entityManager) {
        super(entityManager, VoteAnswer.class);
    }

    @Override
    public List<VoteAnswer> findAllVotesWithPostId(int id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<VoteAnswer> query = builder.createQuery(currClass);
        Root<VoteAnswer> answerRoot = query.from(currClass);
        return entityManager.createQuery(query.select(answerRoot).where(builder.equal(answerRoot.get("postId"), id))).getResultList();
    }

    /**
     * Find all votes which a user has voted.
     *
     * @param id the id of the user
     * @return
     */
    @Override
    public List<VoteAnswer> findAllVotesFromUserId(int id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<VoteAnswer> query = builder.createQuery(currClass);
        Root<VoteAnswer> answerRoot = query.from(currClass);
        return entityManager.createQuery(query.select(answerRoot).where(builder.equal(answerRoot.get("cineDaId"), id))).getResultList();
    }

    @Override
    public List<VoteAnswer> findAllVotesTowardsUserId(int userId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<VoteAnswer> query = builder.createQuery(currClass);
        Root<VoteAnswer> answerRoot = query.from(currClass);
        List<VoteAnswer> res = entityManager.createQuery(query.select(answerRoot).where(builder.equal(answerRoot.get("cinePrimesteId"), userId))).getResultList();
        return res;
    }

    @Override
    public Optional<VoteAnswer> findVoteFromUserForPost(int userId, int postId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<VoteAnswer> query = builder.createQuery(currClass);
        Root<VoteAnswer> answerRoot = query.from(currClass);
        List<VoteAnswer> res = entityManager.createQuery(query.select(answerRoot)
                .where(builder.equal(answerRoot.get("cineDaId"), userId))
                .where(builder.equal(answerRoot.get("postId"), postId))).getResultList();
        if (res.isEmpty()) return Optional.empty();
        else return Optional.of(res.get(0));
    }
}
