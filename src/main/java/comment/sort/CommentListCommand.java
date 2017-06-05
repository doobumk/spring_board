package comment.sort;

import java.util.Date;

/**
 * Created by User on 2017-05-24.
 */
public class CommentListCommand {
    private int id;
    private String content;
    private String writer;
    private Date date;

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
}
