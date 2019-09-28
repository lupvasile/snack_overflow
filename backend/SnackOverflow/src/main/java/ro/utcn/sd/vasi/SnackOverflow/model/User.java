package ro.utcn.sd.vasi.SnackOverflow.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User implements HasIntId {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    @Transient
    private int score = 0;
    private Boolean isModerator;
    @Column(name = "is_banned")
    private Boolean isBlocked;
}
