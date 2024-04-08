package com.bkhb.EchoSphere.dto;

import com.bkhb.EchoSphere.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private Long postId;

    /**
     * 发布用户的ID
     */
    private Long userId;

    /**
     * 帖子标题
     */
    private String title;

    /**
     * 帖子内容
     */
    private String content;

    /**
     * 封禁状态：1启用、0禁用
     */
    private Boolean status;

    /**
     * 帖子浏览量
     */
    private Integer viewCount;

    /**
     * 帖子创建时间
     */
    private LocalDateTime createTime;

    /**
     * 帖子最后更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 点赞数量
     */
    private Integer likeCount;

    /**
     * 评论数量
     */
    private Integer commentCount;

    /**
     * 评论列表
     */
    private List<CommentDto> commentDtoList;

    /**
     * 回复列表
     */
    private List<CommentDto> replyDtoList;

    public PostDto(Post post) {
        this.postId = post.getPostId();
        this.userId = post.getUserId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.status = post.getStatus();
        this.viewCount = post.getViewCount();
        this.createTime = post.getCreateTime();
        this.updateTime = post.getUpdateTime();
    }
}
