package com.bkhb.EchoSphere.service.impl;


import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.SaLoginConfig;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.bkhb.EchoSphere.api.AuthenticationRequest;
import com.bkhb.EchoSphere.entity.User;
import com.bkhb.EchoSphere.execption.BadRequestException;
import com.bkhb.EchoSphere.mapper.UserMapper;
import com.bkhb.EchoSphere.result.BaseResultCodeEnum;
import com.bkhb.EchoSphere.service.IAuthService;
import com.bkhb.EchoSphere.service.IMenuService;
import com.bkhb.EchoSphere.service.IRoleService;
import com.bkhb.EchoSphere.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * 登录
 *
 * @author bkhb
 * @version 1.0
 * @date 2024/3/27 2:04
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {
    private final IUserService userService;
    private final IMenuService menuService;
    private final IRoleService roleService;

    @Override
    public SaTokenInfo login(AuthenticationRequest request) {
        User user = userService.getUserByUsernameOrEmail(request.getEmail());
        if (user == null || !BCrypt.checkpw(request.getPassword(), user.getPassword())) {
            throw new BadRequestException(BaseResultCodeEnum.USERNAME_OR_PASSWORD_ERROR);
        }
        user.setPermissionList(menuService.getPermissionListByUserId(user.getUserId()));
        user.setRoleList(null);
        StpUtil.login(user.getUserId(), SaLoginConfig.setExtra("user", user));
        return StpUtil.getTokenInfo();
    }

    @Override
    public void logout() {
        StpUtil.logout();
    }
}
