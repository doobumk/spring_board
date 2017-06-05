package member.edit;


import org.springframework.transaction.annotation.Transactional;

/**
 * Created by User on 2017-05-14.
 */
public interface ChangePasswordServiceInterface {
     void changePassword(String email, String oldPwd, String newPwd);
}
