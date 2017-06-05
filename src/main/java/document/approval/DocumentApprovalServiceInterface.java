package document.approval;

import document.Document;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by User on 2017-05-14.
 */
public interface DocumentApprovalServiceInterface {

    void approval(Document document);
}
