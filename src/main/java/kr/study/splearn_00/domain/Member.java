package kr.study.splearn_00.domain;

import java.util.Objects;
import java.util.regex.Pattern;

import org.springframework.util.Assert;

import kr.study.splearn_00.shared.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter

@AllArgsConstructor
public class Member {
	private Email email;
	String ninckname;
	String passwordHash;
	MemberStatus status;

	private Member() {

	}

	private Member(String email, String ninckname, String passwordHash) {
		this.email = new Email(email);
		this.ninckname = ninckname;
		this.passwordHash = passwordHash;
		this.status = MemberStatus.PENDING;
	}

	public void activate() {

		Assert.state(this.status == MemberStatus.PENDING, "PENDING 상태가 아닙니다");

		this.status = MemberStatus.ACTIVE;
	}

	// public static Member create(String email, String ninckname, String password, PasswordEncoder passwordEncoder) {
	// 	return new Member(email, ninckname, passwordEncoder.encode(password));
	// }
	public static Member register(MemberRegisterRequest createRequest, PasswordEncoder passwordEncoder) {
		Member member = new Member();

		Pattern EMAIL_PATTERN =
			Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");

		if (!EMAIL_PATTERN.matcher(createRequest.email()).matches()) {
			throw new IllegalArgumentException("Invalid email format:" + createRequest.email());
		}

		member.email = new Email(createRequest.email());
		member.ninckname = Objects.requireNonNull(createRequest.nickname());
		member.status = MemberStatus.PENDING;
		member.passwordHash = Objects.requireNonNull(createRequest.password());

		return member;

		//return new Member(createRequest.email(),createRequest.nickname(),passwordEncoder.encode(createRequest.password()));
	}

	public boolean verifyPassword(String password, PasswordEncoder passwordEncoder) {
		return passwordEncoder.matches(password, this.passwordHash);
	}

	public void changeNickname(String ninckname) {
		this.ninckname = ninckname;
	}

	public void changePassword(String password, PasswordEncoder passwordEncoder) {
		this.passwordHash = passwordEncoder.encode(password);
	}

	public void deactivate() {
		this.status = MemberStatus.DEACTIVATED;
	}

	public boolean isActive() {
		return this.status == MemberStatus.ACTIVE;
	}
}
