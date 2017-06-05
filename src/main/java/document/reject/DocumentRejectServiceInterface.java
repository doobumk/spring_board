package document.reject;

import document.Document;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by User on 2017-05-14.
 */
public interface DocumentRejectServiceInterface {
    void reject(Document document);
}
