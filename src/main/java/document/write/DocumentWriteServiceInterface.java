package document.write;


import document.DocumentUploadFile;

import java.io.IOException;

/**
 * Created by User on 2017-05-14.
 */
public interface DocumentWriteServiceInterface {
    void write(DocumentWriteCommand req,DocumentUploadFile documentUploadFile) throws IOException;
}
