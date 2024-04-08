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
 * 点赞信息表
 * </p>
 *
 * @author bkhb
 * @since 2024-04-08 17:20:18
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("praise")
public class Praise {

    @TableId(value = "praise_id", type = IdType.AUTO)
    private Long praiseId;

    /**
     * 点赞用户的ID
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 被点赞的帖子ID，与comment_id互斥
     */
    @TableField("post_id")
    private Long postId;

    /**
     * 被点赞的评论ID，与post_id互斥
     */
    @TableField("comment_id")
    private Long commentId;

    /**
     * 点赞时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
