package comment.delete;

import comment.CommentDaoInterface;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by User on 2017-05-27.
 */
public class CommentParentDeleteService implements CommentParentDeleteServiceInterface {
    @Autowired
    private CommentDaoInterface commentDao;
    @Override
    public void deleteParent(int groupId) {
        commentDao.delete(groupId);
    }
}
