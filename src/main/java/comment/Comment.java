package comment;

import java.util.Date;

/**
 * Created by User on 2017-05-24.
 */
public class Comment {
    private int commentId;
    private int id;
    private String content;
    private String writer;
    private int parent_ID;
    private int group_ID;
    private int depth;
    private Date date;


    public Comment(int id, String writer, String content,int parent_ID, int group_ID, int depth,Date date) {
        this.id = id;
        this.content = content;
        this.writer = writer;
        this.parent_ID = parent_ID;
        this.group_ID = group_ID;
        this.depth = depth;
        this.date = date;

    }




    public String getWriter() {
        return writer;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getParent_ID() {
        return parent_ID;
    }
    public int getGroup_ID() {
        return group_ID;
    }


    public int getDepth() {
        return depth;
    }
    public int getId() {
        return id;
    }



    public String getContent() {
        return content;
    }

    public Date getDate() {
        return date;
    }
}
