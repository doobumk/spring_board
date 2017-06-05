package controller;

import comment.Comment;
import comment.delete.CommentChildDeleteCommand;
import comment.delete.CommentChildDeleteServiceInterface;
import comment.delete.CommentParentDeleteServiceInterface;
import comment.info.CommentInfoServiceInterface;
import comment.insert.CommentChildWriteServiceInterface;
import comment.insert.CommentWriteCommand;
import comment.insert.CommentWriteInvalidator;
import comment.insert.CommentWriteServiceInterface;
import comment.sort.CommentListCommand;
import comment.sort.CommentListServiceInterface;
import comment.update.CommentUpdateCommand;
import comment.update.CommentUpdateInvalidator;
import comment.update.CommentUpdateServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


/**
 * Created by User on 2017-05-24.
 */

@Controller
@SessionAttributes("comment")
public class CommentController {

    @Autowired
    private CommentWriteServiceInterface commentWriteService;
    @Autowired
    private CommentListServiceInterface commentListService;
    @Autowired
    private CommentInfoServiceInterface commentInfoService;
    @Autowired
    private CommentChildWriteServiceInterface commentChildWriteService;
    @Autowired
    private CommentParentDeleteServiceInterface commentParentDeleteService;
    @Autowired
    private CommentChildDeleteServiceInterface commentChildDeleteService;
    @Autowired
    private CommentUpdateServiceInterface commentUpdateService;

   @ModelAttribute
    public Model getCommentId(Model model,CommentWriteCommand commentWriteCommand){
        model.addAttribute("cmd",commentWriteCommand.getId());
        return model;
    }

    @RequestMapping(value = "comment/write")
    public String writeComment(CommentWriteCommand commentWriteCommand, Errors errors){
        new CommentWriteInvalidator().validate(commentWriteCommand,errors);
        if(errors.hasErrors()){
            System.out.println("에러");
            return "document/read/documentRead";
        }
        commentWriteService.write(commentWriteCommand);
        return "redirect:/document/read/{cmd}";
    }

    @RequestMapping(value = "comment/childWriteForm/{id}")
    public String writeChildForm(@PathVariable int id,Model model){
        Comment comment = commentInfoService.getInfo(id);
        model.addAttribute("comment",comment);
        return "/document/form/commentChildForm";
    }
    @RequestMapping(value = "comment/childWrite")
    public String writeChildComment(Model model, CommentWriteCommand commentWriteCommand,Errors errors){
        new CommentWriteInvalidator().validate(commentWriteCommand,errors);
        if(errors.hasErrors()){
            return "/document/form/commentChildForm";
        }
        System.out.println(commentWriteCommand.getDepth());
        System.out.println(commentWriteCommand.getParent_ID());
        System.out.println(commentWriteCommand.getContent());
        System.out.println(commentWriteCommand.getWriter());
        System.out.println(commentWriteCommand.getCommentId());
        commentWriteCommand.setDepth(commentWriteCommand.getDepth()+1);
        commentWriteCommand.setGroup_ID(commentWriteCommand.getGroup_ID());
        commentWriteCommand.setParent_ID(commentWriteCommand.getParent_ID());
        model.addAttribute("cmd",commentWriteCommand.getId());
        commentChildWriteService.writeChild(commentWriteCommand);
       return "redirect:/document/read/{cmd}";
    }
    @RequestMapping(value = "comment/delete/{groupId}")
    public String deleteParentComment(@PathVariable int groupId){
        commentParentDeleteService.deleteParent(groupId);
        return "redirect:/document/read/{cmd}";
    }

    @RequestMapping(value = "comment/deleteChild")
    public String deleteChildComment(CommentChildDeleteCommand childDeleteCommand){
        commentChildDeleteService.deleteChild(childDeleteCommand);
        return "redirect:/document/read/{cmd}";
    }

    @RequestMapping(value = "comment/updateForm/{id}")
    public String updateCommentForm(@PathVariable int id,Model model){
        Comment comment = commentInfoService.getInfo(id);
        model.addAttribute("comment",comment);
        return "document/form/commentUpdateForm";
    }

    @RequestMapping(value = "comment/update/{id}")
    public String changeContent(@PathVariable int id, CommentUpdateCommand commentUpdateCommand,Errors errors){
        new CommentUpdateInvalidator().validate(commentUpdateCommand,errors);
        if(errors.hasErrors()){
            return "document/form/commentUpdateForm";
        }
        commentUpdateCommand.setCommentId(id);
        commentUpdateService.changeContent(commentUpdateCommand);
        return "redirect:/document/read/{cmd}";
    }

}
