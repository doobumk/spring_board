package controller;

import document.Document;
import document.DocumentDao;
import document.sort.DocumentListAllService;
import document.sort.DocumentListAllServiceInterface;
import document.sort.DocumentListCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by User on 2017-05-03.
 */
@Component
@Controller
public class DocumentListController {
    @ModelAttribute("conditionMap")
    public Map<String, String> searchConditionMap(){
        Map<String,String> conditionMap = new HashMap<>();
        conditionMap.put("제목","TITLE");
        conditionMap.put("내용","CONTENT");
        return conditionMap;
    }
    @Autowired
    private DocumentListAllServiceInterface documentListAllService;

    @RequestMapping(value = "document/listAll", method = RequestMethod.GET)
    public String documentList(Model model, DocumentListCommand documentListCommand){
        //id = documentListCommand.getPageNo();
        //documentListCommand.setPageNo(pageNo);
        System.out.println("1번 동작");
        if(documentListCommand.getPageNo() == 0){
            documentListCommand.setCurrentPage(1);
        }else documentListCommand.setCurrentPage(documentListCommand.getPageNo());
        System.out.println("CONTROLLER"+documentListCommand.toString());

        model.addAttribute("documentList",documentListAllService.listAll(documentListCommand));
        model.addAttribute("page",documentListCommand);

        return "document/sort/documentList";
    }

    //JSON타입으로 뿌리기
    @RequestMapping(value = "dataTransForm", method = RequestMethod.POST)
    @ResponseBody
    public List<Document> documentJSON(DocumentListCommand documentListCommand){
        List<Document> documentList = documentListAllService.listAll(documentListCommand);
        return documentList;
    }







}
