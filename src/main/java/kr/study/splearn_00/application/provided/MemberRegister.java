package kr.study.splearn_00.application.provided;

import kr.study.splearn_00.domain.Member;
import kr.study.splearn_00.domain.MemberRegisterRequest;

/**
 * 회원의 등록과 관련된 기능을 제공한다
 */
public interface MemberRegister {
	Member register(MemberRegisterRequest registerRequest);
}
