package document.update;

import document.Document;
import document.DocumentDao;
import document.DocumentDaoInterface;
import document.DocumentUploadFile;
import document.write.DocumentWriteCommand;
import document.write.DocumentWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by User on 2017-05-04.
 */
public class DocumentUpdateService implements DocumentUpdateServiceInterface {

    @Autowired
    private DocumentDaoInterface documentDao;

    @Override
    public void update(Document document, DocumentUploadFile documentUploadFile,MultipartFile multipartFile) {
        documentDao.update(document);
        String fileName = null;
        multipartFile.getOriginalFilename();
        if(!multipartFile.isEmpty()){
            fileName = multipartFile.getOriginalFilename();
            try {
                multipartFile.transferTo(new File("D:/"+fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        documentUploadFile.setId(document.getId());
        documentUploadFile.setFile(fileName);
        documentDao.updateFile(documentUploadFile);
    }
}
