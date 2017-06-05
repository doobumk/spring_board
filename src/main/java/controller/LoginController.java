package controller;

import member.login.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import member.exception.IdPasswordNotMatchingException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by User on 2017-04-29.
 */
@Component
@Controller
public class LoginController {

    @Autowired
    private AuthServiceInterface authService;


    @RequestMapping(value = "/member/login",method = RequestMethod.GET)
    public String form(LoginCommand loginCommand,@CookieValue(value = "EMAIL_REMEMBER",required = false) Cookie cookie){
        //model.addAttribute("loginCommand", new LoginCommand());
        if(cookie != null){
            loginCommand.setEmail(cookie.getValue());
            loginCommand.setRememberEmail(true);
        }
        return "login/loginForm";
    }

    @RequestMapping(value = "/member/login",method = RequestMethod.POST)
    public String submit(LoginCommand loginCommand, Errors errors, HttpSession session, HttpServletResponse response){
        new LoginCommandValidator().validate(loginCommand,errors);
        if(errors.hasErrors() == true){
            return "login/loginForm";
        }
        try{
            AuthInfo authInfo = authService.authenticate(loginCommand.getEmail(),loginCommand.getPassword()); //세션정보
            session.setAttribute("authInfo",authInfo);
            session.setAttribute("member.level",authInfo.getLevel());
            session.setAttribute("member.id",authInfo.getId());
            System.out.println(session.getAttribute("member.level"));
            Cookie emailCookie = new Cookie("EMAIL_REMEMBER",loginCommand.getEmail());
            emailCookie.setPath("/");
            if(loginCommand.isRememberEmail()){
                emailCookie.setMaxAge(60*60*24*30);
            }else{
                emailCookie.setMaxAge(0);
            }
            response.addCookie(emailCookie);
            return "login/loginSuccess";
        }catch (IdPasswordNotMatchingException e){
            errors.reject("idPasswordNotMatching");
        }
        return "login/loginForm";
    }
}
