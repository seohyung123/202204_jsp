package service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.Test;

class JunitTest {

	private LoginService loginservice = new LoginService();
	@Test
	void testLogin() {
		// 로그인 테스트 
		Map<String, Object> msg = loginservice.loginCheck("java","1111");
		// 로그인성공, 회원 미존재 , 비밀번호 불일치 
		System.out.println(msg);
	}

}
