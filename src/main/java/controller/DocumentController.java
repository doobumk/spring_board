package controller;

import comment.sort.CommentListCommand;
import comment.sort.CommentListServiceInterface;
import document.Document;
import document.DocumentUploadFile;
import document.approval.DocumentApprovalService;
import document.approval.DocumentApprovalServiceInterface;
import document.delete.DocumentDeleteService;
import document.delete.DocumentDeleteServiceInterface;
import document.read.DocumentReadService;
import document.read.DocumentReadServiceInterface;
import document.reject.DocumentRejectService;
import document.reject.DocumentRejectServiceInterface;
import document.update.DocumentUpdateService;
import document.update.DocumentUpdateServiceInterface;
import document.write.DocumentWriteCommand;
import document.write.DocumentWriteService;
import document.write.DocumentWriteServiceInterface;
import document.write.DocumentWriteValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2017-05-02.
 */
@Component
@Controller
@SessionAttributes(value = {"document","commentList"})  //document라는 이름으로 저장되는 모델객체를 session에 저장
public class DocumentController {

    @Autowired
    private DocumentWriteServiceInterface documentWriteService;
    @Autowired
    private DocumentReadServiceInterface documentReadService;
    @Autowired
    private DocumentDeleteServiceInterface documentDeleteService;
    @Autowired
    private DocumentUpdateServiceInterface documentUpdateService;
    @Autowired
    private DocumentApprovalServiceInterface documentApprovalService;
    @Autowired
    private DocumentRejectServiceInterface documentRejectService;
    @Autowired
    private CommentListServiceInterface commentListService;



    @ModelAttribute
    public Model typeList(Model model){
        List<String> type = new ArrayList<>();
        type.add("제안서");
        type.add("보고서");
        type.add("결산서");
        type.add("홍보물");
        return model.addAttribute("type",type);
    }

    @RequestMapping(value = "/document/writeForm")
    public ModelAndView writeForm(ModelAndView mvn){
        mvn.addObject("documentWriteCommand",new DocumentWriteCommand());
        mvn.setViewName("document/form/documentForm");
        return mvn;
    }

    @RequestMapping(value = "/document/write", method = RequestMethod.POST)
    public String writeDocument(Model model,DocumentWriteCommand documentWriteCommand,Errors errors, SessionStatus sessionStatus ) throws IOException {
      new DocumentWriteValidator().validate(documentWriteCommand,errors);
        if(errors.hasErrors()){
            System.out.println("write에러");
            sessionStatus.setComplete();
            return "document/form/documentForm";
        }
        System.out.println("write에러없음");
        DocumentUploadFile uploadFile = new DocumentUploadFile();
        documentWriteService.write(documentWriteCommand,uploadFile);
        model.addAttribute("id",documentWriteCommand.getId());

        return "redirect:/document/read/{id}";
    }
    @RequestMapping(value = "/document/read/{id}")
    public String readDocument(@PathVariable("id") int id,CommentListCommand commentListCommand,Model model){
        commentListCommand.setId(id);
        Document document = documentReadService.read(id);
        String uploadFile = documentReadService.getUploadFile(id);
        model.addAttribute("document",document);
        model.addAttribute("file",uploadFile);
        model.addAttribute("commentList",commentListService.list(commentListCommand));

        return "document/read/documentRead";
    }

    @RequestMapping(value = "/document/delete")
    public String deleteDocument(Document document,DocumentUploadFile documentUploadFile){
        documentDeleteService.delete(document,documentUploadFile);
        return "redirect:/document/listAll";
    }

    @RequestMapping(value = "/document/update", method = RequestMethod.GET)
    public String updateForm(){
        return "document/form/documentUpdateForm";
    }

    @RequestMapping(value = "/document/update", method = RequestMethod.POST)
    public String updateDocument(@ModelAttribute("document") Document document,DocumentUploadFile uploadFile,MultipartFile multipartFile){
        documentUpdateService.update(document,uploadFile,multipartFile);
        return "redirect:/document/listAll";
    }

    @RequestMapping(value = "/document/approval")
    public String approvalDocument(Document document){
        documentApprovalService.approval(document);
        return "redirect:/document/listAll";
    }
    @RequestMapping(value = "document/reject")
    public String reject(Document document){
        documentRejectService.reject(document);
        return "redirect:/document/listAll";
    }
}
