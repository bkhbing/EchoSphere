package com.bkhb.EchoSphere.service;

import com.bkhb.EchoSphere.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 系统用户 服务类
 * </p>
 *
 * @author bkhb
 * @since 2024-03-26 00:57:57
 */
public interface IUserService extends IService<User> {

    User getUser(Long userId);
}
