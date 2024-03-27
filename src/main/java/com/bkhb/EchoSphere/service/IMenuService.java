package com.bkhb.EchoSphere.service;

import com.bkhb.EchoSphere.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 系统菜单 服务类
 * </p>
 *
 * @author bkhb
 * @since 2024-03-28 00:05:57
 */
public interface IMenuService extends IService<Menu> {

    List<String> getPermissionListByUserId(Long userId);
}
