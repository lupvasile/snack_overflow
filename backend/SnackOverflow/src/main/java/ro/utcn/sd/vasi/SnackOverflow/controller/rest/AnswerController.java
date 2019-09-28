package ro.utcn.sd.vasi.SnackOverflow.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.utcn.sd.vasi.SnackOverflow.dto.AnswerDTO;
import ro.utcn.sd.vasi.SnackOverflow.services.AnswerManagementService;
import ro.utcn.sd.vasi.SnackOverflow.services.UserManagementService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerManagementService answerManagementService;
    private final UserManagementService userManagementService;

    @PostMapping("/questions/{questionId}/answers")
    public AnswerDTO addAnswer(@PathVariable int questionId, @RequestBody AnswerDTO answer) {
        return answerManagementService.addAnswer(userManagementService.loadCurrentUser().getId(), questionId, answer.getText());
    }

    @DeleteMapping("/questions/{questionId}/answers/{answerId}")
    public void deleteAnswer(@PathVariable int answerId) {
        answerManagementService.deleteAnswer(userManagementService.loadCurrentUser().getId(), answerId);
    }

    //shoud this return AnswerDTO or should be void?
    @PutMapping("questions/{questionId}/answers/{answerId}")
    public AnswerDTO updateAnswer(@PathVariable int answerId, @RequestBody AnswerDTO answer) {
        return answerManagementService.updateAnswerText(userManagementService.loadCurrentUser().getId(), answerId, answer.getText());
    }

    @GetMapping("/questions/{questionId}/answers")
    public List<AnswerDTO> seeQuestionAnswers(@PathVariable int questionId) {
        return answerManagementService.listAnswersForQuestion(questionId);
    }
}
