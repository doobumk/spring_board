package document.delete;

import document.Document;
import document.DocumentDao;
import document.DocumentDaoInterface;
import document.DocumentUploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by User on 2017-05-04.
 */
public class DocumentDeleteService implements DocumentDeleteServiceInterface {

    @Autowired
    private DocumentDaoInterface documentDao;

    @Override
   // @Transactional
    public void delete(Document document,DocumentUploadFile documentUploadFile){
        documentDao.delete(document);
        documentUploadFile.setId(document.getId());
        documentDao.deleteFile(documentUploadFile);
    }


}
