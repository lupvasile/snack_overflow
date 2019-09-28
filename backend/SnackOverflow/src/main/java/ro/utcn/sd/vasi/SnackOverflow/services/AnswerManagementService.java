package ro.utcn.sd.vasi.SnackOverflow.services;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.sd.vasi.SnackOverflow.dto.AnswerDTO;
import ro.utcn.sd.vasi.SnackOverflow.dto.UserDTO;
import ro.utcn.sd.vasi.SnackOverflow.event.*;
import ro.utcn.sd.vasi.SnackOverflow.exceptions.AnswerNotFoundException;
import ro.utcn.sd.vasi.SnackOverflow.exceptions.NotEnoughPermissionsException;
import ro.utcn.sd.vasi.SnackOverflow.exceptions.QuestionNotFoundException;
import ro.utcn.sd.vasi.SnackOverflow.exceptions.UserNotFoundException;
import ro.utcn.sd.vasi.SnackOverflow.model.Answer;
import ro.utcn.sd.vasi.SnackOverflow.model.Question;
import ro.utcn.sd.vasi.SnackOverflow.model.User;
import ro.utcn.sd.vasi.SnackOverflow.model.VoteAnswer;
import ro.utcn.sd.vasi.SnackOverflow.repository.api.RepositoryFactory;

import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service(value = "AnswerManagementServiceOld")
@RequiredArgsConstructor
public class AnswerManagementService {
    private final RepositoryFactory repositoryFactory;
    private final ApplicationEventPublisher eventPublisher;
    private final ServiceHelper serviceHelper;

    @Transactional
    public List<AnswerDTO> listAnswersForQuestion(int questionId) {
        Question question = repositoryFactory.createQuestionRepository().findById(questionId).orElseThrow(QuestionNotFoundException::new);

        return repositoryFactory.createAnswerRepository().findAllbyQuestionId(questionId).stream()
                .sorted(Comparator.comparing(Answer::getVoteCount).reversed())
                .map(serviceHelper::getAnswerDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public boolean sendVote(int userId, int answerId, boolean value) {
        VoteAnswer currVote = repositoryFactory.createAnswerVoteRepository().findVoteFromUserForPost(userId, answerId).orElse(null);
        Answer answer = repositoryFactory.createAnswerRepository().findById(answerId).orElseThrow(AnswerNotFoundException::new);

        if (currVote != null && currVote.isValue() == value) return false;
        if (userId == answer.getAuthorId()) return false;

        if (currVote == null) currVote = new VoteAnswer(null, userId, answer.getAuthorId(), answerId, value);
        currVote.setValue(value);

        repositoryFactory.createAnswerVoteRepository().save(currVote);

        eventPublisher.publishEvent(new VotedAnswerEvent(serviceHelper.getAnswerDTO(
                repositoryFactory.createAnswerRepository().findById(answer.getId()).orElseThrow(AnswerNotFoundException::new))));
        eventPublisher.publishEvent(new UserUpdatedEvent(UserDTO.ofEntity(repositoryFactory.createUserRepository().findById(userId).orElseThrow(UserNotFoundException::new))));
        eventPublisher.publishEvent(new UserUpdatedEvent(UserDTO.ofEntity(repositoryFactory.createUserRepository().findById(answer.getAuthorId()).orElseThrow(UserNotFoundException::new))));
        return true;
    }

    @Transactional
    public AnswerDTO addAnswer(int userId, int questionId, String text) {
        User user = repositoryFactory.createUserRepository().findById(userId).orElseThrow(UserNotFoundException::new);
        if (user.getIsBlocked()) return null;

        Question question = repositoryFactory.createQuestionRepository().findById(questionId).orElseThrow(QuestionNotFoundException::new);

        AnswerDTO answerDTO = serviceHelper.getAnswerDTO(
                repositoryFactory.createAnswerRepository().save(new Answer(userId, text, ZonedDateTime.now(), questionId, 0)));

        eventPublisher.publishEvent(new AnswerCreatedEvent(answerDTO));
        return answerDTO;
    }

    @Transactional
    public AnswerDTO updateAnswerText(int userId, int answerId, String newText) {
        User user = repositoryFactory.createUserRepository().findById(userId).orElseThrow(UserNotFoundException::new);
        Answer answer = repositoryFactory.createAnswerRepository().findById(answerId).orElseThrow(AnswerNotFoundException::new);

        if (!answer.getAuthorId().equals(user.getId()) && !user.getIsModerator())
            throw new NotEnoughPermissionsException();

        answer.setText(newText);
        AnswerDTO answerDTO = serviceHelper.getAnswerDTO(
                repositoryFactory.createAnswerRepository().save(answer));

        eventPublisher.publishEvent(new AnswerUpdatedEvent(answerDTO));
        return answerDTO;
    }

    @Transactional
    public void deleteAnswer(int userId, int answerId) {
        User user = repositoryFactory.createUserRepository().findById(userId).orElseThrow(UserNotFoundException::new);
        Answer answer = repositoryFactory.createAnswerRepository().findById(answerId).orElseThrow(AnswerNotFoundException::new);

        if (!answer.getAuthorId().equals(user.getId()) && !user.getIsModerator())
            throw new NotEnoughPermissionsException();

        repositoryFactory.createAnswerRepository().remove(answer);
        eventPublisher.publishEvent(new AnswerDeletedEvent(answerId));
        repositoryFactory.createAnswerVoteRepository().findAllVotesWithPostId(answerId)
                .forEach(v -> eventPublisher.publishEvent(new UserUpdatedEvent(UserDTO.ofEntity(
                        repositoryFactory.createUserRepository().findById(v.getCineDaId()).orElseThrow(UserNotFoundException::new)))));
        eventPublisher.publishEvent(new UserUpdatedEvent(UserDTO.ofEntity(repositoryFactory.createUserRepository().findById(answer.getAuthorId()).orElseThrow(UserNotFoundException::new))));

    }
}
