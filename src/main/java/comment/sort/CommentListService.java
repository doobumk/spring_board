package comment.sort;

import comment.Comment;
import comment.CommentDao;
import comment.CommentDaoInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by User on 2017-05-24.
 */
public class CommentListService implements CommentListServiceInterface {
    @Autowired
    private CommentDaoInterface commentDao;

    @Override
    public List<Comment> list(CommentListCommand commentListCommand) {
        List<Comment> commentList = commentDao.getCommentList(commentListCommand.getId());

        return commentList;
    }
}
