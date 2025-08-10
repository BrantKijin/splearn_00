package kr.study.splearn_00.application.required;

import kr.study.splearn_00.shared.Email;

/**
 * 이메일을 발송한다
 */
public interface EmailSender {
	void send(Email email,String subject, String body);
}
