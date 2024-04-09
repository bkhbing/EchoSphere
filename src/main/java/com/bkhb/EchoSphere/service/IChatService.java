package com.bkhb.EchoSphere.service;

import com.bkhb.EchoSphere.dto.ChatDto;
import com.bkhb.EchoSphere.entity.Chat;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bkhb.EchoSphere.entity.Message;

import java.util.List;

/**
 * <p>
 * 聊天室表 服务类
 * </p>
 *
 * @author bkhb
 * @since 2024-04-09 23:03:16
 */
public interface IChatService extends IService<Chat> {

    List<ChatDto> getChatByLoginUserId();

    List<Message> getMessageByChatId(Long chatId);
}
