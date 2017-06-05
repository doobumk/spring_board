package member.edit;

import member.Member;
import member.MemberDao;
import member.MemberDaoInterface;
import member.exception.MemberNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public class ChangePasswordService implements ChangePasswordServiceInterface {

	@Autowired
	private MemberDaoInterface memberDao;



//	@Transactional
	@Override
	public void changePassword(String email, String oldPwd, String newPwd) {
		Member member = memberDao.selectByEmail(email);
		if (member == null)
			throw new MemberNotFoundException();
		
		member.changePassword(oldPwd, newPwd);
		
		memberDao.changePassword(member);
	}
}
