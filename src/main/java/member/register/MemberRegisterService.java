package member.register;

import member.MemberDao;
import member.MemberDaoInterface;
import member.exception.AlreadyExistingMemberException;
import member.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

public class MemberRegisterService implements MemberRegisterServiceInterface {
	@Autowired
	private MemberDaoInterface memberDao;

	@Override
	public void regist(RegisterRequest req) {
		Member member = memberDao.selectByEmail(req.getEmail());
		if (member != null) {
			throw new AlreadyExistingMemberException("dup email " + req.getEmail());
		}
		Member newMember = new Member(
				req.getEmail(), req.getPassword(), req.getName(),
				new Date(),req.getGroupcode(),req.getLevel());

		memberDao.insert(newMember);
	}
}
