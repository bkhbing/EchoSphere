package com.bkhb.EchoSphere.service.impl;

import cn.dev33.satoken.dao.SaTokenDao;
import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.SaLoginConfig;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bkhb.EchoSphere.dto.PageDto;
import com.bkhb.EchoSphere.dto.UserDto;
import com.bkhb.EchoSphere.entity.User;
import com.bkhb.EchoSphere.execption.BadRequestException;
import com.bkhb.EchoSphere.execption.EntityExistException;
import com.bkhb.EchoSphere.mapper.UserMapper;
import com.bkhb.EchoSphere.result.BaseResultCodeEnum;
import com.bkhb.EchoSphere.service.IMenuService;
import com.bkhb.EchoSphere.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
    private final IMenuService menuService;

    @Override
    public User getUser(Long userId) {
        return userMapper.selectById(userId);
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
    public UserDto getUserInfo() {
        // 从Session中获取usr信息
        return (UserDto) StpUtil.getSession().get("userDto");
    }

    @Override
    public List<UserDto> getUserList() {
        return userMapper.selectList(null).stream().map(item -> {
            UserDto userDto = new UserDto(item);
            userDto.setPermissions(menuService.getPermissionListByUserId(item.getUserId()));
            return userDto;
        }).toList();
    }

    @Override
    public User addUser(User user) {
        // 判断用户名和邮箱是否唯一
        if (userMapper.selectCount(new LambdaQueryWrapper<User>().eq(User::getUsername, user.getUsername())) > 0
                || userMapper.selectCount(new LambdaQueryWrapper<User>().eq(User::getEmail, user.getEmail())) > 0) {
            throw new BadRequestException("用户名或者邮箱已经存在");
        }
        user.setUserId(null);
        user.setPassword(BCrypt.hashpw(user.getPassword()));
        userMapper.insert(user);
        return user;
    }

    /**
     * 登陆
     */
    @Override
    public SaTokenInfo login(UserDto userDto) {
        // 通过邮箱或者用户名获取用户信息，这里前端传过来的username可以是用户名也可以是邮箱
        User user = getUserByUsernameOrEmail(userDto.getUsername());
        // 验证密码
        if (user == null || !BCrypt.checkpw(userDto.getPassword(), user.getPassword())) {
            throw new BadRequestException(BaseResultCodeEnum.USERNAME_OR_PASSWORD_ERROR);
        }
        // 判断用户是否禁用
        if (!user.getEnabled()) {
            throw new BadRequestException(BaseResultCodeEnum.USER_DISABLED);
        }
        UserDto newUserDto = new UserDto(user);
        // 封装用户权限标识，权限认证时会用到
        newUserDto.setPermissions(menuService.getPermissionListByUserId(user.getUserId()));
        // 登陆，并将用户信息存入redis
//        StpUtil.login(user.getUserId(), SaLoginConfig.setExtra("user", newUserDto));
        StpUtil.login(user.getUserId());
        // 在登录时缓存 user 对象
        StpUtil.getSession().set("userDto", newUserDto);
        return StpUtil.getTokenInfo();
    }

    @Override
    public void logout() {
        StpUtil.logout();
    }

    @Override
    public void register(UserDto userDto) {
        // 判断用户是否已注册
        if (userMapper.selectCount(new LambdaQueryWrapper<User>().eq(User::getUsername, userDto.getUsername())) > 0) {
            throw new EntityExistException(User.class, "username", userDto.getUsername());
        }
        if (userMapper.selectCount(new LambdaQueryWrapper<User>().eq(User::getEmail, userDto.getEmail())) > 0) {
            throw new EntityExistException(User.class, "email", userDto.getEmail());
        }
        // 注册用户
        User user = User.builder()
                .username(userDto.getUsername())
                .password(BCrypt.hashpw(userDto.getPassword()))
                .email(userDto.getEmail())
                .build();
        addUser(user);
    }

    @Override
    public UserDto updateUserInfoByUserId(UserDto userDto) {
        Long userId = StpUtil.getLoginIdAsLong();
        User user = new User();
        BeanUtil.copyProperties(userDto, user);
        user.setUserId(userId);
        // 判断用户名和邮箱是否唯一
        if (user.getUsername() != null) {
            if (userMapper.selectCount(new LambdaQueryWrapper<User>().eq(User::getUsername, user.getUsername())) > 0) {
                throw new EntityExistException(User.class, "username", user.getUsername());
            }
        }
        if (user.getEmail() != null) {
            if (userMapper.selectCount(new LambdaQueryWrapper<User>().eq(User::getEmail, user.getEmail())) > 0) {
                throw new EntityExistException(User.class, "email", user.getEmail());
            }
        }
        userMapper.updateById(user);
        UserDto newUserDto = new UserDto(userMapper.selectById(userId));
        // 封装用户权限标识，权限认证时会用到
        newUserDto.setPermissions(menuService.getPermissionListByUserId(userId));
        // TODO 登陆并将用户信息存入redis
        StpUtil.getSession().set("userDto", newUserDto);
        return newUserDto;
    }

    @Override
    public PageDto<User> getUserPageDtoList(Integer current, Integer pageSize, String username) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        if (username != null && !username.isEmpty()) {
            queryWrapper.like(User::getUsername, username).or()
                    .like(User::getEmail, username).or()
                    .like(User::getNickName, username);
        }
        IPage<User> page = new Page<>(current, pageSize);
        IPage<User> userIPage = userMapper.selectPage(page, queryWrapper);

        return new PageDto<User>(userIPage);
    }

    @Override
    public void delUserByUserId(Long userId) {
        if (userMapper.selectById(userId) == null) {
            throw new BadRequestException(BaseResultCodeEnum.RESOURCE_NOT_FOUND);
        }
        userMapper.deleteById(userId);
    }

    @Override
    public UserDto updateUserByUserId(UserDto userDto) {
        if (userMapper.selectById(userDto.getUserId()) == null) {
            throw new BadRequestException(BaseResultCodeEnum.RESOURCE_NOT_FOUND);
        }
        if (userDto.getPassword() != null) {
            userDto.setPassword(BCrypt.hashpw(userDto.getPassword()));
        }
        User user = new User();
        BeanUtil.copyProperties(userDto, user);
        userMapper.updateById(user);
        StpUtil.getSession().set("userDto", userDto);
        return new UserDto(userMapper.selectById(user.getUserId()));
    }
}
