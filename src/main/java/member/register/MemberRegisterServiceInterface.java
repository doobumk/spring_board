package member.register;

import org.springframework.transaction.annotation.Transactional;

/**
 * Created by User on 2017-05-14.
 */
public interface MemberRegisterServiceInterface {
    void regist(RegisterRequest req);
}
