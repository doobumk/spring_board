package comment.insert;

import comment.Comment;

/**
 * Created by User on 2017-05-24.
 */
public interface CommentWriteServiceInterface {
    void write(CommentWriteCommand commentWriteCommand);
}
