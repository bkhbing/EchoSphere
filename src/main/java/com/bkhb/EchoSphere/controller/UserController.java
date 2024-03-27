package com.bkhb.EchoSphere.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import com.bkhb.EchoSphere.entity.User;
import com.bkhb.EchoSphere.execption.BadRequestException;
import com.bkhb.EchoSphere.execption.EntityExistException;
import com.bkhb.EchoSphere.result.ResultWrapper;
import com.bkhb.EchoSphere.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * 获取当前用户信息
     * @return
     */
    @SaCheckLogin
    @GetMapping("userInfo")
    public User userInfo() {
        return userService.getUserInfo();
    }

    /**
     * 获取所有用户信息
     */
    @SaCheckPermission("user:list")
    @GetMapping("list")
    public List<User> list() {
        return userService.getUserList();
    }
}
