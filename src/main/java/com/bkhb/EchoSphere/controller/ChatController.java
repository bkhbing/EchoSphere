package com.bkhb.EchoSphere.controller;

import com.bkhb.EchoSphere.dto.ChatDto;
import com.bkhb.EchoSphere.entity.Message;
import com.bkhb.EchoSphere.service.IChatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 聊天室表 前端控制器
 * </p>
 *
 * @author bkhb
 * @since 2024-04-09 23:03:16
 */
@Tag(name = "聊天室管理", description = "聊天室管理")
@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatController {
    private final IChatService chatService;

    @Operation(summary = "查看聊天室", description = "查看当前用户的所有聊天室。")
    @GetMapping
    public List<ChatDto> getChat() {
        return chatService.getChatByLoginUserId();
    }

    @Operation(summary = "查看聊天记录", description = "通过聊天室ID查看聊天记录。")
    @GetMapping("{chatId}/message")
    public List<Message> getMessageByChatId(@PathVariable Long chatId) {
        return chatService.getMessageByChatId(chatId);
    }
}
