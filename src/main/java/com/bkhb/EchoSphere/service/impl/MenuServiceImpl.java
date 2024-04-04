package com.bkhb.EchoSphere.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bkhb.EchoSphere.entity.Menu;
import com.bkhb.EchoSphere.mapper.MenuMapper;
import com.bkhb.EchoSphere.mapper.RoleMapper;
import com.bkhb.EchoSphere.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 系统菜单 服务实现类
 * </p>
 *
 * @author bkhb
 * @since 2024-03-28 00:05:57
 */
@Service
@RequiredArgsConstructor
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {
    private final MenuMapper menuMapper;
    private final RoleMapper roleMapper;

    @Override
    public List<String> getPermissionListByUserId(Long userId) {
        return menuMapper.selectPermissionListByUserId(userId);
    }
}
