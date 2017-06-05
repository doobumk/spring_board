package document;


import java.util.Date;

public class Document {
   private int id;
   private int writer_level;
   private String writer_name;
   private String title;
   private String content;
   private int type;
   private int state;
   private String approval;
   private int approval_level;
   private Date createDate;
   private Date modifiedDate;
   private Date approvalDate;



    public Document(int writer_level, String writer_name, String title, String content, int type, int state,int approval_level, Date createDate) {
        this.writer_level = writer_level;
        this.writer_name = writer_name;
        this.title = title;
        this.content = content;
        this.type = type;
        this.state = state;
        this.approval_level = approval_level;
        this.createDate = createDate;
    }

    public int getApproval_level() {
        return approval_level;
    }

    public void setApproval_level(int approval_level) {
        this.approval_level = approval_level;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getApproval() {
        return approval;
    }

    public void setApproval(String approval) {
        this.approval = approval;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Date getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }

    @Override
    public String toString() {
        return "Document{" +
                "id=" + id +
                ", writer_level='" + writer_level + '\'' +
                ", writer_name='" + writer_name + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", type='" + type + '\'' +
                ", state='" + state + '\'' +
                ", approval='" + approval + '\'' +
                ", createDate=" + createDate +
                ", modifiedDate=" + modifiedDate +
                ", approvalDate=" + approvalDate +
                '}';
    }
}
