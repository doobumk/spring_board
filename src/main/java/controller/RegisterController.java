package controller;


import member.register.MemberRegisterServiceInterface;
import member.register.RegisterRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import member.exception.AlreadyExistingMemberException;
import member.register.MemberRegisterService;
import member.register.RegisterRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2017-04-26.
 */
@Component
@Controller
@SessionAttributes(value = {"groupcode","level"})
public class RegisterController {

    @Autowired
    private MemberRegisterServiceInterface memberRegisterService;



    @RequestMapping(value = "/member/register/step1", method = RequestMethod.GET)
    public String handleStep1Get(){
        return "register/step1";
    }

    @RequestMapping(value = "/member/register/step2", method = RequestMethod.POST)
    public ModelAndView handleStep2(@RequestParam(value = "agree",defaultValue = "false")Boolean agree){
        ModelAndView modelAndView = new ModelAndView();
        if(agree == false){
            System.out.println("!agree 실행됨");
            modelAndView.setViewName("redirect:/member/register/step1");
        }
        if(agree == true) {
            System.out.println("agree 실행됨");
            List<String> groupcode = new ArrayList<>();
            groupcode.add("개발부");
            groupcode.add("영업부");
            List<String> level = new ArrayList<>();
            level.add("사원");
            level.add("대리");
            level.add("부장");
            level.add("사장");
            modelAndView.addObject("registerRequest", new RegisterRequest());
            modelAndView.addObject("groupcode", groupcode);
            modelAndView.addObject("level", level);
            modelAndView.setViewName("register/step2");
        }
        return modelAndView;
    }


    @RequestMapping(value = "/member/register/step2", method = RequestMethod.GET)
    public String handleStep2GET(){

            return "redirect:/member/register/step1";
    }
   @RequestMapping(value ="member/register/step3", method = RequestMethod.GET)
    public String handleStep3(){
        return "redirect:/member/register/step1";
    }

    @RequestMapping(value = "member/register/step3", method = RequestMethod.POST)
    //public String handleStep3(@ModelAttribute("formData") RegisterRequest registerRequest, Errors errors){
        public String handleStep3(RegisterRequest registerRequest, Errors errors){
        new RegisterRequestValidator().validate(registerRequest,errors);
        if(errors.hasErrors())
            return "register/step2";
        try{
            memberRegisterService.regist(registerRequest);
            return "register/step3";
        }catch (AlreadyExistingMemberException ex){
            errors.rejectValue("email","duplicate");
            return "register/step2";
        }
    }
}

