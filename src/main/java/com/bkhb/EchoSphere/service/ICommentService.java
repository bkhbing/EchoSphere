package com.bkhb.EchoSphere.service;

import com.bkhb.EchoSphere.dto.CommentDto;
import com.bkhb.EchoSphere.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 帖子信息表 服务类
 * </p>
 *
 * @author bkhb
 * @since 2024-04-03 16:52:04
 */
public interface ICommentService extends IService<Comment> {

    List<CommentDto> getCommentDtoListByPostId(Long postId);

    List<CommentDto> getReplyDtoListByPostId(Long postId);

    Integer getCommentCountByPostId(Long postId);

    CommentDto addComment(CommentDto comment);

    void delCommentById(Long commentId);
}
