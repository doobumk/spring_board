package member.sort;

import member.Member;

import java.util.List;

/**
 * Created by User on 2017-05-18.
 */
public interface MemberListByRegdateServiceInterface {
    List<Member> selectByRegdate(MemberListCommandByRegdate memberListCommandByRegdate);
}
