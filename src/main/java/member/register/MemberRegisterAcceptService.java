package member.register;

import member.MemberDaoInterface;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by User on 2017-05-27.
 */
public class MemberRegisterAcceptService implements MemberRegisterAcceptServiceInterface{
    @Autowired
    private MemberDaoInterface memberDao;
    @Override
    public void acceptReg(int id) {
        memberDao.regAccept(id);
    }
}
