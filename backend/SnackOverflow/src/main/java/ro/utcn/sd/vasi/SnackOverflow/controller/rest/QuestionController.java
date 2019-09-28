package ro.utcn.sd.vasi.SnackOverflow.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.utcn.sd.vasi.SnackOverflow.dto.QuestionDTO;
import ro.utcn.sd.vasi.SnackOverflow.dto.TagDTO;
import ro.utcn.sd.vasi.SnackOverflow.model.Tag;
import ro.utcn.sd.vasi.SnackOverflow.services.QuestionManagementService;
import ro.utcn.sd.vasi.SnackOverflow.services.UserManagementService;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionManagementService questionManagementService;
    private final UserManagementService userManagementService;

    @GetMapping("/questions")
    public List<QuestionDTO> listQuestions() {
        return questionManagementService.listQuestions();
    }

    @PostMapping("/questions")
    public QuestionDTO addQuestion(@RequestBody QuestionDTO question) {
        Set<Tag> tags = question.getTags().stream().map(Tag::new).collect(Collectors.toSet());
        return questionManagementService.addQuestion(userManagementService.loadCurrentUser().getId(), question.getTitle(), question.getText(), tags);
    }

    @GetMapping("/questions/findByTags/{tagsAsString}")
    public List<QuestionDTO> filterByTag(@PathVariable String tagsAsString) {
        Set<Tag> tags = Arrays.asList(tagsAsString.split(",")).stream().map(Tag::new).collect(Collectors.toSet());
        return questionManagementService.filterQuestionsByTag(tags);
    }

    @GetMapping("/questions/findByTitle/{title}")
    public List<QuestionDTO> handleFilterByTitle(@PathVariable String title) {
        System.out.println("filtering with title: " + title);
        return questionManagementService.filterQuestionsByTitle(title);
    }

    @PutMapping("questions/{questionId}")
    public QuestionDTO handleUpdateQuestion(@PathVariable int questionId, @RequestBody QuestionDTO question) {
        return questionManagementService.updateQuestion(userManagementService.loadCurrentUser().getId(), questionId, question.getTitle(), question.getText());
    }

    @DeleteMapping("/questions/{questionId}")
    public void deleteQuestion(@PathVariable int questionId) {
        questionManagementService.deleteQuestion(userManagementService.loadCurrentUser().getId(), questionId);
    }

    @GetMapping("/questions/tags")
    public List<TagDTO> listTags() {
        return questionManagementService.listAllTags();
    }
}
