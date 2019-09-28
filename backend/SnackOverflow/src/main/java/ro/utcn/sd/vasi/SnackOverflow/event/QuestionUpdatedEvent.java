package ro.utcn.sd.vasi.SnackOverflow.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ro.utcn.sd.vasi.SnackOverflow.dto.QuestionDTO;

@Data
@EqualsAndHashCode(callSuper = true)
public class QuestionUpdatedEvent extends BaseEvent {
    private final QuestionDTO questionDTO;

    public QuestionUpdatedEvent(QuestionDTO questionDTO) {
        super(EventType.QUESTION_UPDATED);
        this.questionDTO = questionDTO;
    }
}
