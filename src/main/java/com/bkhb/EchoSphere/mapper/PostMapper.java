package com.bkhb.EchoSphere.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bkhb.EchoSphere.entity.Comment;
import com.bkhb.EchoSphere.entity.Post;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 帖子信息表 Mapper 接口
 * </p>
 *
 * @author bkhb
 * @since 2024-03-29 20:47:32
 */
@Mapper
public interface PostMapper extends BaseMapper<Post> {
}
