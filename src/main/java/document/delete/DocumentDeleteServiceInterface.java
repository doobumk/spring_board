package document.delete;

import document.Document;
import document.DocumentUploadFile;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by User on 2017-05-14.
 */
public interface DocumentDeleteServiceInterface {
    void delete(Document document,DocumentUploadFile documentUploadFile);

}
