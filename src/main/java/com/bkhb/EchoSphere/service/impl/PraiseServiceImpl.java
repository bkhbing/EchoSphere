package com.bkhb.EchoSphere.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bkhb.EchoSphere.entity.Comment;
import com.bkhb.EchoSphere.entity.Post;
import com.bkhb.EchoSphere.entity.Praise;
import com.bkhb.EchoSphere.entity.User;
import com.bkhb.EchoSphere.execption.BadRequestException;
import com.bkhb.EchoSphere.mapper.CommentMapper;
import com.bkhb.EchoSphere.mapper.PostMapper;
import com.bkhb.EchoSphere.mapper.PraiseMapper;
import com.bkhb.EchoSphere.result.BaseResultCodeEnum;
import com.bkhb.EchoSphere.service.IPraiseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 点赞信息表 服务实现类
 * </p>
 *
 * @author bkhb
 * @since 2024-04-08 17:20:18
 */
@Service
@RequiredArgsConstructor
public class PraiseServiceImpl extends ServiceImpl<PraiseMapper, Praise> implements IPraiseService {
    private final PraiseMapper praiseMapper;
    private final PostMapper postMapper;
    private final CommentMapper commentMapper;

    // 通过帖子ID点赞
    @Override
    public void addPraiseByPostId(Long postId) {
        // 获取当前登录用户ID
        Long userId = StpUtil.getLoginIdAsLong();

        // 查询帖子
        LambdaQueryWrapper<Post> postLambdaQueryWrapper = new LambdaQueryWrapper<>();
        postLambdaQueryWrapper.eq(Post::getPostId, postId)
                .eq(Post::getStatus, true);
        Post post = postMapper.selectOne(postLambdaQueryWrapper);

        // 如果帖子不存在，则抛出异常
        if (post == null) {
            throw new BadRequestException(BaseResultCodeEnum.RESOURCE_NOT_FOUND);
        } else if (post.getUserId().equals(userId)) {
            throw new BadRequestException("不能给自己点赞");
        }

        // 查询是否已点过赞
        LambdaQueryWrapper<Praise> praiseLambdaQueryWrapper = new LambdaQueryWrapper<>();
        praiseLambdaQueryWrapper.eq(Praise::getPostId, postId)
                .eq(Praise::getUserId, userId);
        Praise praise = praiseMapper.selectOne(praiseLambdaQueryWrapper);
        if (praise != null) {
            throw new BadRequestException("不能重复点赞");
        }

        // 保存点赞记录
        praise = new Praise();
        praise.setUserId(userId);
        praise.setPostId(postId);
        save(praise);
    }

    // 通过帖子ID取消点赞
    @Override
    public void delPraiseByPostId(Long postId) {
        // 获取当前登录用户ID
        Long userId = StpUtil.getLoginIdAsLong();

        // 查询并删除点赞记录
        LambdaQueryWrapper<Praise> praiseLambdaQueryWrapper = new LambdaQueryWrapper<>();
        praiseLambdaQueryWrapper.eq(Praise::getPostId, postId)
                .eq(Praise::getUserId, userId);
        if (!remove(praiseLambdaQueryWrapper)) {
            throw new BadRequestException(BaseResultCodeEnum.ILLEGAL_ARGUMENT);
        }
    }

    // 通过评论ID点赞
    @Override
    public void addPraiseByCommentId(Long commentId) {
        // 获取当前登录用户ID
        Long userId = StpUtil.getLoginIdAsLong();

        // 查询评论
        LambdaQueryWrapper<Comment> commentLambdaQueryWrapper = new LambdaQueryWrapper<>();
        commentLambdaQueryWrapper.eq(Comment::getCommentId, commentId)
                .eq(Comment::getStatus, true);
        Comment comment = commentMapper.selectOne(commentLambdaQueryWrapper);

        // 如果评论不存在，则抛出异常
        if (comment == null) {
            throw new BadRequestException(BaseResultCodeEnum.RESOURCE_NOT_FOUND);
        } else if (comment.getUserId().equals(userId)) {
            throw new BadRequestException("不能给自己点赞");
        }

        // 查询是否已点过赞
        LambdaQueryWrapper<Praise> praiseLambdaQueryWrapper = new LambdaQueryWrapper<>();
        praiseLambdaQueryWrapper.eq(Praise::getCommentId, commentId)
                .eq(Praise::getUserId, userId);
        Praise praise = praiseMapper.selectOne(praiseLambdaQueryWrapper);
        if (praise != null) {
            throw new BadRequestException("不能重复点赞");
        }

        // 保存点赞记录
        praise = new Praise();
        praise.setUserId(userId);
        praise.setCommentId(commentId);
        save(praise);
    }

    // 通过评论ID取消点赞
    @Override
    public void delPraiseByCommentId(Long commentId) {
        // 获取当前登录用户ID
        Long userId = StpUtil.getLoginIdAsLong();

        // 查询并删除点赞记录
        LambdaQueryWrapper<Praise> praiseLambdaQueryWrapper = new LambdaQueryWrapper<>();
        praiseLambdaQueryWrapper.eq(Praise::getCommentId, commentId)
                .eq(Praise::getUserId, userId);
        if (!remove(praiseLambdaQueryWrapper)) {
            throw new BadRequestException(BaseResultCodeEnum.ILLEGAL_ARGUMENT);
        }
    }

}
