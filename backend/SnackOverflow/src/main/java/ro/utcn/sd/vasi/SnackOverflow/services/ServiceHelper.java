package ro.utcn.sd.vasi.SnackOverflow.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.utcn.sd.vasi.SnackOverflow.dto.AnswerDTO;
import ro.utcn.sd.vasi.SnackOverflow.dto.QuestionDTO;
import ro.utcn.sd.vasi.SnackOverflow.model.Answer;
import ro.utcn.sd.vasi.SnackOverflow.model.Question;

@RequiredArgsConstructor
@Component
public class ServiceHelper {
    private final UserManagementService userManagementService;

    public AnswerDTO getAnswerDTO(Answer answer) {
        return AnswerDTO.ofEntity(answer, userManagementService.getUserDTO(answer.getAuthorId()));
    }

    public QuestionDTO getQuestionDTO(Question question) {
        return QuestionDTO.ofEntity(question, userManagementService.getUserDTO(question.getAuthorId()));
    }
}
