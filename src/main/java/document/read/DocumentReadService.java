package document.read;

import document.Document;
import document.DocumentDao;
import document.DocumentDaoInterface;
import document.DocumentUploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by User on 2017-05-03.
 */
public class DocumentReadService implements DocumentReadServiceInterface {

    @Autowired
    private DocumentDaoInterface documentDao;



    @Override
    //@Transactional
    public Document read(int id){
       Document document =  documentDao.getBoard(id);
       return document;
    }

    @Override
    public String getUploadFile(int id) {
        System.out.println("SERVIE ID"+id);
        return documentDao.getUploadFile(id);
    }
}
