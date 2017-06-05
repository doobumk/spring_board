package document.approval;

import document.Document;

import java.util.Date;

/**
 * Created by User on 2017-05-06.
 */
public class DocumentApprovalCommand {
    private int approval_level;
    private int id;
    private int state;
    private String approval;
    private Date approval_date;

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

    public Date getApproval_date() {
        return approval_date;
    }

    public void setApproval_date(Date approval_date) {
        this.approval_date = approval_date;
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
}
