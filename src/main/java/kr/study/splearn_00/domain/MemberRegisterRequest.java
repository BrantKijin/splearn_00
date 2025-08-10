package kr.study.splearn_00.domain;

public record MemberRegisterRequest(
	String email,
	String nickname,
	String password
) {
}
