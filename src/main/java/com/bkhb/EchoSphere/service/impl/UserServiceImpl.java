package com.bkhb.EchoSphere.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bkhb.EchoSphere.entity.User;
import com.bkhb.EchoSphere.mapper.UserMapper;
import com.bkhb.EchoSphere.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 系统用户 服务实现类
 * </p>
 *
 * @author bkhb
 * @since 2024-03-26 00:57:57
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    private final UserMapper userMapper;

    @Override
    public User getUser(Long userId) {
        return userMapper.getUser(userId);
    }

    /**
     * 通过邮箱或者用户名获取用户信息
     * @param email
     * @return
     */
    @Override
    public User getUserByUsernameOrEmail(String email) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, email).or().eq(User::getEmail, email);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public User getUserInfo() {
        return JSONUtil.toBean(JSONUtil.toJsonStr(StpUtil.getExtra("user")), User.class);
    }

    @Override
    public List<User> getUserList() {
        return userMapper.selectList(null);
    }
}
