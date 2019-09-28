package ro.utcn.sd.vasi.SnackOverflow.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Entity
@NoArgsConstructor
public class Answer implements HasIntId {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "author")
    private Integer authorId;
    private String text;
    @Column(name = "creation")
    private ZonedDateTime creationDateTime;
    @Column(name = "question")
    private Integer questionId;
    @Transient
    private int voteCount = 0;

    public Answer(Integer authorId, String text, ZonedDateTime creationDateTime, Integer questionId, int voteCount) {
        this(null, authorId, text, creationDateTime, questionId, voteCount);
    }

    public Answer(Integer id, Integer authorId, String text, ZonedDateTime creationDateTime, Integer questionId, int voteCount) {
        this.id = id;
        this.authorId = authorId;
        this.text = text;
        this.creationDateTime = creationDateTime;
        this.questionId = questionId;
        this.voteCount = voteCount;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", authorId=" + authorId +
                ", text='" + text + '\'' +
                ", creationDate=" + DateTimeFormatter.ofPattern("dd/MM/yyyy - hh:mm").format(creationDateTime) +
                ", questionId=" + questionId +
                ", voteCount=" + voteCount +
                '}';
    }
}