package com.bkhb.EchoSphere.mapper;

import com.bkhb.EchoSphere.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 系统用户 Mapper 接口
 * </p>
 *
 * @author bkhb
 * @since 2024-03-26 00:57:57
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
