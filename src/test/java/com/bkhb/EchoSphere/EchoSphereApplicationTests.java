package com.bkhb.EchoSphere;

import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.StpUtil;
import com.bkhb.EchoSphere.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EchoSphereApplicationTests {
	@Autowired
	private IUserService userService;

	@Test
	void contextLoads() {
		String pw_hash = BCrypt.hashpw("123456", BCrypt.gensalt());
		System.out.println(pw_hash);
	}

}
