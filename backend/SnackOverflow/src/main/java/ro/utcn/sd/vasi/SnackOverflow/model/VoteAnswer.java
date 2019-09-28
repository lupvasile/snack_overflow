package ro.utcn.sd.vasi.SnackOverflow.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class VoteAnswer implements HasIntId {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user")
    private Integer cineDaId;
    @Column(name = "vote_recipient")
    private Integer cinePrimesteId;
    @Column(name = "answer")
    private Integer postId;
    @Column(name = "vote_type")
    private boolean value;
}
