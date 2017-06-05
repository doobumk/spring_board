package document.download;


import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by User on 2017-05-21.
 */
public class FileDownloadView extends AbstractView{
    public FileDownloadView() {
        setContentType("application/download; charset=utf-8");
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> map, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        File file = (File)map.get("downloadFile");
        System.out.println("View에서 path"+file.getPath());
        System.out.println("View에서 이름"+file.getName());
        httpServletResponse.setContentType(getContentType());
        System.out.println("getContentType"+getContentType());
        httpServletResponse.setContentLength((int)file.length());

        String userAgent = httpServletRequest.getHeader("User-Agent");
        boolean checkBrowser = userAgent.indexOf("MSIE") > -1;
        String fileName = null;
        if(checkBrowser){
            fileName = URLEncoder.encode(file.getName(),"utf-8");
        }else{
            fileName = new String(file.getName().getBytes("utf-8"),"iso-8859-1");
        }

        httpServletResponse.setHeader("Content-Disposition","attachment; filename=\""+fileName+"\";");
        httpServletResponse.setHeader("Content-Transfer-Encoding","binary");
        OutputStream outputStream = httpServletResponse.getOutputStream();
        FileInputStream fileInputStream = null;
        System.out.println("View에서의 fileName"+fileName);
        try{
            fileInputStream = new FileInputStream(file);
            FileCopyUtils.copy(fileInputStream,outputStream);
        }finally{
            if(fileInputStream != null)
                try{
                fileInputStream.close();
                }catch (IOException e){
                }
        }
        outputStream.flush();
    }
}
