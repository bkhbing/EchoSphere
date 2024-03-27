package com.bkhb.EchoSphere.service.impl;

import com.bkhb.EchoSphere.entity.Role;
import com.bkhb.EchoSphere.mapper.RoleMapper;
import com.bkhb.EchoSphere.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author bkhb
 * @since 2024-03-28 00:05:57
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
    private final RoleMapper roleMapper;
}
