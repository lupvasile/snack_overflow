package ro.utcn.sd.vasi.SnackOverflow.repository.jdbc;

import org.springframework.jdbc.core.RowMapper;
import ro.utcn.sd.vasi.SnackOverflow.model.Answer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZonedDateTime;


public class AnswerMapper implements RowMapper<Answer> {

    @Override
    public Answer mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Answer(rs.getInt("id"),
                rs.getInt("author"),
                rs.getString("text"),
                ZonedDateTime.parse(rs.getString("creation")),
                rs.getInt("question"),
                0);
    }
}
