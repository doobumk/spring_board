package member.sort;

import member.Member;

/**
 * Created by User on 2017-05-18.
 */
public interface MemberDetailByIdServiceInterface {
    Member selectById(Long id);
}
