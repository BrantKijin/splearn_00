package kr.study.splearn_00;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import kr.study.splearn_00.domain.Member;
import kr.study.splearn_00.domain.MemberCreateRequest;
import kr.study.splearn_00.domain.MemberStatus;
import kr.study.splearn_00.domain.PasswordEncoder;

@SpringBootTest
class Splearn00ApplicationTests {

	Member member;
	PasswordEncoder passwordEncoder;

	@BeforeEach
	void setUp() throws Exception {
		this.passwordEncoder = new PasswordEncoder() {
			@Override
			public String encode(String password) {
				return password.toUpperCase();
			}

			@Override
			public boolean matches(String password, String encodedPassword) {
				return encode(password).equals(passwordEncoder);
			}
		};
		MemberCreateRequest createRequest = new MemberCreateRequest("aa","bb","cc");
		member = Member.create(createRequest, passwordEncoder
		);
	}

	@Test
	void contextLoads() {

		Assertions.assertThat(member).isNotNull();
		Assertions.assertThat(member.getStatus()).isEqualTo(MemberStatus.PENDING);
	}

	@Test
	void activate() {

		member.activate();

		Assertions.assertThat(member.getStatus()).isEqualTo(MemberStatus.ACTIVE);
	}

	@Test
	void verifyPassword(){
		Assertions.assertThat(member.verifyPassword("secret",passwordEncoder)).isTrue();
	}

}
