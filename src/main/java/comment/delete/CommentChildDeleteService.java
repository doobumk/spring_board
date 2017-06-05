package comment.delete;

import comment.Comment;
import comment.CommentDaoInterface;
import comment.insert.CommentChildWriteServiceInterface;
import comment.insert.CommentWriteCommand;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by User on 2017-05-27.
 */
public class CommentChildDeleteService implements CommentChildDeleteServiceInterface {
    @Autowired
    private CommentDaoInterface commentDao;

    @Override
    public void deleteChild(CommentChildDeleteCommand cmd) {
        commentDao.deleteChild(cmd);
    }
}
