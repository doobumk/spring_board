package member.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by User on 2017-05-29.
 */
public class AdminCheckInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false); //세션이 존재하면 새로운 세션을 만들지 않고 null을 반환한다.
        Object level = session.getAttribute("member.level");
        if(session.getAttribute("member.level") == null){
        }
        else if(!level.equals(4)){
            session.invalidate();
        }
        else if(level.equals(4)){
            System.out.println("잘된다");
            return true;
        }
        response.sendRedirect(request.getContextPath()+ "/member/login");
        return false; //컨트롤러 실행안함
    }
}
