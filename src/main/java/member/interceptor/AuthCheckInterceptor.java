package member.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by User on 2017-04-30.
 */
public class AuthCheckInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false); //세션이 존재하면 새로운 세션을 만들지 않고 null을 반환한다.
        if(session != null){
            Object authInfo = session.getAttribute("authInfo");
            if(authInfo != null){

                return  true; //컨트롤러 실행
            }
        }
        response.sendRedirect(request.getContextPath()+ "/member/login");
        return false; //컨트롤러 실행안함
    }



}
