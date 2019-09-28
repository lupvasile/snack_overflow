package ro.utcn.sd.vasi.SnackOverflow.repository.jdbc;

import org.springframework.jdbc.core.RowMapper;
import ro.utcn.sd.vasi.SnackOverflow.model.VoteAnswer;

import java.sql.ResultSet;
import java.sql.SQLException;


public class VoteAnswerMapper implements RowMapper<VoteAnswer> {

    @Override
    public VoteAnswer mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new VoteAnswer(rs.getInt("id"), rs.getInt("user"), rs.getInt("vote_recipient"), rs.getInt("answer"),
                rs.getBoolean("vote_type"));
    }
}
