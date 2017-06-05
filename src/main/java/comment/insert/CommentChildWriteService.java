package comment.insert;

import comment.Comment;
import comment.CommentDao;
import comment.CommentDaoInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by User on 2017-05-26.
 */
public class CommentChildWriteService implements CommentChildWriteServiceInterface{
    @Autowired
    private CommentDaoInterface commentDao;

    @Override
    public void writeChild(CommentWriteCommand cmd) {
        Comment comment = new Comment(cmd.getId(),cmd.getWriter(),cmd.getContent(),cmd.getParent_ID(),cmd.getGroup_ID(),cmd.getDepth(),new Date());

        commentDao.update(comment);
        commentDao.insertComment(comment);



    }
}
