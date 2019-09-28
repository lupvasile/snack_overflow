package ro.utcn.sd.vasi.SnackOverflow.repository.api;

import ro.utcn.sd.vasi.SnackOverflow.model.Answer;

import java.util.List;

public interface AnswerRepository extends GeneralRepository<Answer> {
    List<Answer> findAllbyQuestionId(int questionId);
}
