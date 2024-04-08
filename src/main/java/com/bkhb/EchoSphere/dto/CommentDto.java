package com.bkhb.EchoSphere.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.bkhb.EchoSphere.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private Long commentId;

    /**
     * 发布评论用户的ID
     */
    private Long userId;

    /**
     * 目标用户 ID
     */
    private Long targetId;

    /**
     * 评论类型：0评论、1帖子
     */
    private Integer entityType;

    /**
     * 评论或者帖子ID
     */
    private Long entityId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 状态：1正常、0禁用
     */
    private Boolean status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 用户名
     */
    private String userNickName;

    /**
     * 目标用户名
     */
    private String targetNickName;

    public CommentDto(Comment comment) {
        if (comment == null) {
            return;
        }
        this.commentId = comment.getCommentId();
        this.userId = comment.getUserId();
        this.targetId = comment.getTargetId();
        this.entityType = comment.getEntityType();
        this.entityId = comment.getEntityId();
        this.content = comment.getContent();
        this.status = comment.getStatus();
        this.createTime = comment.getCreateTime();
    }
}
