package ro.utcn.sd.vasi.SnackOverflow.services;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.sd.vasi.SnackOverflow.dto.QuestionDTO;
import ro.utcn.sd.vasi.SnackOverflow.dto.TagDTO;
import ro.utcn.sd.vasi.SnackOverflow.dto.UserDTO;
import ro.utcn.sd.vasi.SnackOverflow.event.*;
import ro.utcn.sd.vasi.SnackOverflow.exceptions.NotEnoughPermissionsException;
import ro.utcn.sd.vasi.SnackOverflow.exceptions.NotEnoughTagsException;
import ro.utcn.sd.vasi.SnackOverflow.exceptions.QuestionNotFoundException;
import ro.utcn.sd.vasi.SnackOverflow.exceptions.UserNotFoundException;
import ro.utcn.sd.vasi.SnackOverflow.model.Question;
import ro.utcn.sd.vasi.SnackOverflow.model.Tag;
import ro.utcn.sd.vasi.SnackOverflow.model.User;
import ro.utcn.sd.vasi.SnackOverflow.model.VoteQuestion;
import ro.utcn.sd.vasi.SnackOverflow.repository.api.RepositoryFactory;

import javax.persistence.QueryTimeoutException;
import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service(value = "QuestionManagementServiceOld")
@RequiredArgsConstructor
public class QuestionManagementService {
    private final RepositoryFactory repositoryFactory;
    private final ApplicationEventPublisher eventPublisher;
    private final ServiceHelper serviceHelper;

    @Transactional
    public List<QuestionDTO> listQuestions() {
        return repositoryFactory.createQuestionRepository().findAll().stream().sorted(Comparator.comparing(Question::getCreationDateTime).reversed())
                .map(serviceHelper::getQuestionDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<QuestionDTO> filterQuestionsByTag(Set<Tag> tags) {
        return repositoryFactory.createQuestionRepository().findAll().stream()
                .filter(x -> x.getTags().containsAll(tags))
                .sorted(Comparator.comparing(Question::getCreationDateTime).reversed())
                .map(serviceHelper::getQuestionDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<QuestionDTO> filterQuestionsByTitle(String titlePattern) {
        return repositoryFactory.createQuestionRepository().findAll().stream()
                .filter(x -> x.getTitle().contains(titlePattern))
                .sorted(Comparator.comparing(Question::getCreationDateTime).reversed())
                .map(serviceHelper::getQuestionDTO)
                .collect(Collectors.toList());
    }

    /**
     * I suppose all the time that the userId is valid
     *
     * @param userId
     * @param title
     * @param text
     * @param tags
     * @return
     */
    @Transactional
    public QuestionDTO addQuestion(int userId, String title, String text, Set<Tag> tags) {
        User user = repositoryFactory.createUserRepository().findById(userId).orElseThrow(UserNotFoundException::new);
        if (user.getIsBlocked()) throw new NotEnoughPermissionsException();
        if (tags.isEmpty()) throw new NotEnoughTagsException();

        tags = saveQuestionTags(tags);

        QuestionDTO questionDTO = serviceHelper.getQuestionDTO(
                repositoryFactory.createQuestionRepository().save(new Question(userId, title, text, ZonedDateTime.now(), tags, 0)));

        eventPublisher.publishEvent(new QuestionCreatedEvent(questionDTO));
        return questionDTO;
    }

    @Transactional
    public boolean sendVote(int userId, int questionId, boolean value) {
        VoteQuestion currVote = repositoryFactory.createQuestionVoteRepository().findVoteFromUserForPost(userId, questionId).orElse(null);
        Question question = repositoryFactory.createQuestionRepository().findById(questionId).orElseThrow(QuestionNotFoundException::new);

        if (currVote != null && currVote.isValue() == value) return false;
        if (userId == question.getAuthorId()) return false;

        if (currVote == null) currVote = new VoteQuestion(null, userId, question.getAuthorId(), questionId, value);
        currVote.setValue(value);

        repositoryFactory.createQuestionVoteRepository().save(currVote);

        eventPublisher.publishEvent(new VotedQuestionEvent(serviceHelper.getQuestionDTO(
                repositoryFactory.createQuestionRepository().findById(question.getId()).orElseThrow(QuestionNotFoundException::new))));
        eventPublisher.publishEvent(new UserUpdatedEvent(UserDTO.ofEntity(repositoryFactory.createUserRepository().findById(userId).orElseThrow(UserNotFoundException::new))));
        eventPublisher.publishEvent(new UserUpdatedEvent(UserDTO.ofEntity(repositoryFactory.createUserRepository().findById(question.getAuthorId()).orElseThrow(UserNotFoundException::new))));
        return true;
    }

    @Transactional
    public List<TagDTO> listAllTags() {
        return repositoryFactory.createTagRepository().findAll().stream().map(TagDTO::ofEntity).collect(Collectors.toList());
    }

    @Transactional
    public QuestionDTO updateQuestion(int userId, int questionId, String newTitle, String newText) {
        User user = repositoryFactory.createUserRepository().findById(userId).orElseThrow(UserNotFoundException::new);
        Question question = repositoryFactory.createQuestionRepository().findById(questionId).orElseThrow(QueryTimeoutException::new);

        if (!user.getIsModerator()) throw new NotEnoughPermissionsException();

        question.setTitle(newTitle);
        question.setText(newText);

        QuestionDTO questionDTO = serviceHelper.getQuestionDTO(
                repositoryFactory.createQuestionRepository().save(question));

        eventPublisher.publishEvent(new QuestionUpdatedEvent(questionDTO));
        return questionDTO;
    }

    @Transactional
    public void deleteQuestion(int userId, int questionId) {
        User user = repositoryFactory.createUserRepository().findById(userId).orElseThrow(UserNotFoundException::new);
        Question question = repositoryFactory.createQuestionRepository().findById(questionId).orElseThrow(QuestionNotFoundException::new);

        if (!user.getIsModerator()) throw new NotEnoughPermissionsException();

        repositoryFactory.createQuestionRepository().remove(question);
        eventPublisher.publishEvent(new QuestionDeletedEvent(questionId));
        repositoryFactory.createQuestionVoteRepository().findAllVotesWithPostId(questionId)
                .forEach(v -> eventPublisher.publishEvent(new UserUpdatedEvent(UserDTO.ofEntity(
                        repositoryFactory.createUserRepository().findById(v.getCineDaId()).orElseThrow(UserNotFoundException::new)))));
        eventPublisher.publishEvent(new UserUpdatedEvent(UserDTO.ofEntity(repositoryFactory.createUserRepository().findById(question.getAuthorId()).orElseThrow(UserNotFoundException::new))));
    }

    @Transactional
    public QuestionDTO getQuestion(int questionId) {
        return serviceHelper.getQuestionDTO(repositoryFactory.createQuestionRepository().findById(questionId).orElseThrow(QuestionNotFoundException::new));
    }

    @Transactional
    protected Set<Tag> saveQuestionTags(Set<Tag> tags) {
        Set<Tag> existingTags = new HashSet<Tag>(repositoryFactory.createTagRepository().findAll());
        Set<Tag> newTags = new HashSet<>(tags);
        newTags.removeAll(existingTags);

        newTags.forEach(t -> {
            t.setId(null);
            repositoryFactory.createTagRepository().save(t);
        });

        newTags = new HashSet<>(repositoryFactory.createTagRepository().findAll());
        newTags.retainAll(tags);
        return newTags;
    }
}
