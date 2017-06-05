package comment.insert;

import comment.Comment;
import comment.CommentDaoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.util.Date;

/**
 * Created by User on 2017-05-24.
 */

@Controller
public class CommentWriteService implements CommentWriteServiceInterface {

    @Autowired
    private CommentDaoInterface commentDao;

    @Override
    public void write(CommentWriteCommand cmd) {
        Comment comment = new Comment(cmd.getId(),cmd.getWriter(),cmd.getContent(),cmd.getParent_ID(),cmd.getGroup_ID(),cmd.getDepth(),new Date());
        commentDao.insert(comment);
        System.out.println(comment.getCommentId());
        commentDao.copy(comment);
        //commentDao.copy();
    }

}
