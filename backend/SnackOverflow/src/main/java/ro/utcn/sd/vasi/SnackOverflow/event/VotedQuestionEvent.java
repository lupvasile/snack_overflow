package ro.utcn.sd.vasi.SnackOverflow.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ro.utcn.sd.vasi.SnackOverflow.dto.QuestionDTO;

@Data
@EqualsAndHashCode(callSuper = true)
public class VotedQuestionEvent extends BaseEvent {
    private final QuestionDTO questionDTO;

    public VotedQuestionEvent(QuestionDTO questionDTO) {
        super(EventType.VOTED_QUESTION);
        this.questionDTO = questionDTO;
    }
}
