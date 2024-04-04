-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
                        `id` BIGINT NOT NULL AUTO_INCREMENT,
                        `user_id` BIGINT NOT NULL COMMENT '发布评论用户的ID',
                        `target_id` BIGINT NOT NULL COMMENT '目标用户 ID',
                        `entity_type` INT DEFAULT 0 COMMENT '评论类型：0评论、1回复',
                        `entity_id` BIGINT NOT NULL COMMENT '评论或者帖子ID',
                        `content` TEXT NOT NULL COMMENT '评论内容',
                        `status`  bit(1) DEFAULT b'1' COMMENT '状态：1正常、0禁用',
                        `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='帖子信息表';

-- ----------------------------
-- Records of posts
-- ----------------------------
BEGIN;
INSERT INTO `comment` (`user_id`, `target_id`, `entity_type`, `entity_id`, `content`, `create_time`)
VALUES
    (1, 2, 0, 1, '这是对帖子1的第1个评论', NOW()),
    (2, 1, 0, 2, '这是对帖子2的第1个评论', NOW()),
    (2, 1, 1, 1, '这是对帖子1的第1个回复', NOW()),
    (1, 2, 1, 1, '这是对帖子1的第2个回复', NOW()),
    (1, 2, 1, 2, '这是对帖子2的第1个回复', NOW());
COMMIT;