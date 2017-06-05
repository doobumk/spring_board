package document.approval;

import document.Document;
import document.DocumentDao;
import document.DocumentDaoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by User on 2017-05-05.
 */
public class DocumentApprovalService implements DocumentApprovalServiceInterface {

    @Autowired
    private DocumentDaoInterface documentDao;



    @Override
   // @Transactional
    public void approval(Document document){
        documentDao.approval(document);
    }
}
