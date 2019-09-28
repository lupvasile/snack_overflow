package ro.utcn.sd.vasi.SnackOverflow.repository.jdbc;

import org.springframework.jdbc.core.RowMapper;
import ro.utcn.sd.vasi.SnackOverflow.model.VoteQuestion;

import java.sql.ResultSet;
import java.sql.SQLException;


public class VoteQuestionMapper implements RowMapper<VoteQuestion> {

    @Override
    public VoteQuestion mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new VoteQuestion(rs.getInt("id"), rs.getInt("user"), rs.getInt("vote_recipient"), rs.getInt("question"),
                rs.getBoolean("vote_type"));
    }
}
