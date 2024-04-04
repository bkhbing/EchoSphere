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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@Tag(name = "用户管理", description = "用户管理")
public class UserController {
    final private IUserService userService;

    /**
     * 登陆接口
     */
    @Operation(summary = "登陆接口", description = "登陆接口，只需要填写用户名和密码即可")
    @PostMapping("login")
    public SaTokenInfo login(@RequestBody UserDto userDto) {
        return userService.login(userDto);
    }

    /**
     * 注销登录
     */
    @Operation(summary = "注销登录", description = "注销登录,只需要在请求头添加token即可")
    @PostMapping("logout")
    public void logout() {
        userService.logout();
    }

    /**
     * 注册
     */
    @Operation(summary = "注册", description = "注册，只需要填写用户名和密码即可")
    @SaIgnore
    @PostMapping("register")
    public void register(@RequestBody UserDto userDto) {
        userService.register(userDto);
    }

    /**
     * 获取当前用户信息
     */
    @Operation(summary = "获取当前用户信息", description = "获取当前用户信息")
    @GetMapping("info")
    public UserDto userInfo() {
        return userService.getUserInfo();
    }

    @Operation(summary = "更新当前用户信息", description = "更新当前用户信息")
    @PutMapping("info")
    public UserDto updateUserInfo(@RequestBody UserDto userDto) {
        return userService.updateUserInfoByUserId(userDto);
    }

    @Operation(summary = "更新用户信息", description = "更新用户信息")
    @SaCheckPermission("user:edit")
    @PutMapping()
    public UserDto updateUser(@RequestBody UserDto userDto) {
        return userService.updateUserByUserId(userDto);
    }

    /**
     * 获取所有用户信息
     */
    @Operation(summary = "获取所有用户信息", description = "获取所有用户信息")
    @SaCheckPermission("user:list")
    @GetMapping("list")
    public List<UserDto> list() {
        return userService.getUserList();
    }
}
