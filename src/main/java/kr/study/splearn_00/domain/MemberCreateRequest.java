package kr.study.splearn_00.domain;

public record MemberCreateRequest(
	String email,
	String nickname,
	String password
) {
}
