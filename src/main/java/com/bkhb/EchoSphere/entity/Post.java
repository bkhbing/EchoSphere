package com.bkhb.EchoSphere.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 帖子信息表
 * </p>
 *
 * @author bkhb
 * @since 2024-03-29 20:47:32
 */
@Builder
@Getter
@Setter
@Accessors(chain = true)
@TableName("post")
public class Post {

      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 发布用户的ID
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 帖子标题
     */
    @TableField("title")
    private String title;

    /**
     * 帖子内容
     */
    @TableField("content")
    private String content;

    /**
     * 封禁状态：1启用、0禁用
     */
    @TableField("status")
    private Boolean status;

    /**
     * 帖子浏览量
     */
    @TableField("view_count")
    private Integer viewCount;

    /**
     * 帖子创建时间
     */
      @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 帖子最后更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;
}
