package document;

import document.search.DocumentSearchCommand;
import document.sort.DocumentListCommand;

import java.util.List;

/**
 * Created by User on 2017-05-14.
 */
public interface DocumentDaoInterface {
    void insert(Document document);

    void reject(Document document);

    void approval(Document document);

    void update(Document document);
    void updateFile(DocumentUploadFile documentUploadFile);

    void delete(Document document);

    void uploadFile(DocumentUploadFile documentUploadFile);
    void deleteFile(DocumentUploadFile documentUploadFile);

   // void downloadFile(DocumentUploadFile documentUploadFile);

    int count();

    Document getBoard(int id);

    String getUploadFile(int id);

    List<Document> getDocumentList(DocumentListCommand documentListCommand);
   // List<Document> getDocumentListByTitle(DocumentListCommand documentListCommand, DocumentSearchCommand documentSearchCommand);
}
