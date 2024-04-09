package com.bkhb.EchoSphere.controller;

import com.bkhb.EchoSphere.dto.PageDto;
import com.bkhb.EchoSphere.entity.Message;
import com.bkhb.EchoSphere.service.IMessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 私信表 前端控制器
 * </p>
 *
 * @author bkhb
 * @since 2024-04-09 22:24:42
 */
@Tag(name = "私信管理", description = "私信管理")
@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
public class MessageController {
    private final IMessageService messageService;

    @Operation(summary = "发送私信", description = "用户发送一条私信给另一个用户。")
    @PostMapping("send")
    public Message sendMessage(@RequestBody Message message) {
        return messageService.sendMessage(message);
    }

    @Operation(summary = "标记私信为已读", description = "将指定ID的私信标记为已读。")
    @PostMapping("{messageId}/read")
    public void readMessage(@PathVariable Long messageId) {
        messageService.readMessage(messageId);
    }

    @Operation(summary = "查询收件箱", description = "获取当前用户收到的所有未删除的私信。")
    @GetMapping("inbox")
    public List<Message> getInbox() {
        return messageService.getInbox();
    }
}
