package member;

import member.register.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Date;
import java.util.List;

@Repository("memberDao")
public class MemberDao implements MemberDaoInterface {

	private JdbcTemplate jdbcTemplate;
	private RowMapper<Member> memberRowMapper = new RowMapper<Member>() {
        @Override
        public Member mapRow(ResultSet rs, int rowNum)
                throws SQLException {
            Member member = new Member(rs.getString("EMAIL"),
                    rs.getString("PASSWORD"),
                    rs.getString("NAME"),
                    rs.getTimestamp("REGDATE"),
                    rs.getString("GROUPCODE"),
                    rs.getInt("LEVEL"));
            member.setId(rs.getLong("id"));
			member.setRegaccept(rs.getInt("regaccept"));
            return member;
        }
    };

	private static final String selectByEmail = "select * from MEMBER where EMAIL = ?";
	private static final String insert = "insert into MEMBER (EMAIL, PASSWORD, NAME, REGDATE, GROUPCODE, LEVEL) values (?, ?, ?, ?,?,?)";
	private static final String changePassword = "update MEMBER set NAME = ?, PASSWORD = ? where EMAIL = ?";
	private static final String changeGroupcode = "update MEMBER set groupcode = ? where EMAIL= ?";
	private static final String selectAll = "select * from MEMBER";
    private static final String countSQL = "select count(*) from MEMBER";
    private static final String selectByRegdate = "select * from MEMBER where REGDATE between ? and ? order by REGDATE desc";
    private static final String selectById = "select * from MEMBER where id=?";
    private static final String acceptReg = "update Member set regaccept = regaccept+1 where id=?";

    @Autowired
	public MemberDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

    @Override
	public Member selectById(Long id){
        Member member = jdbcTemplate.queryForObject(selectById, memberRowMapper, id);
        return member;
    }
	@Override
	public Member selectByEmail(String email) {
		List<Member> results = jdbcTemplate.query(
				selectByEmail,memberRowMapper, email);

		return results.isEmpty() ? null : results.get(0);
	}

	@Override
	public void insert(final Member member) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(
						insert,
						new String[] {"ID"});
				pstmt.setString(1,  member.getEmail());
				pstmt.setString(2,  member.getPassword());
				pstmt.setString(3,  member.getName());
				pstmt.setTimestamp(4,  
						new Timestamp(member.getRegisterDate().getTime()));
				pstmt.setString(5, member.
						getGroupcode());
				pstmt.setInt(6,member.getLevel());
				return pstmt;
			}
		}, keyHolder);
		Number keyValue = keyHolder.getKey();
		member.setId(keyValue.longValue());
	}

	@Override
	public void changeGroupcode(Member member) {
        jdbcTemplate.update(changeGroupcode, member.getGroupcode(), member.getEmail());
    }

	@Override
	public void changePassword(Member member) {
		jdbcTemplate.update(changePassword, member.getName(), member.getPassword(), member.getEmail());
	}

	@Override
	public List<Member> selectAll() {
		List<Member> results = jdbcTemplate.query(selectAll,
                memberRowMapper);
		return results;
	}

	@Override
	public int count() {
		Integer count = jdbcTemplate.queryForObject(countSQL, Integer.class);
		return count;
	}

	@Override
	public List<Member> selectByRegdate(Date from, Date to){
		List<Member> result = jdbcTemplate.query(selectByRegdate,memberRowMapper,from,to);
        return result;
	}

	@Override
	public void regAccept(int id) {
		jdbcTemplate.update(acceptReg,id);
	}
}
