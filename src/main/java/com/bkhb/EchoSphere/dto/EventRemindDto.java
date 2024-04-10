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
public class EventRemindDto {
    /**
     * 消息 ID
     */
    private Long eventRemindId;

    /**
     * 动作类型，如0=评论、1=点赞
     */
    private Integer action;

    /**
     * 事件源 ID，如评论 ID、文章 ID 等
     */
    private Long sourceId;

    /**
     * 事件源类型："Comment"、"Post"等
     */
    private String sourceType;

    /**
     * 事件源的内容，比如回复的内容，回复的评论等等
     */
    private String sourceContent;

//    /**
//     * 事件所发生的地点链接 url
//     */
//    private String url;

    /**
     * 消息状态（0: 未读, 1: 已读）
     */
    private Boolean status;

    /**
     * 操作者的 ID，即谁关注了你，at 了你
     */
    private Long senderId;

    /**
     * 接受通知的用户的 ID
     */
    private Long recipientId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 操作者用户名
     */
    private String senderName;

    /**
     * 操作者头像路径
     */
    private String senderAvatarPath;
}
