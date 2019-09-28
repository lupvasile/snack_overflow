package ro.utcn.sd.vasi.SnackOverflow.repository.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ro.utcn.sd.vasi.SnackOverflow.model.Question;
import ro.utcn.sd.vasi.SnackOverflow.model.Tag;
import ro.utcn.sd.vasi.SnackOverflow.repository.api.QuestionRepository;

import java.util.*;

public class JdbcQuestionRepository extends JdbcGeneralRepository<Question> implements QuestionRepository {
    private JdbcTagRepository jdbcTagRepository;

    public JdbcQuestionRepository(JdbcTemplate template, JdbcTagRepository tagRepository) {
        super(template, Question.class, new QuestionMapper());
        this.jdbcTagRepository = tagRepository;
    }

    @Override
    public List<Question> findAll() {
        List<Question> questions = super.findAll();
        questions.forEach(x -> makeQuestionComplete(x));

        return questions;
    }

    @Override
    protected int insert(Question question) {
        int questionId = super.insert(question);

        SimpleJdbcInsert insert = new SimpleJdbcInsert(template);
        insert.setTableName("question_tag");
        insert.usingGeneratedKeyColumns("id");

        Map<String, Object> map = new HashMap<>();
        question.getTags().forEach(t -> {
            map.clear();
            map.put("question", questionId);
            map.put("tag", t.getId());
            insert.executeAndReturnKey(map);
        });

        return questionId;
    }

    @Override
    public Optional<Question> findById(int id) {
        Optional<Question> q = super.findById(id);
        if (q.isPresent()) {
            Question goodQ = q.get();
            makeQuestionComplete(goodQ);
            q = Optional.of(goodQ);
        }

        return q;
    }

    private void makeQuestionComplete(Question question) {
        List<Integer> tagIds = template.query("SELECT * FROM question_tag WHERE question = ?", (rs, i) -> rs.getInt("tag"),
                question.getId());

        Set<Tag> tags = new HashSet<>();
        tagIds.forEach(tagId -> tags.add(jdbcTagRepository.findById(tagId).orElse(null)));
        question.getTags().addAll(tags);
    }
}
