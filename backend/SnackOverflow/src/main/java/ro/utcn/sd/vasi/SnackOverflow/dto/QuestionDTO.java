package ro.utcn.sd.vasi.SnackOverflow.dto;

import lombok.Data;
import org.springframework.util.CollectionUtils;
import ro.utcn.sd.vasi.SnackOverflow.model.Question;
import ro.utcn.sd.vasi.SnackOverflow.model.Tag;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class QuestionDTO {
    private int id;
    private UserDTO author;
    private String title;
    private String text;
    private String creationDateTime;
    private List<String> tags;
    private int voteCount;

    public static QuestionDTO ofEntity(Question question, UserDTO author) {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setId(question.getId());
        questionDTO.setAuthor(author);
        questionDTO.setTitle(question.getTitle());
        questionDTO.setText(question.getText());
        questionDTO.setCreationDateTime(DateTimeFormatter.ofPattern("dd/MM/yyyy - hh:mm").format(question.getCreationDateTime()));
        questionDTO.setVoteCount(question.getVoteCount());

        if (!CollectionUtils.isEmpty(question.getTags())) {
            questionDTO.setTags(question.getTags().stream()
                    .map(Tag::toString)
                    .collect(Collectors.toList()));
        }

        return questionDTO;
    }
}
