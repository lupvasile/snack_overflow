package ro.utcn.sd.vasi.SnackOverflow.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ro.utcn.sd.vasi.SnackOverflow.dto.AnswerDTO;

@Data
@EqualsAndHashCode(callSuper = true)
public class AnswerUpdatedEvent extends BaseEvent {
    private final AnswerDTO answerDTO;

    public AnswerUpdatedEvent(AnswerDTO answerDTO) {
        super(EventType.ANSWER_UPDATED);
        this.answerDTO = answerDTO;
    }
}
