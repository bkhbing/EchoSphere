package com.bkhb.EchoSphere.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bkhb.EchoSphere.dto.CommentDto;
import com.bkhb.EchoSphere.entity.Comment;
import com.bkhb.EchoSphere.entity.EventRemind;
import com.bkhb.EchoSphere.entity.Post;
import com.bkhb.EchoSphere.execption.BadRequestException;
import com.bkhb.EchoSphere.mapper.CommentMapper;
import com.bkhb.EchoSphere.mapper.EventRemindMapper;
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
import java.util.Objects;

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
    private final EventRemindMapper eventRemindMapper;

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
//        if (userMapper.selectById(comment.getTargetId()) == null) {
//            throw new BadRequestException(BaseResultCodeEnum.BAD_REQUEST);
//        }
        // 判断这次评论是否属于回复
        Long userId = StpUtil.getLoginIdAsLong();
        comment.setUserId(userId);
        if (comment.getEntityType() == 0) {
            // 判断帖子是否存在
            LambdaQueryWrapper<Post> postLambdaQueryWrapper = new LambdaQueryWrapper<>();
            postLambdaQueryWrapper.eq(Post::getPostId, comment.getEntityId()).eq(Post::getStatus, true);
            Post post = postMapper.selectOne(postLambdaQueryWrapper);
            if (Objects.isNull(post)) {
                throw new BadRequestException(BaseResultCodeEnum.BAD_REQUEST);
            }
            comment.setTargetId(post.getUserId());

        } else if (comment.getEntityType() == 1) {
            LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Comment::getCommentId, comment.getEntityId()).eq(Comment::getStatus, true).eq(Comment::getEntityType, 0);
            Comment comment1 = commentMapper.selectOne(queryWrapper);
            if (Objects.isNull(comment1)) {
                throw new BadRequestException(BaseResultCodeEnum.BAD_REQUEST);
            }
            comment.setTargetId(comment1.getUserId());
        }
        Comment newComment = new Comment();
        BeanUtil.copyProperties(comment, newComment);
        save(newComment);

        // 添加评论消息提醒
        EventRemind eventRemind = new EventRemind();
        eventRemind.setAction(0);
        eventRemind.setUrl("/comment/" + newComment.getCommentId());
        eventRemind.setSenderId(userId);
        eventRemind.setRecipientId(newComment.getTargetId());
        eventRemind.setSourceId(newComment.getCommentId());
        eventRemind.setSourceType("Comment");
        eventRemind.setSourceContent(newComment.getContent());
        eventRemindMapper.insert(eventRemind);

        CommentDto commentDto = new CommentDto(commentMapper.selectById(newComment.getCommentId()));
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
