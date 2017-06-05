package member.sort;

import member.Member;
import member.MemberDaoInterface;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by User on 2017-05-18.
 */
public class MemberListByRegdateService implements MemberListByRegdateServiceInterface {

    @Autowired
    private MemberDaoInterface memberDaoInterface;

    @Override
    public List<Member> selectByRegdate(MemberListCommandByRegdate memberListCommandByRegdate) {
       return  memberDaoInterface.selectByRegdate(memberListCommandByRegdate.getFrom(),memberListCommandByRegdate.getTo());
    }
}
