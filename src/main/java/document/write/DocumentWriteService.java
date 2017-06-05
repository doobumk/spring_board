package document.write;

import document.Document;
import document.DocumentDao;
import document.DocumentDaoInterface;
import document.DocumentUploadFile;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by User on 2017-05-03.
 */
public class DocumentWriteService implements DocumentWriteServiceInterface, ApplicationContextAware {

    @Autowired
    private DocumentDaoInterface documentDao;

    private WebApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = (WebApplicationContext) applicationContext;
    }

    @Override
    public void write(DocumentWriteCommand req, DocumentUploadFile documentUploadFile) throws IOException{
        Document newDocument = new Document(req.getWriter_level(),req.getWriter_name(),req.getTitle(),req.getContent(),req.getType(),1,req.getApproval_level(),new Date());
        documentDao.insert(newDocument);
        req.setId(newDocument.getId());
        String fileDirectory = context.getServletContext().getRealPath("/");
        String storage = "WEB-INF/storage/";
        Path url = Paths.get(fileDirectory+storage);
        if(Files.notExists(url)){
            Files.createDirectories(url);
        }

        MultipartFile multipartFile = req.getMultipartFile();
        System.out.println(multipartFile.getOriginalFilename());
        if(!multipartFile.isEmpty()){
            String fileName = multipartFile.getOriginalFilename();
            try {
                multipartFile.transferTo(new File(fileDirectory+storage+fileName));
                System.out.println(fileDirectory+storage+fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        documentUploadFile.setFile(multipartFile.getOriginalFilename());
        documentUploadFile.setId(newDocument.getId());
        documentDao.uploadFile(documentUploadFile);
    }


}
