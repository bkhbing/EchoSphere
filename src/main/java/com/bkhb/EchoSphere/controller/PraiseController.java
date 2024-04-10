package com.bkhb.EchoSphere.controller;

import com.bkhb.EchoSphere.entity.Praise;
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

    @Operation(summary = "点赞", description = "点赞帖子或者评论")
    @PostMapping
    public void addPraise(@RequestBody Praise praise) {
        praiseService.addPraise(praise);
    }

    @Operation(summary = "取消点赞", description = "取消点赞帖子或者评论")
    @DeleteMapping
    public void delPraise(@RequestBody Praise praise) {
        praiseService.delPraise(praise);
    }
}
