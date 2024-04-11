package com.bkhb.EchoSphere.service;

import com.bkhb.EchoSphere.dto.PageDto;
import com.bkhb.EchoSphere.dto.PostDto;
import com.bkhb.EchoSphere.entity.Post;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 帖子信息表 服务类
 * </p>
 *
 * @author bkhb
 * @since 2024-03-29 20:47:32
 */
public interface IPostService extends IService<Post> {

    void addPost(Post post);

    PageDto<Post> selectPageAll(Long current, Integer pageSize);

    PostDto getPostById(Long postId);

    Post updatePostById(Post post);

    void delPostById(Long postId);

    void auditPost(Long postId);

    List<Post> findPostByTitleOrContent(String query);

    List<Post> getPostByUserId(long loginIdAsLong);

    void addPostAsync(Post post);
}
