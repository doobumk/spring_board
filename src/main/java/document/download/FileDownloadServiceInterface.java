package document.download;

import java.io.File;

/**
 * Created by User on 2017-05-21.
 */
public interface FileDownloadServiceInterface {
    File downloadFunction(FileDownloadCommand fileDownloadCommand);

}
