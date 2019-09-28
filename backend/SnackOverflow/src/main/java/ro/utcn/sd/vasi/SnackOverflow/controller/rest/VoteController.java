package ro.utcn.sd.vasi.SnackOverflow.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.utcn.sd.vasi.SnackOverflow.services.AnswerManagementService;
import ro.utcn.sd.vasi.SnackOverflow.services.QuestionManagementService;
import ro.utcn.sd.vasi.SnackOverflow.services.UserManagementService;

@RestController
@RequiredArgsConstructor
public class VoteController {
    private final AnswerManagementService answerManagementService;
    private final QuestionManagementService questionManagementService;
    private final UserManagementService userManagementService;

    //is put the appropiate method here?
    @PostMapping("/questions/{questionId}/answers/{answerId}/votes/{voteValue}")
    public boolean voteAnswer(@PathVariable int answerId, @PathVariable int voteValue) {
        boolean send = voteValue == 1;
        return answerManagementService.sendVote(userManagementService.loadCurrentUser().getId(), answerId, send);
    }

    @PostMapping("/questions/{questionId}/votes/{voteValue}")
    private void voteQuestion(@PathVariable int questionId, @PathVariable int voteValue) {
        boolean send = voteValue == 1;
        questionManagementService.sendVote(userManagementService.loadCurrentUser().getId(), questionId, send);
    }
}
