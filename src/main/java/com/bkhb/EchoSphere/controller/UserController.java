package com.bkhb.EchoSphere.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.SaTokenInfo;
import com.bkhb.EchoSphere.dto.UserDto;
import com.bkhb.EchoSphere.entity.User;
import com.bkhb.EchoSphere.execption.BadRequestException;
import com.bkhb.EchoSphere.execption.EntityExistException;
import com.bkhb.EchoSphere.result.ResultWrapper;
import com.bkhb.EchoSphere.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 系统用户 前端控制器
 * </p>
 *
 * @author bkhb
 * @since 2024-03-26 00:57:57
 */
@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    final private IUserService userService;

    /**
     * 登陆接口
     */
    @PostMapping("login")
    public SaTokenInfo login(@RequestBody UserDto userDto) {
        return userService.login(userDto);
    }

    /**
     * 注销登录
     */
    @PostMapping("logout")
    public void logout() {
        userService.logout();
    }

    /**
     * 注册
     */
    @SaIgnore
    @PostMapping("register")
    public void register(@RequestBody UserDto userDto) {
        userService.register(userDto);
    }

    /**
     * 获取当前用户信息
     */
    @GetMapping("info")
    public UserDto userInfo() {
        return userService.getUserInfo();
    }

    /**
     * 获取所有用户信息
     */
    @SaCheckPermission("user:list")
    @GetMapping("list")
    public List<UserDto> list() {
        return userService.getUserList();
    }
}
