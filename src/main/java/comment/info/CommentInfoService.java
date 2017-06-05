package comment.info;

import comment.Comment;
import comment.CommentDaoInterface;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by User on 2017-05-26.
 */
public class CommentInfoService implements CommentInfoServiceInterface {
    @Autowired
    private CommentDaoInterface commentDao;

    @Override
    public Comment getInfo(int commentId) {
        return commentDao.select(commentId);

    }
}
