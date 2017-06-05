package member.login;

import member.MemberDaoInterface;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by User on 2017-05-14.
 */
public interface AuthServiceInterface {

    AuthInfo authenticate(String email, String password);
}
