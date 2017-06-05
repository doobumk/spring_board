package document.download;

import document.DocumentDaoInterface;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;

/**
 * Created by User on 2017-05-21.
 */
public class FileDownloadService implements FileDownloadServiceInterface, ApplicationContextAware {
    private WebApplicationContext context;
    @Autowired
    private DocumentDaoInterface documentDao;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = (WebApplicationContext)applicationContext;
    }

    @Override
    public File downloadFunction(FileDownloadCommand fileDownloadCommand) {
        fileDownloadCommand.setUrl(context.getServletContext().getRealPath("/")+"WEB-INF/storage/");
        fileDownloadCommand.setFileName(getFileName(fileDownloadCommand.getId()));
        String path = fileDownloadCommand.getUrl()+fileDownloadCommand.getFileName();
        System.out.println(path);
        return new File(path);
    }

    private String getFileName(int id){
        return documentDao.getUploadFile(id);
    }
}
