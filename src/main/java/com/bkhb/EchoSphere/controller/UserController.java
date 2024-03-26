package com.bkhb.EchoSphere.controller;

import com.bkhb.EchoSphere.entity.User;
import com.bkhb.EchoSphere.result.ResultWrapper;
import com.bkhb.EchoSphere.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping
    public ResultWrapper<User> getUser() {
        return ResultWrapper.success(userService.getUser(1L));
    }
}
