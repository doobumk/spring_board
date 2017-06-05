package controller;


import member.MemberDao;
import member.MemberDaoInterface;
import member.sort.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import member.Member;
import member.exception.MemberNotFoundException;

import java.util.List;

/**
 * Created by User on 2017-04-30.
 */
@Component
@Controller
public class MemberListController {

    @Autowired
    private MemberListByRegdateServiceInterface memberListByRegdateService;
    @Autowired
    private MemberDetailByIdServiceInterface memberDetailByIdService;
    @Autowired
    private MemberListAllServiceInterface memberListAllService;

    @RequestMapping("/member/list")
    public String memberList(Model model){
        model.addAttribute("memberList",memberListAllService.selectAll());
        return "member/memberList";
    }


    @RequestMapping("/member/list/regdate")
    public String memberListByRegdate(MemberListCommandByRegdate memberListCommandByRegdate, Errors errors, Model model){
        if(errors.hasErrors()){
            return "member/memberListByRegdate";
        }
        if(memberListCommandByRegdate.getFrom() !=null && memberListCommandByRegdate.getTo() != null){
            List<Member> memberList = memberListByRegdateService.selectByRegdate(memberListCommandByRegdate);
            model.addAttribute("member",memberList);
        }
        return "member/memberListByRegdate";
    }

    @RequestMapping(value = "/member/list/id/{id}")
    public String memberDetail(@PathVariable("id") Long id, Model model){
        Member member = memberDetailByIdService.selectById(id);

        model.addAttribute("member",member);
        return "member/memberDetail";
    }
    @RequestMapping("/member/list/id/search")
    public String memberDetailById(Long id,Model model){
        try{
            Member member = memberDetailByIdService.selectById(id);
            model.addAttribute("member",member);
        }catch (Exception e){
            throw new MemberNotFoundException();
        }
        return "member/memberDetailById";
    }
    @ExceptionHandler(MemberNotFoundException.class)
    public String HandleMemberNotFoundException(){
        return "exception/noMemberException";
    }
}
