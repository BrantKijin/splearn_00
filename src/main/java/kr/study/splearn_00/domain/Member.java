package kr.study.splearn_00.domain;

public class Member {
	String email;
	String ninckname;
	String passwordHash;
	MemberStatus status;

	public Member(String email, String ninckname, String passwordHash, MemberStatus status) {
		this.email = email;
		this.ninckname = ninckname;
		this.passwordHash = passwordHash;
		this.status = status;
	}
}
