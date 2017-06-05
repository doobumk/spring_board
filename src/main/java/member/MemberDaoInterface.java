package member;

import member.register.RegisterRequest;

import java.util.Date;
import java.util.List;

/**
 * Created by User on 2017-05-14.
 */
public interface MemberDaoInterface {
    Member selectById(Long id);

    Member selectByEmail(String email);

    void insert(Member member);

    void changeGroupcode(Member member);

    void changePassword(Member member);

    void regAccept(int id);

    List<Member> selectAll();

    int count();

    List<Member> selectByRegdate(Date from, Date to);
}
