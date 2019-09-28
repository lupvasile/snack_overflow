package ro.utcn.sd.vasi.SnackOverflow.repository.jdbc;

import org.springframework.jdbc.core.RowMapper;
import ro.utcn.sd.vasi.SnackOverflow.model.Tag;

import java.sql.ResultSet;
import java.sql.SQLException;


public class TagMapper implements RowMapper<Tag> {

    @Override
    public Tag mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Tag(rs.getInt("id"), rs.getString("name"));
    }
}
