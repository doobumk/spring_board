package document.sort;

import document.Document;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by User on 2017-05-14.
 */
public interface DocumentListAllServiceInterface {

    List<Document> listAll(DocumentListCommand documentListCommand);

}
