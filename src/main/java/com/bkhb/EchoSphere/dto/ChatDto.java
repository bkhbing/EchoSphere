package com.bkhb.EchoSphere.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatDto {
    /**
     * 聊天室ID
     */
    private Long chatId;

    /**
     * 对方用户ID
     */
    private Long userId;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像真实路径
     */
    private String avatarPath;

    /**
     * 最后一条消息的内容
     */
    private String lastMessage;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
