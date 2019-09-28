package ro.utcn.sd.vasi.SnackOverflow.event;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AnswerDeletedEvent extends BaseEvent {
    private int answerId;

    public AnswerDeletedEvent(int answerId) {
        super(EventType.ANSWER_DELETED);
        this.answerId = answerId;
    }
}
