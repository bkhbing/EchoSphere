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
 * 私信表
 * </p>
 *
 * @author bkhb
 * @since 2024-04-09 22:24:42
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("message")
public class Message {

    /**
     * 私信ID
     */
      @TableId(value = "message_id", type = IdType.AUTO)
    private Long messageId;

    /**
     * 发送者用户ID
     */
    @TableField("sender_id")
    private Long senderId;

    /**
     * 接收者用户ID
     */
    @TableField("receiver_id")
    private Long receiverId;

    /**
     * 消息内容
     */
    @TableField("content")
    private String content;

    /**
     * 消息状态（0: 未读, 1: 已读）
     */
    @TableField("`status`")
    private Boolean status;

    /**
     * 发送人是否删除（0: 否, 1: 是）
     */
    @TableField("sender_remove")
    private Boolean senderRemove;

    /**
     * 接受人是否删除（0: 否, 1: 是）
     */
    @TableField("receiver_remove")
    private Boolean receiverRemove;

    /**
     * 发送时间
     */
      @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
