package ro.utcn.sd.vasi.SnackOverflow.repository.data;

import org.springframework.data.repository.Repository;
import ro.utcn.sd.vasi.SnackOverflow.model.VoteAnswer;
import ro.utcn.sd.vasi.SnackOverflow.repository.api.VoteAnswerRepository;

import java.util.List;
import java.util.Optional;

public interface DataVoteAnswerRepository extends Repository<VoteAnswer, Integer>, VoteAnswerRepository {
    void delete(VoteAnswer element);

    @Override
    default void remove(VoteAnswer element) {
        delete(element);
    }

    List<VoteAnswer> findAllByPostId(Integer postId);

    @Override
    default List<VoteAnswer> findAllVotesWithPostId(int id) {
        return findAllByPostId(id);
    }

    List<VoteAnswer> findAllByCineDaId(Integer id);

    @Override
    default List<VoteAnswer> findAllVotesFromUserId(int id) {
        return findAllByCineDaId(id);
    }

    List<VoteAnswer> findAllByCinePrimesteId(Integer id);

    @Override
    default List<VoteAnswer> findAllVotesTowardsUserId(int userId) {
        return findAllByCinePrimesteId(userId);
    }

    Optional<VoteAnswer> findFirstByCineDaIdAndCinePrimesteId(Integer cineDaId, Integer cinePrimesteId);

    @Override
    default Optional<VoteAnswer> findVoteFromUserForPost(int userId, int postId) {
        return findFirstByCineDaIdAndCinePrimesteId(userId, postId);
    }
}
