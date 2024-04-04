package com.bkhb.EchoSphere;

import cn.dev33.satoken.dao.SaTokenDao;
import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.json.JSONUtil;
import com.bkhb.EchoSphere.service.IPostService;
import com.bkhb.EchoSphere.service.IUserService;
import com.bkhb.EchoSphere.service.impl.EmailService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EchoSphereApplicationTests {
	@Autowired
	private IUserService userService;

	@Autowired
	private EmailService emailService;

	@Autowired
	private IPostService postService;

	@Test
	void contextLoads() {
	}

}
