package controller;

import member.edit.ChangePasswordServiceInterface;
import member.edit.ChangePwdCommand;
import member.edit.ChangePwdCommandValidator;
import member.login.AuthInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import member.edit.ChangePasswordService;
import member.exception.IdPasswordNotMatchingException;

import javax.servlet.http.HttpSession;

/**
 * Created by User on 2017-04-29.
 */
@Component
@Controller
public class ChangePwdController {

    @Autowired
    private ChangePasswordServiceInterface changePasswordService;



    @RequestMapping(value = "/member/edit/changePassword",method = RequestMethod.GET)
    public String form(Model model){
        model.addAttribute("changePwdCommand",new ChangePwdCommand()); //데이터 전송
        return "edit/changePwdForm";
    }

    @RequestMapping(value = "/member/edit/changePassword",method = RequestMethod.POST)
    public String submit(ChangePwdCommand changePwdCommand, Errors errors, HttpSession session){
        new ChangePwdCommandValidator().validate(changePwdCommand,errors);
        if(errors.hasErrors()){
            return "edit/changePwdForm";
        }
        AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
        try{
            changePasswordService.changePassword(authInfo.getEmail(),changePwdCommand.getOldPassword(),changePwdCommand.getNewPassword());
            return "edit/changePwdSuccess";
        }catch (IdPasswordNotMatchingException e){
            errors.rejectValue("oldPassword","notMatch");
            return "edit/changePwdForm";
        }
    }
}
