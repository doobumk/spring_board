package comment;

import comment.delete.CommentChildDeleteCommand;
import comment.update.CommentUpdateCommand;

import java.util.List;

/**
 * Created by User on 2017-05-24.
 */
public interface CommentDaoInterface {
    void insert(Comment comment);
    void insertComment(Comment comment);
    void delete(int groupid);
    void deleteChild(CommentChildDeleteCommand commentChildDeleteCommand);
    void update(Comment comment);
    void copy(Comment comment);
    void change(CommentUpdateCommand commentUpdateCommnad);
    Comment select(int  id);
    List<Comment> getCommentList(int id);

}
