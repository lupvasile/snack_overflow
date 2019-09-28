package ro.utcn.sd.vasi.SnackOverflow.dto;

import lombok.Data;
import ro.utcn.sd.vasi.SnackOverflow.model.User;

@Data
public class UserDTO {
    private int id;
    private String username;
    private int score;

    //is it secure to have isBlocked and isModerator?

    public static UserDTO ofEntity(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setScore(user.getScore());

        return userDTO;
    }
}
