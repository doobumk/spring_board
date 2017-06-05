package comment.delete;

/**
 * Created by User on 2017-05-27.
 */
public class CommentChildDeleteCommand {
    private int groupId;
    private int commentId;
    private int depth;

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }
}
