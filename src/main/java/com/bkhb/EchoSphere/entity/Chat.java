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
 * 聊天室表
 * </p>
 *
 * @author bkhb
 * @since 2024-04-09 23:28:54
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("chat")
public class Chat {

    /**
     * 聊天室ID
     */
      @TableId(value = "chat_id", type = IdType.AUTO)
    private Long chatId;

    /**
     * 用户ID
     */
    @TableField("user1_id")
    private Long user1Id;

    /**
     * 用户ID
     */
    @TableField("user2_id")
    private Long user2Id;

    /**
     * 最后一条消息的内容
     */
    @TableField("last_message")
    private String lastMessage;

    /**
     * 创建时间
     */
      @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
