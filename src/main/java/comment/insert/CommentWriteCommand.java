package comment.insert;

/**
 * Created by User on 2017-05-24.
 */
public class CommentWriteCommand {

    private int commentId;
    private int id;
    private String content;
    private String writer;
    private int parent_ID;
    private int group_ID;
    private int depth;



    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getParent_ID() {
        return parent_ID;
    }

    public void setParent_ID(int parent_ID) {
        this.parent_ID = parent_ID;
    }

    public int getGroup_ID() {
        return group_ID;
    }

    public void setGroup_ID(int group_ID) {
        this.group_ID = group_ID;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }
}
