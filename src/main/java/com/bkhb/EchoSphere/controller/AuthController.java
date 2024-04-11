package com.bkhb.EchoSphere.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.SaTokenInfo;
import com.bkhb.EchoSphere.dto.UserDto;
import com.bkhb.EchoSphere.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 登陆认证表 前端控制器
 * </p>
 *
 * @author bkhb
 * @since 2024-04-03 16:52:04
 */
@Tag(name = "登陆认证", description = "登陆认证")
@RestController
@RequestMapping
@RequiredArgsConstructor
public class AuthController {
    final private IUserService userService;

    @Operation(summary = "登陆接口", description = "登陆接口，只需要填写用户名和密码即可")
    @SaIgnore
    @PostMapping("login")
    public SaTokenInfo login(@RequestBody UserDto userDto) {
        return userService.login(userDto);
    }

    @Operation(summary = "注销登录", description = "注销登录,只需要在请求头添加token即可")
    @DeleteMapping("logout")
    public void logout() {
        userService.logout();
    }

    @Operation(summary = "注册", description = "注册，只需要填写用户名和密码即可")
    @SaIgnore
    @PostMapping("register")
    public void register(@RequestBody UserDto userDto) {
        userService.register(userDto);
    }
}
