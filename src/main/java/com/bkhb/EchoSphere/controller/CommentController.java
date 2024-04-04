package com.bkhb.EchoSphere.controller;

import com.bkhb.EchoSphere.dto.CommentDto;
import com.bkhb.EchoSphere.service.ICommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 帖子信息表 前端控制器
 * </p>
 *
 * @author bkhb
 * @since 2024-04-03 16:52:04
 */
@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
@Tag(name = "评论管理", description = "评论管理")
public class CommentController {
    private final ICommentService commentService;

    @Operation(summary = "添加评论", description = "添加评论")
    @PostMapping
    public CommentDto add(@RequestBody CommentDto comment) {
        return commentService.addComment(comment);
    }

    @Operation(summary = "删除评论", description = "删除评论，只能删除自己的评论")
    @DeleteMapping("{commentId}")
    public void del(@PathVariable("commentId") Long commentId) {
        commentService.delCommentById(commentId);
    }
}
