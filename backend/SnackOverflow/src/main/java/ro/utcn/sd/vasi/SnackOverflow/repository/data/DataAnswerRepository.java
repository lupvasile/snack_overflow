package ro.utcn.sd.vasi.SnackOverflow.repository.data;

import org.springframework.data.repository.Repository;
import ro.utcn.sd.vasi.SnackOverflow.model.Answer;
import ro.utcn.sd.vasi.SnackOverflow.repository.api.AnswerRepository;

import java.util.List;

public interface DataAnswerRepository extends Repository<Answer, Integer>, AnswerRepository {
    void delete(Answer answer);

    @Override
    default void remove(Answer answer) {
        delete(answer);
    }

    List<Answer> findAllByQuestionId(Integer questionId);

    @Override
    default List<Answer> findAllbyQuestionId(int questionId) {
        return findAllByQuestionId(questionId);
    }
}
