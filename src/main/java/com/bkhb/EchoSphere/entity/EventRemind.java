package com.bkhb.EchoSphere.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 事件提醒表
 * </p>
 *
 * @author bkhb
 * @since 2024-04-10 14:39:15
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("event_remind")
public class EventRemind {

    /**
     * 消息 ID
     */
    @TableId(value = "event_remind_id", type = IdType.AUTO)
    private Long eventRemindId;

    /**
     * 动作类型，如0=评论、1=点赞
     */
    @TableField("`action`")
    private Boolean action;

    /**
     * 事件源 ID，如评论 ID、文章 ID 等
     */
    @TableField("source_id")
    private Long sourceId;

    /**
     * 事件源类型："Comment"、"Post"等
     */
    @TableField("source_type")
    private String sourceType;

    /**
     * 事件源的内容，比如回复的内容，回复的评论等等
     */
    @TableField("source_content")
    private String sourceContent;

    /**
     * 事件所发生的地点链接 url
     */
    @TableField("url")
    private String url;

    /**
     * 消息状态（0: 未读, 1: 已读）
     */
    @TableField("`status`")
    private Boolean status;

    /**
     * 操作者的 ID，即谁关注了你，at 了你
     */
    @TableField("sender_id")
    private Long senderId;

    /**
     * 接受通知的用户的 ID
     */
    @TableField("recipient_id")
    private Long recipientId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
