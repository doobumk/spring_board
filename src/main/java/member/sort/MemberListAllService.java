package member.sort;

import member.Member;
import member.MemberDaoInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by User on 2017-05-29.
 */
public class MemberListAllService implements MemberListAllServiceInterface {
    @Autowired
    private MemberDaoInterface memberDao;
    @Override
    public List<Member> selectAll() {
        return memberDao.selectAll();
    }
}
