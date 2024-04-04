-- ----------------------------
-- Table structure for posts
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post` (
                         `id` BIGINT NOT NULL AUTO_INCREMENT,
                         `user_id` BIGINT NOT NULL COMMENT '发布用户的ID',
                         `title` VARCHAR(255) NOT NULL COMMENT '帖子标题',
                         `content` TEXT NOT NULL COMMENT '帖子内容',
                         `status`  bit(1) DEFAULT b'1' COMMENT '状态：1正常、0禁用',
                         `view_count` INT DEFAULT 0 COMMENT '帖子浏览量',
                         `create_time` DATETIME DEFAULT NULL COMMENT '帖子创建时间',
                         `update_time` DATETIME DEFAULT NULL COMMENT '帖子最后更新时间',
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='帖子信息表';

-- ----------------------------
-- Records of posts
-- ----------------------------
BEGIN;
INSERT INTO `post` (`user_id`, `title`, `content`, `create_time`, `update_time`)
VALUES
    (1, '欢迎来到论坛', '这是一个用户1发表的帖子内容，欢迎大家积极讨论。', NOW(), NOW()),
    (1, '关于论坛的使用规则', '这里是一些论坛使用规则，请大家遵守。', NOW(), NOW()),
    (2, '如何提高编程技能', '这是用户2发表的关于如何提高编程技能的讨论帖，欢迎大家分享经验。', NOW(), NOW()),
    (2, '今日足球赛事讨论', '这里讨论今日的足球赛事，欢迎喜欢足球的朋友们加入。', NOW(), NOW());
COMMIT;