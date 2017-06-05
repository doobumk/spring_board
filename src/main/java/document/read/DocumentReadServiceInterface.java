package document.read;

import document.Document;
import document.DocumentUploadFile;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by User on 2017-05-14.
 */
public interface DocumentReadServiceInterface {
    Document read(int id);
    String getUploadFile(int id);
}
