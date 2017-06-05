package controller;

import document.download.FileDownloadCommand;
import document.download.FileDownloadServiceInterface;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * Created by User on 2017-05-21.
 */
@Controller
public class DownloadController{
    @Autowired
    private FileDownloadServiceInterface fileDownloadServiceInterface;


    @RequestMapping("/document/download")
    public ModelAndView downloadFunction(@RequestParam("id") int id,FileDownloadCommand fileDownloadCommand){
        fileDownloadCommand.setId(id);
        File file = fileDownloadServiceInterface.downloadFunction(fileDownloadCommand);
        System.out.println("컨트롤러에서의값"+fileDownloadCommand.getUrl());

        return new ModelAndView("fileDownloadView","downloadFile",file );
    }
}
