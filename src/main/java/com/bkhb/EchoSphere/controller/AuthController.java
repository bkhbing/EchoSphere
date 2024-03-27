package com.bkhb.EchoSphere.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.bkhb.EchoSphere.api.AuthenticationRequest;
import com.bkhb.EchoSphere.entity.User;
import com.bkhb.EchoSphere.service.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户认证
 *
 * @author bkhb
 * @version 1.0
 * @date 2024/3/26 23:43
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final IAuthService authenticate;

    /**
     * 注册方法
     */
    @SaIgnore
    @PostMapping("/register")
    public void register(@RequestBody User user) {
        authenticate.register(user);
    }

    /**
     * 鉴权(登录方法)
     */
    @PostMapping("/login")
    public SaTokenInfo login(@RequestBody AuthenticationRequest request) {
        return authenticate.login(request);
    }

    /**
     * 登出
     */
    @PostMapping("/logout")
    public void logout() {
        authenticate.logout();
    }

    /**
     * 注销
     */
    @PostMapping("/revoke")
    public String revoke() {
        return "123";
    }
}
