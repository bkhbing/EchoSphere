package com.bkhb.EchoSphere.mapper;

import com.bkhb.EchoSphere.entity.Chat;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 聊天室表 Mapper 接口
 * </p>
 *
 * @author bkhb
 * @since 2024-04-09 23:03:16
 */
@Mapper
public interface ChatMapper extends BaseMapper<Chat> {

}
