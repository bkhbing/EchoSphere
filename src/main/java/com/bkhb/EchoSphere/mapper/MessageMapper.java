package com.bkhb.EchoSphere.mapper;

import com.bkhb.EchoSphere.entity.Message;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 私信表 Mapper 接口
 * </p>
 *
 * @author bkhb
 * @since 2024-04-09 22:24:42
 */
@Mapper
public interface MessageMapper extends BaseMapper<Message> {

}
