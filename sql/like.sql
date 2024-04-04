-- ----------------------------
-- Table structure for like
-- ----------------------------
DROP TABLE IF EXISTS `like`;
CREATE TABLE `like` (
                        `id` BIGINT NOT NULL AUTO_INCREMENT,
                        `user_id` BIGINT NOT NULL COMMENT '点赞用户的ID',
                        `post_id` BIGINT DEFAULT NULL COMMENT '被点赞的帖子ID，与comment_id互斥',
                        `comment_id` BIGINT DEFAULT NULL COMMENT '被点赞的评论ID，与post_id互斥',
                        `create_time` DATETIME NOT NULL COMMENT '点赞时间',
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `unique_like` (`user_id`, `post_id`, `comment_id`) COMMENT '保证用户对同一帖子或评论只能点赞一次'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='点赞信息表';

-- ----------------------------
-- Records of like
-- ----------------------------
BEGIN;
INSERT INTO `like` (`user_id`, `title`, `content`, `create_time`, `update_time`)
VALUES
    (1, '欢迎来到论坛', '这是一个用户1发表的帖子内容，欢迎大家积极讨论。', NOW(), NOW()),
    (1, '关于论坛的使用规则', '这里是一些论坛使用规则，请大家遵守。', NOW(), NOW()),
    (2, '如何提高编程技能', '这是用户2发表的关于如何提高编程技能的讨论帖，欢迎大家分享经验。', NOW(), NOW()),
    (2, '今日足球赛事讨论', '这里讨论今日的足球赛事，欢迎喜欢足球的朋友们加入。', NOW(), NOW());
COMMIT;