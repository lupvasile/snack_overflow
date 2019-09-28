package ro.utcn.sd.vasi.SnackOverflow.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class Question implements HasIntId {
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "question_tag", joinColumns = @JoinColumn(name = "question"), inverseJoinColumns = @JoinColumn(name = "tag"))
    //@Convert(converter = ZonedDateTime.class)
    private final Set<Tag> tags = new HashSet<>();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "author")
    private Integer authorId;
    private String title;
    private String text;
    @Column(name = "creation")
    private ZonedDateTime creationDateTime;
    @Transient
    private int voteCount = 0;

    public Question(Integer authorId, String title, String text, ZonedDateTime creationDateTime, Set<Tag> tags, int voteCount) {
        this(null, authorId, title, text, creationDateTime, tags, voteCount);
    }

    public Question(Integer id, Integer authorId, String title, String text, ZonedDateTime creationDateTime, Set<Tag> tags, int voteCount) {
        this.id = id;
        this.authorId = authorId;
        this.title = title;
        this.text = text;
        this.creationDateTime = creationDateTime;
        this.voteCount = voteCount;

        this.tags.addAll(tags);
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", authorId=" + authorId +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", creationDate=" + DateTimeFormatter.ofPattern("dd/MM/yyyy - hh:mm").format(creationDateTime) +
                ", tags=" + tags +
                ", voteCount=" + voteCount +
                '}';
    }

    public String toStringNoText() {
        return "Question{" +
                "id=" + id +
                ", authorId=" + authorId +
                ", title='" + title + '\'' +
                //", text='" + text + '\'' +
                ", creationDate=" + DateTimeFormatter.ofPattern("dd/MM/yyyy - hh:mm").format(creationDateTime) +
                ", tags=" + tags +
                ", voteCount=" + voteCount +
                '}';
    }
}
