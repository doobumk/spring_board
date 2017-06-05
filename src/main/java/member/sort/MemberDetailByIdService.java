package member.sort;

import member.Member;
import member.MemberDaoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by User on 2017-05-18.
 */
public class MemberDetailByIdService implements MemberDetailByIdServiceInterface {

    @Autowired
    private MemberDaoInterface memberDaoInterface;

    @Override
    //@Transactional
    public Member selectById(Long id) {
        return memberDaoInterface.selectById(id);
    }
}
