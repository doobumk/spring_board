package common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by User on 2017-05-29.
 */
@Aspect
public class CheckAdminAuth {
    @Pointcut("execution(* member.sort.*.*(..))")
    public void checkAdmin(){} //admin Check

    @Before(value = "checkAdmin()")
    public boolean checkAuth(JoinPoint jp) {
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        for(Object object : jp.getArgs()){
            if(object instanceof HttpServletRequest){
                request = (HttpServletRequest)object;
            }
            if(object instanceof HttpServletResponse){
                response = (HttpServletResponse)object;
            }
        }
        try{
            HttpSession s = (HttpSession) RequestContextHolder
                    .currentRequestAttributes()
                    .resolveReference(RequestAttributes.REFERENCE_SESSION);

            System.out.println(s.getId());
            Object level = s.getAttribute("member.level");
            System.out.println("세션레벨:"+level);
            if(level == null){
                System.out.println("session NULL");
                response.sendRedirect(request.getContextPath()+ "/member/login");
                return false;
            }
        }catch (Exception e) {

        }
        return true;

    }

}
