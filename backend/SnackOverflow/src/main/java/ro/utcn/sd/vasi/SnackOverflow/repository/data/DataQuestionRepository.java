package ro.utcn.sd.vasi.SnackOverflow.repository.data;


import org.springframework.data.repository.Repository;
import ro.utcn.sd.vasi.SnackOverflow.model.Question;
import ro.utcn.sd.vasi.SnackOverflow.repository.api.QuestionRepository;

public interface DataQuestionRepository extends Repository<Question, Integer>, QuestionRepository {
    void delete(Question element);

    @Override
    default void remove(Question element) {
        delete(element);
    }
}
