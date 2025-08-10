package kr.study.splearn_00.application.required;

import org.springframework.data.repository.Repository;

import kr.study.splearn_00.domain.Member;

public interface MemberRepository extends Repository<Member, Long> {
	Member save(Member member);
}
