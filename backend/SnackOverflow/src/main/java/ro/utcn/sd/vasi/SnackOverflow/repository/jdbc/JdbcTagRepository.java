package ro.utcn.sd.vasi.SnackOverflow.repository.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;
import ro.utcn.sd.vasi.SnackOverflow.model.Tag;
import ro.utcn.sd.vasi.SnackOverflow.repository.api.TagRepository;


public class JdbcTagRepository extends JdbcGeneralRepository<Tag> implements TagRepository {

    public JdbcTagRepository(JdbcTemplate template) {
        super(template, Tag.class, new TagMapper());
    }
}
