package ro.utcn.sd.vasi.SnackOverflow.event;

import lombok.Data;

@Data
public class BaseEvent {
    private final EventType type;
}
