package document;

import document.search.DocumentSearchCommand;
import document.sort.DocumentListCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * Created by User on 2017-05-02.
 */
public class DocumentDao implements DocumentDaoInterface {

    private JdbcTemplate jdbcTemplate;
    private RowMapper<Document> documentRowMapper = new RowMapper<Document>() {
        @Override
        public Document mapRow(ResultSet resultSet, int i) throws SQLException {
            Document document = new Document(resultSet.getInt("WRITER_LEVEL"),
                    resultSet.getString("WRITER_NAME"),
                    resultSet.getString("TITLE"),
                    resultSet.getString("CONTENT"),
                    resultSet.getInt("TYPE"),
                    resultSet.getInt("STATE"),
                    resultSet.getInt("approval_level"),
                    resultSet.getTimestamp("createDate"));
            document.setId(resultSet.getInt("ID"));
            document.setApproval(resultSet.getString("APPROVAL"));
            document.setModifiedDate(resultSet.getTimestamp("MODIFIEDDATE"));
            document.setApprovalDate(resultSet.getTimestamp("APPROVALDATE"));
            return document;
        }
    };

    private RowMapper<String> fileRowMapper = new RowMapper<String>() {
        @Override
        public String mapRow(ResultSet resultSet, int i) throws SQLException {
            String fileName = resultSet.getString("FILE");
            return fileName;
        }
    };
    //SQL
    private static final String insert = "insert into document (WRITER_LEVEL, WRITER_NAME, TITLE, CONTENT, TYPE, STATE,APPROVAL_LEVEL, createDate) values (?, ?, ?, ?, ?, ?, ?,?)"; //APPROVAL DEFUALT = 미결재
    private static final String getDocumentList = "select * from document order by id desc limit ?,?";
    private static final String getDocumentListByTitle = "select * from document where title like ? order by id desc limit ?,?";
    private static final String getDocumentListByContent = "select * from document where content like ? order by id desc limit ?,?";
    private static final String selectCount = "select count(*) from document";
    private static final String delete = "delete from document where id=?";
    private static final String getDocument = "select * from document where id=?";
    private static final String update = "update document set title=?, content=?, modifiedDate=?, type=? where id=?";
    private static final String approval = "update document set state =? ,approval=? ,approval_level=? ,approvalDate=? where id=?";
    private static final String reject = "update document set state=4 where id=?";
    private static final String uploadFile = "insert into documentfile (id,file) values(?,?)";
    private static final String deleteFile = "delete from documentfile where id=?";
    private static final String updateFile = "update documentFile set file=? where id=?";
    private static final String getFile = "select file from documentfile where id=?";


    @Autowired
    public DocumentDao(DataSource dataSoruce) {
        this.jdbcTemplate = new JdbcTemplate(dataSoruce);
    }


    @Override
    public void insert(final Document document) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con)
                    throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(
                        insert,
                        new String[] {"ID"});
                pstmt.setInt(
                        1,  document.getWriter_level());
                pstmt.setString(2,  document.getWriter_name());
                pstmt.setString(3,  document.getTitle());
                pstmt.setString(4,document.getContent());
                pstmt.setInt(5, document.getType());
                pstmt.setInt(6, document.getState());
                pstmt.setInt(7,document.getApproval_level());
                pstmt.setTimestamp(8,
                        new Timestamp(document.getCreateDate().getTime()));
                return pstmt;
            }
        }, keyHolder);
        Number keyValue = keyHolder.getKey();
        document.setId(keyValue.intValue());
    }

    @Override
    public void uploadFile(DocumentUploadFile documentUploadFile) {
        jdbcTemplate.update(uploadFile,documentUploadFile.getId(),documentUploadFile.getFile());
    }

    @Override
    public void deleteFile(DocumentUploadFile documentUploadFile) {
        jdbcTemplate.update(deleteFile,documentUploadFile.getId());
    }

    @Override
    public void updateFile(DocumentUploadFile documentUploadFile) {
        jdbcTemplate.update(updateFile,documentUploadFile.getFile(),documentUploadFile.getId());
    }

    @Override
    public void reject(Document document){
        jdbcTemplate.update(reject,document.getId());
    }

    @Override
    public void approval(Document document){
        jdbcTemplate.update(approval,document.getState(),document.getApproval(),document.getApproval_level(),new Date(),document.getId());
    }

    @Override
    public void update(Document document){
        jdbcTemplate.update(update, document.getTitle(),document.getContent(),new Date(),document.getType(),document.getId());
    }

    @Override
    public void delete(Document document){
        jdbcTemplate.update(delete,document.getId());
    }


    @Override
    public int count() {
        Integer count = jdbcTemplate.queryForObject(selectCount, Integer.class);
        return count;
    }

    @Override
    public Document getBoard(int id){
        Document document1 = jdbcTemplate.queryForObject(getDocument,documentRowMapper,id);
        return document1;
    }


    @Override
    public String getUploadFile(int id) {
        String file = jdbcTemplate.queryForObject(getFile,fileRowMapper,id);
        System.out.println("dao ID"+id);
        return file;
    }

    @Override
    public List<Document> getDocumentList(DocumentListCommand documentListCommand){
       // DocumentSearchCommand documentSearchCommand = new DocumentSearchCommand();
        List<Document> documentList = new ArrayList<>();
        if(documentListCommand.getSearchCondition() != null) {
            if (documentListCommand.getSearchCondition().equals("TITLE") && documentListCommand.getSearchKeyword() != null) {
                System.out.println("TITLE 동작");
                documentList =
                        jdbcTemplate.query(getDocumentListByTitle,
                                documentRowMapper, "%" + documentListCommand.getSearchKeyword() + "%",(documentListCommand.getCurrentPage()-1)*documentListCommand.getSize(),documentListCommand.getSize());
            }
            if (documentListCommand.getSearchCondition().equals("CONTENT") && documentListCommand.getSearchKeyword() != null) {
                System.out.println("CONTENT 동작");

                documentList = jdbcTemplate.query(getDocumentListByContent,
                        documentRowMapper, "%" + documentListCommand.getSearchKeyword() + "%",(documentListCommand.getCurrentPage()-1)*documentListCommand.getSize(),documentListCommand.getSize());
            }
        }
        else documentList =  jdbcTemplate.query(getDocumentList, documentRowMapper,(documentListCommand.getCurrentPage()-1)*documentListCommand.getSize(),documentListCommand.getSize());
        return documentList;
    }
}
