package controller;

import member.register.MemberRegisterAcceptServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by User on 2017-05-27.
 */
@Component
@Controller
public class AdminController {
    @Autowired
    private MemberRegisterAcceptServiceInterface memberRegisterAcceptService;

    @RequestMapping(value = "/member/accept/{id}")
    public String regAccept(@PathVariable int id){
        memberRegisterAcceptService.acceptReg(id);
        return null;
    }
}
