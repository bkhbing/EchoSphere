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
 * 帖子信息表
 * </p>
 *
 * @author bkhb
 * @since 2024-04-03 16:52:04
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("comment")
public class Comment {

    @TableId(value = "comment_id", type = IdType.AUTO)
    private Long commentId;

    /**
     * 发布评论用户的ID
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 目标用户 ID
     */
    @TableField("target_id")
    private Long targetId;

    /**
     * 评论类型：0评论、1帖子
     */
    @TableField("entity_type")
    private Integer entityType;

    /**
     * 评论或者帖子ID
     */
    @TableField("entity_id")
    private Long entityId;

    /**
     * 评论内容
     */
    @TableField("content")
    private String content;

    /**
     * 状态：0正常、1禁用
     */
    @TableField("`status`")
    private Boolean status;

    /**
     * 创建时间
     */
      @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
