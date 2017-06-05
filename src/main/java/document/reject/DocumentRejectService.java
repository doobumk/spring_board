package document.reject;

import document.Document;
import document.DocumentDao;
import document.DocumentDaoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by User on 2017-05-06.
 */
public class DocumentRejectService implements DocumentRejectServiceInterface {
    @Autowired
    private DocumentDaoInterface documentDao;


    @Override
    //@Transactional
    public void reject(Document document){
        documentDao.reject(document);
    }
}
