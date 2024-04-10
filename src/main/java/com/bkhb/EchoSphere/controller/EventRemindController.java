package com.bkhb.EchoSphere.controller;

import com.bkhb.EchoSphere.dto.EventRemindDto;
import com.bkhb.EchoSphere.entity.EventRemind;
import com.bkhb.EchoSphere.service.IEventRemindService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 事件提醒表 前端控制器
 * </p>
 *
 * @author bkhb
 * @since 2024-04-10 14:39:15
 */
@Tag(name = "事件提醒管理")
@RequiredArgsConstructor
@RestController
@RequestMapping("/eventRemind")
public class EventRemindController {
    private final IEventRemindService eventRemindService;

    @Operation(summary = "获取点赞消息", description = "获取当前用户的聚合消息列表")
    @GetMapping("praise")
    public List<EventRemindDto> getPraiseList() {
        return eventRemindService.getPraiseList();
    }

    @Operation(summary = "获取消息的url", description = "获取消息的url")
    @GetMapping("{eventRemindId}/url")
    public String getUrl(@PathVariable Long eventRemindId) {
        return eventRemindService.getUrl(eventRemindId);
    }
}
