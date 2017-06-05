package member;

import member.exception.IdPasswordNotMatchingException;

import java.util.Date;
/*사원테이블
		1.id(autoIncrement)
		2.email
		3.pw
		4.가입날짜
		5.부서코드(1/2)
		6.직급코드(1/2/3/4)*/

public class Member {

	private Long id;
	private String email;
	private String password;
	private String name;
	private Date registerDate;
	private String groupcode;
	private int level;
	private int regaccept;

	public Member(String email, String password, String name, Date registerDate, String groupcode, int level) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.registerDate = registerDate;
		this.groupcode = groupcode;
		this.level = level;
	}

	public int getRegaccept() {
		return regaccept;
	}

	public void setRegaccept(int regaccept) {
		this.regaccept = regaccept;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public void setGroupcode(String groupcode) {
		this.groupcode = groupcode;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getGroupcode() {
		return groupcode;
	}

	public int getLevel() {
		return level;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void changePassword(String oldPassword, String newPassword) {
		if (!password.equals(oldPassword))
			throw new IdPasswordNotMatchingException();
		this.password = newPassword;
	}

	public boolean matchPassword(String password){
		System.out.println(this.password);
		System.out.println(password);
		return this.password.equals(password);
	}

}
