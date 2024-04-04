package com.bkhb.EchoSphere.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bkhb.EchoSphere.dto.PageDto;
import com.bkhb.EchoSphere.dto.PostDto;
import com.bkhb.EchoSphere.entity.Post;
import com.bkhb.EchoSphere.entity.User;
import com.bkhb.EchoSphere.execption.BadRequestException;
import com.bkhb.EchoSphere.mapper.PostMapper;
import com.bkhb.EchoSphere.result.BaseResultCodeEnum;
import com.bkhb.EchoSphere.service.ICommentService;
import com.bkhb.EchoSphere.service.IPostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 帖子信息表 服务实现类
 * </p>
 *
 * @author bkhb
 * @since 2024-03-29 20:47:32
 */
@Service
@RequiredArgsConstructor
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements IPostService {

    private final PostMapper postMapper;
    private final ICommentService commentService;

    @Override
    public Post addPost(Post post) {
        post.setUserId(StpUtil.getLoginIdAsLong());
        save(post);
        return getById(post.getId());
    }

    @Override
    public PageDto<Post> selectPageAll(Long current, Integer pageSize) {
        LambdaQueryWrapper<Post> queryWrapper = new LambdaQueryWrapper<>();
        // 过了未发布和封禁的数据
        queryWrapper.eq(Post::getStatus, true).eq(Post::getStatus, true);
        IPage<Post> page = new Page<>(current,pageSize);
        postMapper.selectPage(page, queryWrapper);
        return new PageDto<>(page);
    }

    @Override
    public PostDto getPostById(Long postId) {
        LambdaQueryWrapper<Post> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Post::getId, postId).eq(Post::getStatus, true).eq(Post::getStatus, true);
        Post post = postMapper.selectOne(queryWrapper);
        // 浏览量加一
        if (post == null) {
            return null;

        }
        int count = post.getViewCount()+1;
        post.setViewCount(count);
        postMapper.updateById(post);
        PostDto postDto = new PostDto(post);
        postDto.setCommentDtoList(commentService.getCommentDtoListByPostId(postId));
        postDto.setReplyDtoList(commentService.getReplyDtoListByPostId(postId));
        postDto.setCommentCount(commentService.getCommentCountByPostId(postId));
        return postDto;
    }

    @Override
    public Post updatePostById(Post post) {
        // 判断帖子id是否正确
        Post oldPost = postMapper.selectById(post.getId());
        if (oldPost == null){
            throw new BadRequestException(BaseResultCodeEnum.RESOURCE_NOT_FOUND);
        }
        // 判断这个帖子是不是自己的
        if (!oldPost.getUserId().equals(StpUtil.getLoginIdAsLong())) {
            throw new BadRequestException(BaseResultCodeEnum.NO_OPERATE_PERMISSION);
        }
        postMapper.updateById(post);
        return postMapper.selectById(post.getId());
    }

    @Override
    public void delPostById(Long postId) {
        Long userId = StpUtil.getLoginIdAsLong();
        if (!isUserPostOwner(userId, postId)) {
            throw new BadRequestException(BaseResultCodeEnum.NO_OPERATE_PERMISSION);
        }
        postMapper.deleteById(postId);
    }

    @Override
    public void auditPost(Long postId) {
        Post post = postMapper.selectById(postId);
        if (post == null) {
            throw new BadRequestException(BaseResultCodeEnum.RESOURCE_NOT_FOUND);
        }
        post.setStatus(!post.getStatus());
        postMapper.updateById(post);
    }

    @Override
    public List<Post> findPostByTitleOrContent(String query) {
        LambdaQueryWrapper<Post> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Post::getStatus,true)
                .like(Post::getTitle,query)
                .like(Post::getContent,query);
        return postMapper.selectList(queryWrapper);
    }

    @Override
    public List<Post> getPostByUserId(long loginIdAsLong) {
        LambdaQueryWrapper<Post> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Post::getUserId,loginIdAsLong);
        return postMapper.selectList(queryWrapper);
    }

    /**
     * 判断用户是否拥有这个帖子
     */
    public boolean isUserPostOwner(Long userId, Long postId) {
        Post post = postMapper.selectById(postId);
        return post != null && post.getUserId().equals(userId);
    }
}
