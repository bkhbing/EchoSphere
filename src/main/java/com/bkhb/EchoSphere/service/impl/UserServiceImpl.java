package com.bkhb.EchoSphere.service.impl;

import com.bkhb.EchoSphere.entity.User;
import com.bkhb.EchoSphere.dao.UserDao;
import com.bkhb.EchoSphere.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户 服务实现类
 * </p>
 *
 * @author bkhb
 * @since 2024-03-26 00:57:57
 */
@Service
@AllArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements IUserService {
    final private UserDao userDao;

    @Override
    public User getUser(Long userId) {
        return userDao.getUser(userId);
    }
}
