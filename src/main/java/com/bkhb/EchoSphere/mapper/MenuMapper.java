package com.bkhb.EchoSphere.mapper;

import com.bkhb.EchoSphere.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 系统菜单 Mapper 接口
 * </p>
 *
 * @author bkhb
 * @since 2024-03-28 00:05:57
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    List<String> selectPermissionListByUserId(Long userId);
}
