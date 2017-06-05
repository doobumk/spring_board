package member.login;

import common.CheckLoginState;
import member.MemberDao;
import member.MemberDaoInterface;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import member.exception.IdPasswordNotMatchingException;
import member.Member;
import org.springframework.stereotype.Service;

/**
 * Created by User on 2017-04-29.
 */
public class AuthService implements AuthServiceInterface {
    @Autowired
    private MemberDaoInterface memberDao;

    @Override
    public AuthInfo authenticate(String email, String password){
        Member member = memberDao.selectByEmail(email);
        if(member == null){
            throw new IdPasswordNotMatchingException();
        }
        if(!member.matchPassword(password)){
            throw new IdPasswordNotMatchingException();
        }
        AuthInfo authInfo = new AuthInfo(member.getId(),member.getLevel(),member.getEmail(),member.getName());
        return authInfo;
    }

}
