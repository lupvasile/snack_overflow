package ro.utcn.sd.vasi.SnackOverflow.controller.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import ro.utcn.sd.vasi.SnackOverflow.dto.UserLoggedInDTO;
import ro.utcn.sd.vasi.SnackOverflow.event.BaseEvent;
import ro.utcn.sd.vasi.SnackOverflow.services.UserManagementService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserManagementService userManagementService;
    private final SimpMessagingTemplate messagingTemplate;

    @GetMapping("/me")
    public UserLoggedInDTO readCurrentUser() {
        return UserLoggedInDTO.ofEntity(userManagementService.loadCurrentUser());
    }

    @PostMapping("/me2")
    public void asdf(@RequestBody UserLoggedInDTO user) {
        System.out.println(user);
    }


    //shoud this be post or get or put?
    @PutMapping("/users/{userId}/ban")
    public void banUser(@PathVariable int userId) {
        userManagementService.banUser(userManagementService.loadCurrentUser().getId(), userId);
    }

    @GetMapping("/users")
    public List<UserLoggedInDTO> listAllUsers() {
        return userManagementService.listAllUsers(userManagementService.loadCurrentUser().getId());
    }

    @EventListener(BaseEvent.class)
    public void handleEvent(BaseEvent event) {
        log.info("Got an event: {}.", event);
        messagingTemplate.convertAndSend("/topic/events", event);
    }
}
