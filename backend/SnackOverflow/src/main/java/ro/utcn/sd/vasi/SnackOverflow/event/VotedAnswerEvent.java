package ro.utcn.sd.vasi.SnackOverflow.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ro.utcn.sd.vasi.SnackOverflow.dto.AnswerDTO;

@Data
@EqualsAndHashCode(callSuper = true)
public class VotedAnswerEvent extends BaseEvent {
    private final AnswerDTO answerDTO;

    public VotedAnswerEvent(AnswerDTO answerDTO) {
        super(EventType.VOTED_ANSWER);
        this.answerDTO = answerDTO;
    }
}
