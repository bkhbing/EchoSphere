package com.bkhb.EchoSphere.controller;

import com.bkhb.EchoSphere.service.IPostService;
import com.bkhb.EchoSphere.service.IPraiseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 点赞信息表 前端控制器
 * </p>
 *
 * @author bkhb
 * @since 2024-04-08 17:20:18
 */
@Tag(name = "点赞管理", description = "点赞管理")
@RestController
@RequestMapping("/praise")
@RequiredArgsConstructor
public class PraiseController {
    private final IPraiseService praiseService;

    @Operation(summary = "点赞帖子", description = "点赞帖子")
    @PostMapping("post/{postId}")
    public void addPraiseByPostId(@PathVariable Long postId) {
        praiseService.addPraiseByPostId(postId);
    }

    @Operation(summary = "取消点赞帖子", description = "取消点赞帖子")
    @DeleteMapping("post/{postId}")
    public void delPraiseByPostId(@PathVariable Long postId) {
        praiseService.delPraiseByPostId(postId);
    }

    @Operation(summary = "点赞评论", description = "点赞评论")
    @PostMapping("comment/{commentId}")
    public void addPraiseByCommentId(@PathVariable Long commentId) {
        praiseService.addPraiseByCommentId(commentId);
    }

    @Operation(summary = "取消点赞评论", description = "取消点赞评论")
    @DeleteMapping("comment/{commentId}")
    public void delPraiseByCommentId(@PathVariable Long commentId) {
        praiseService.delPraiseByCommentId(commentId);
    }
}
