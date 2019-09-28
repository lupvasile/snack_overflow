package ro.utcn.sd.vasi.SnackOverflow.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ro.utcn.sd.vasi.SnackOverflow.dto.UserDTO;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserUpdatedEvent extends BaseEvent {
    private final UserDTO userDTO;

    public UserUpdatedEvent(UserDTO userDTO) {
        super(EventType.USER_UPDATED);
        this.userDTO = userDTO;
    }
}
