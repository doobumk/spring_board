package comment;

import comment.delete.CommentChildDeleteCommand;
import comment.update.CommentUpdateCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

/**
 * Created by User on 2017-05-24.
 */
@Repository("commentDao")
public class CommentDao implements CommentDaoInterface{
    private JdbcTemplate jdbcTemplate;

    private static final String insertComment = "insert into comment (id,writer,comment,parentid,groupid,depth,date) values (?,?,?,?,?,?,?)";
    private static final String insertSubComment = "insert into comment (id,writer,comment,parentid,groupid,depth,date) values (?,?,?,?,?,?,?)";
    private static final String copyCommentId = "update comment set groupID = ? where commentid=?";
    private static final String insertChildComment = "update comment set parentid = parentid+1 where groupId=? and parentid > ?";
    private static final String getCommentList="select * from comment where id=? order by groupid desc, parentid asc ";
    private static final String commentInfo = "select * from comment where commentid=? ";
    private static final String deleteParent = "delete from comment where groupid=?";
    private static final String deleteChild = "delete from comment where groupid=? and commentid=? or depth > ?";
    private static final String changeContent = "update comment set comment=? , date=? where commentid=?";
    @Autowired
    public CommentDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<Comment> commentRowMapper = new RowMapper<Comment>() {
        @Override
        public Comment mapRow(ResultSet resultSet, int i) throws SQLException {
            Comment comment = new Comment(resultSet.getInt("ID"),resultSet.getString("writer"),resultSet.getString("COMMENT"),
                    resultSet.getInt("parentID"),resultSet.getInt("groupID"),resultSet.getInt("depth"),resultSet.getTimestamp("date"));
            comment.setCommentId(resultSet.getInt("COMMENTID"));
            return comment;
        }
    };

    @Override
    public void copy(Comment comment) {
        jdbcTemplate.update(copyCommentId,comment.getCommentId(),comment.getCommentId());
    }

    @Override
    public void insert(final Comment comment) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con)
                    throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(
                        insertComment,
                        new String[] {"commentId"});
                pstmt.setInt(
                        1,  comment.getId());
                pstmt.setString(2,  comment.getWriter());
                pstmt.setString(3,  comment.getContent());
                pstmt.setInt(4, comment.getParent_ID());
                pstmt.setInt(5, comment.getGroup_ID());
                pstmt.setInt(6, comment.getDepth());
                pstmt.setTimestamp(7,new Timestamp(comment.getDate().getTime()));
                return pstmt;
            }
        }, keyHolder);
        Number keyValue = keyHolder.getKey();
        //System.out.println(keyValue);
        comment.setCommentId(keyValue.intValue());

    }

    @Override
    public void insertComment(final Comment comment) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con)
                    throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(
                        insertSubComment,
                        new String[] {"commentId"});
                pstmt.setInt(
                        1,  comment.getId());
                pstmt.setString(2,  comment.getWriter());
                pstmt.setString(3,  comment.getContent());
                pstmt.setInt(4, comment.getParent_ID()+1);
                pstmt.setInt(5, comment.getGroup_ID());
                pstmt.setInt(6, comment.getDepth()+1);
                pstmt.setTimestamp(7,new Timestamp(comment.getDate().getTime()));
                return pstmt;
            }
        }, keyHolder);
        Number keyValue = keyHolder.getKey();
        comment.setCommentId(keyValue.intValue());

    }

    @Override
    public void delete(int groupid) {
        jdbcTemplate.update(deleteParent,groupid);
    }

    @Override
    public void deleteChild(CommentChildDeleteCommand cmd) {
        jdbcTemplate.update(deleteChild,cmd.getGroupId(),cmd.getCommentId(),cmd.getDepth());
    }

    @Override
    public void update(Comment comment) {
        jdbcTemplate.update(insertChildComment,comment.getGroup_ID(),comment.getParent_ID());
    }

    @Override
    public void change(CommentUpdateCommand commentUpdateCommand) {
        jdbcTemplate.update(changeContent,commentUpdateCommand.getContent(),commentUpdateCommand.getDate(),commentUpdateCommand.getCommentId());
    }

    @Override
    public Comment select(int id) {
        return jdbcTemplate.queryForObject(commentInfo,commentRowMapper,id);
    }

    @Override
    public List<Comment> getCommentList(int id) {
        List<Comment> list = jdbcTemplate.query(getCommentList,commentRowMapper,id);
        return list;
    }
}
