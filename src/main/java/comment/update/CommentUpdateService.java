package comment.update;

import comment.CommentDaoInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by User on 2017-05-27.
 */
public class CommentUpdateService implements CommentUpdateServiceInterface{
    @Autowired
    private CommentDaoInterface commentDao;

    @Override
    public void changeContent(CommentUpdateCommand commentUpdateCommand) {
        commentUpdateCommand.setDate(new Date());
        commentDao.change(commentUpdateCommand);
    }
}
