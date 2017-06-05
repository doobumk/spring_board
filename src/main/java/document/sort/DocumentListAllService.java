package document.sort;

import document.Document;
import document.DocumentDao;
import document.DocumentDaoInterface;
import document.search.DocumentSearchCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by User on 2017-05-03.
 */
public class DocumentListAllService implements DocumentListAllServiceInterface {

    @Autowired
    private DocumentDaoInterface documentDao;

    @Override
  //  @Transactional
    public List<Document> listAll(DocumentListCommand documentListCommand){
        DocumentSearchCommand documentSearchCommand = new DocumentSearchCommand();
        //if(documentListCommand.getSearchCondition() == null) documentListCommand.setSearchCondition("TITLE");
        //if(documentListCommand.getSearchKeyword() == null) documentListCommand.setSearchKeyword("");

        int total = documentDao.count();
        documentListCommand.setSize(10);
        documentListCommand.setTotal(total);
        documentListCommand.Paging();
        System.out.println("서비스"+documentListCommand.toString());

       List<Document> result = documentDao.getDocumentList(documentListCommand);
       return result;
    }



}
