package ro.utcn.sd.vasi.SnackOverflow.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ro.utcn.sd.vasi.SnackOverflow.dto.AnswerDTO;

@Data
@EqualsAndHashCode(callSuper = true)
public class AnswerCreatedEvent extends BaseEvent {
    private final AnswerDTO answerDTO;

    public AnswerCreatedEvent(AnswerDTO answerDTO) {
        super(EventType.ANSWER_CREATED);
        this.answerDTO = answerDTO;
    }
}
