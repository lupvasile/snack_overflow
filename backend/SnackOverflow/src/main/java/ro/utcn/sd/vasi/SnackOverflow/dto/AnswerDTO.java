package ro.utcn.sd.vasi.SnackOverflow.dto;

import lombok.Data;
import ro.utcn.sd.vasi.SnackOverflow.model.Answer;

import java.time.format.DateTimeFormatter;

@Data
public class AnswerDTO {
    private int id;
    private UserDTO author;
    private String text;
    private String creationDateTime;
    private int questionId;
    private int voteCount;

    //I think is wrong to use repositorty here for getting author from answer.getAuthorId()
    public static AnswerDTO ofEntity(Answer answer, UserDTO author) {
        AnswerDTO answerDTO = new AnswerDTO();
        answerDTO.setId(answer.getId());
        answerDTO.setAuthor(author);
        answerDTO.setText(answer.getText());
        answerDTO.setCreationDateTime(DateTimeFormatter.ofPattern("dd/MM/yyyy - hh:mm").format(answer.getCreationDateTime()));
        answerDTO.setQuestionId(answer.getQuestionId());
        answerDTO.setVoteCount(answer.getVoteCount());

        return answerDTO;
    }
}
