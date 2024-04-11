package com.bkhb.EchoSphere.controller.admin;


import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.bean.BeanUtil;
import com.bkhb.EchoSphere.dto.PageDto;
import com.bkhb.EchoSphere.dto.UserDto;
import com.bkhb.EchoSphere.entity.User;
import com.bkhb.EchoSphere.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "后台用户管理", description = "后台用户管理")
@RestController
@RequestMapping("/sys/user")
@RequiredArgsConstructor
public class SysUserController {
    private final IUserService userService;

    @Operation(summary = "分页查询用户信息", description = "分页查询用户信息")
    @SaCheckPermission("user:list")
    @GetMapping("list")
    public PageDto<User> list(@RequestParam(defaultValue = "1") Integer current,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String username) {
        return userService.getUserPageDtoList(current, pageSize, username);
    }

    @Operation(summary = "更新用户", description = "更新用户")
    @SaCheckPermission("user:edit")
    @PutMapping("edit")
    public UserDto edit(@RequestBody User user) {
        return userService.updateUserByUserId(new UserDto(user));
    }

    @Operation(summary = "新增用户", description = "新增用户")
    @SaCheckPermission("user:add")
    @PostMapping("add")
    public User add(@RequestBody User user) {
        return userService.addUser(user);
    }

    @Operation(summary = "删除用户", description = "删除用户")
    @SaCheckPermission("user:del")
    @DeleteMapping("del/{userId}")
    public void del(@PathVariable Long userId) {
        userService.delUserByUserId(userId);
    }
}
