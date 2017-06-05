package document.write;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by User on 2017-05-03.
 */
public class DocumentWriteCommand {
    private int writer_level;
    private String writer_name;
    private String title;
    private String content;
    private int type;
    private int state;
    private int approval_level;
    private MultipartFile multipartFile;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    public int getApproval_level() {
        return approval_level;
    }

    public void setApproval_level(int approval_level) {
        this.approval_level = approval_level;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getWriter_level() {
        return writer_level;
    }

    public void setWriter_level(int writer_level) {
        this.writer_level = writer_level;
    }

    public String getWriter_name() {
        return writer_name;
    }

    public void setWriter_name(String writer_name) {
        this.writer_name = writer_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
