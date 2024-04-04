package com.bkhb.EchoSphere.mapper;

import com.bkhb.EchoSphere.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 帖子信息表 Mapper 接口
 * </p>
 *
 * @author bkhb
 * @since 2024-04-03 16:52:04
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

}
