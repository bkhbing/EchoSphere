package com.bkhb.EchoSphere.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bkhb.EchoSphere.dto.CommentDto;
import com.bkhb.EchoSphere.entity.Comment;
import com.bkhb.EchoSphere.entity.Post;
import com.bkhb.EchoSphere.execption.BadRequestException;
import com.bkhb.EchoSphere.mapper.CommentMapper;
import com.bkhb.EchoSphere.mapper.PostMapper;
import com.bkhb.EchoSphere.mapper.UserMapper;
import com.bkhb.EchoSphere.result.BaseResultCodeEnum;
import com.bkhb.EchoSphere.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bkhb.EchoSphere.service.IPostService;
import com.bkhb.EchoSphere.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 帖子信息表 服务实现类
 * </p>
 *
 * @author bkhb
 * @since 2024-04-03 16:52:04
 */
@Service
@RequiredArgsConstructor
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {
    private final UserMapper userMapper;
    private final CommentMapper commentMapper;
    private final PostMapper postMapper;

    @Override
    public List<CommentDto> getCommentDtoListByPostId(Long postId) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getEntityId, postId).eq(Comment::getEntityType, 0).eq(Comment::getStatus, true);
        return getCommentDtoList(queryWrapper);
    }

    @Override
    public List<CommentDto> getReplyDtoListByPostId(Long postId) {
        List<CommentDto> commentDtoList = new ArrayList<>();
        getCommentDtoListByPostId(postId).forEach(commentDto -> {
            LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Comment::getEntityId, commentDto.getCommentId()).eq(Comment::getEntityType, 1).eq(Comment::getStatus, true);
            commentDtoList.addAll(commentMapper.selectList(queryWrapper).stream().map(comment -> {
                CommentDto commentDto1 = new CommentDto(comment);
                commentDto1.setTargetNickName(userMapper.selectById(comment.getTargetId()).getNickName());
                commentDto1.setUserNickName(userMapper.selectById(comment.getUserId()).getNickName());
                return commentDto1;
            }).toList());
        });
        return commentDtoList;
    }

    @Override
    public Integer getCommentCountByPostId(Long postId) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getEntityId, postId).eq(Comment::getStatus, true);
        return Math.toIntExact(commentMapper.selectCount(queryWrapper));
    }

    @Override
    public CommentDto addComment(CommentDto comment) {
        // 判断用户是否存在
        if (userMapper.selectById(comment.getTargetId()) == null) {
            throw new BadRequestException(BaseResultCodeEnum.BAD_REQUEST);
        }
        // 判断帖子或者评论是否存在
        if (comment.getEntityType() == 0) {
            LambdaQueryWrapper<Post> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Post::getPostId, comment.getEntityId()).eq(Post::getStatus, true);
            if (postMapper.selectOne(queryWrapper) == null) {
                throw new BadRequestException(BaseResultCodeEnum.BAD_REQUEST);
            }
        } else if (comment.getEntityType() == 1) {
            LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Comment::getCommentId, comment.getEntityId()).eq(Comment::getStatus, true).eq(Comment::getEntityType, 0);
            if (commentMapper.selectOne(queryWrapper) == null) {
                throw new BadRequestException(BaseResultCodeEnum.BAD_REQUEST);
            }
        }
        Long userId = StpUtil.getLoginIdAsLong();
        Comment newComment = new Comment();
        newComment.setUserId(userId);
        newComment.setTargetId(comment.getTargetId());
        newComment.setEntityType(comment.getEntityType());
        newComment.setEntityId(comment.getEntityId());
        newComment.setContent(comment.getContent());
        save(newComment);
        CommentDto commentDto = new CommentDto(newComment);
        commentDto.setUserNickName(userMapper.selectById(comment.getTargetId()).getNickName());
        commentDto.setTargetNickName(userMapper.selectById(comment.getTargetId()).getNickName());
        return commentDto;
    }

    @Override
    public void delCommentById(Long commentId) {
        Long userId = StpUtil.getLoginIdAsLong();
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getCommentId, commentId).eq(Comment::getUserId, userId);
        if (commentMapper.selectOne(queryWrapper) == null) {
            throw new BadRequestException(BaseResultCodeEnum.NO_OPERATE_PERMISSION);
        }
        commentMapper.deleteById(commentId);
    }

    private List<CommentDto> getCommentDtoList(LambdaQueryWrapper<Comment> queryWrapper) {
        List<Comment> commentList = list(queryWrapper);
        if (commentList == null) {
            return null;
        }
        return commentList.stream().map(comment -> {
            CommentDto commentDto = new CommentDto(comment);
            commentDto.setUserNickName(userMapper.selectById(comment.getTargetId()).getNickName());
            commentDto.setTargetNickName(userMapper.selectById(comment.getTargetId()).getNickName());
            return commentDto;
        }).toList();
    }
}
