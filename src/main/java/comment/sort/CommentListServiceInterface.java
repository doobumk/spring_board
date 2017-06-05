package comment.sort;

import comment.Comment;

import java.util.List;

/**
 * Created by User on 2017-05-24.
 */
public interface CommentListServiceInterface {
    List<Comment> list(CommentListCommand comment);

}
