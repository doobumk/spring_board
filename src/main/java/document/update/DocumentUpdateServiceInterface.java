package document.update;

import document.Document;
import document.DocumentUploadFile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by User on 2017-05-14.
 */
public interface DocumentUpdateServiceInterface {
    void update(Document document, DocumentUploadFile documentUploadFile, MultipartFile multipartFile);
}
