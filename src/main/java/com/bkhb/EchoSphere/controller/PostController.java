package com.bkhb.EchoSphere.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.StpUtil;
import com.bkhb.EchoSphere.dto.PageDto;
import com.bkhb.EchoSphere.dto.PostDto;
import com.bkhb.EchoSphere.entity.Post;
import com.bkhb.EchoSphere.service.IPostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 帖子信息表 前端控制器
 * </p>
 *
 * @author bkhb
 * @since 2024-03-29 20:47:32
 */
@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
@Tag(name = "帖子管理", description = "帖子管理")
public class PostController {
    private final IPostService postService;

    @Operation(summary = "添加帖子", description = "添加帖子")
    @PostMapping
    public void addPost(@RequestBody Post post) {
        post.setUserId(StpUtil.getLoginIdAsLong());
        postService.addPostAsync(post);
    }

    @Operation(summary = "查询所有帖子", description = "分页查询帖子")
    @GetMapping
    public PageDto<Post> selectPageAll(@RequestParam(defaultValue = "1") Long current, @RequestParam(defaultValue = "10") Integer pageSize) {
        return postService.selectPageAll(current, pageSize);
    }

    @Operation(summary = "查询单个帖子", description = "根据ID查询单个帖子")
    @SaIgnore
    @GetMapping("{postId}")
    public PostDto selectOne(@PathVariable Long postId) {
        return postService.getPostById(postId);
    }

    @Operation(summary = "修改帖子", description = "根据ID修改帖子")
    @PutMapping("{postId}")
    public Post edit(@PathVariable Long postId, @RequestBody Post post) {
        post.setPostId(postId);
        return postService.updatePostById(post);
    }

    @Operation(summary = "删除帖子", description = "根据ID删除帖子")
    @DeleteMapping("{postId}")
    public void del(@PathVariable Long postId) {
        postService.delPostById(postId);
    }

    @Operation(summary = "根据id审核帖子", description = "只有管理员可以使用")
    @SaCheckPermission("user:edit")
    @PutMapping("{postId}/status")
    public void audit(@PathVariable Long postId) {
        postService.auditPost(postId);
    }

    @Operation(summary = "搜索帖子", description = "搜索帖子")
    @GetMapping("search")
    public List<Post> search(@RequestParam String query) {
        return postService.findPostByTitleOrContent(query);
    }

    @Operation(summary = "获取当前用户的所有帖子", description = "获取当前用户的所有帖子")
    @GetMapping("list")
    public List<Post> list() {
        return postService.getPostByUserId(StpUtil.getLoginIdAsLong());
    }
}
