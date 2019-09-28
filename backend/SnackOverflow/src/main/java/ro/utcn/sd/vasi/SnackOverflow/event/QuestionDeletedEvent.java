package ro.utcn.sd.vasi.SnackOverflow.event;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class QuestionDeletedEvent extends BaseEvent {
    private int questionId;

    public QuestionDeletedEvent(int questionId) {
        super(EventType.QUESTION_DELETED);
        this.questionId = questionId;
    }
}
