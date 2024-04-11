DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
                        `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
                        `username` varchar(180) NOT NULL COMMENT '用户名',
                        `nick_name` varchar(255) DEFAULT NULL COMMENT '昵称',
                        `gender` INT(2) DEFAULT 0 COMMENT '性别 1男性 2女性 0未知',
                        `email` varchar(180) NOT NULL COMMENT '邮箱',
                        `avatar_name` varchar(255) DEFAULT NULL COMMENT '头像地址',
                        `avatar_path` varchar(255) DEFAULT NULL COMMENT '头像真实路径',
                        `password` varchar(255) NOT NULL COMMENT '密码',
                        `is_admin` TINYINT(1) DEFAULT 0 COMMENT '是否为admin账号',
                        `enabled` TINYINT(1) DEFAULT 1 COMMENT '状态：1启用、0禁用',
                        `create_by` varchar(255) DEFAULT NULL COMMENT '创建者',
                        `update_by` varchar(255) DEFAULT NULL COMMENT '更新者',
                        `pwd_reset_time` datetime DEFAULT NULL COMMENT '修改密码的时间',
                        `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
                        `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                        PRIMARY KEY (`user_id`),
                        UNIQUE KEY `uniq_username` (`username`),
                        UNIQUE KEY `uniq_email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统用户';

BEGIN;
INSERT INTO `user` VALUES (1, 'admin', '管测试理员', 0, '201507802@qq.com', 'avatar-20200806032259161.png', '/Users/jie/Documents/work/me/admin/eladmin/~/avatar/avatar-20200806032259161.png', '$2a$10$.fvUlg749k35zrxC0eSF.uDI1E0saKJ1BKoUpXZ/TMoDTspToBs2y', b'1', 1, NULL, 'admin', '2020-05-03 16:38:31', '2018-08-23 09:11:56', '2020-09-05 10:43:31');
INSERT INTO `user` VALUES (2, 'test', '测试', 1, '231@qq.com', NULL, NULL, '$2a$10$4XcyudOYTSz6fue6KFNMHeUQnCX5jbBQypLEnGk1PmekXt5c95JcK', b'0', 1, 'admin', 'admin', NULL, '2020-05-05 11:15:49', '2020-09-05 10:43:38');
COMMIT;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
                        `role_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
                        `name` varchar(100) NOT NULL COMMENT '名称',
                        `level` int DEFAULT NULL COMMENT '角色级别',
                        `description` varchar(255) DEFAULT NULL COMMENT '描述',
                        `data_scope` varchar(255) DEFAULT NULL COMMENT '数据权限',
                        `create_by` varchar(255) DEFAULT NULL COMMENT '创建者',
                        `update_by` varchar(255) DEFAULT NULL COMMENT '更新者',
                        `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
                        `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                        PRIMARY KEY (`role_id`),
                        UNIQUE KEY `uniq_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
                        `menu_id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
                        `pid` bigint DEFAULT NULL COMMENT '上级菜单ID',
                        `sub_count` int DEFAULT 0 COMMENT '子菜单数目',
                        `type` int DEFAULT NULL COMMENT '菜单类型',
                        `title` varchar(100) NOT NULL COMMENT '菜单标题',
                        `name` varchar(100) NOT NULL COMMENT '组件名称',
                        `component` varchar(255) DEFAULT NULL COMMENT '组件',
                        `menu_sort` int DEFAULT NULL COMMENT '排序',
                        `icon` varchar(255) DEFAULT NULL COMMENT '图标',
                        `path` varchar(255) DEFAULT NULL COMMENT '链接地址',
                        `i_frame` TINYINT(1) DEFAULT 0 COMMENT '是否外链',
                        `cache` TINYINT(1) DEFAULT 0 COMMENT '缓存',
                        `hidden` TINYINT(1) DEFAULT 0 COMMENT '隐藏',
                        `permission` varchar(255) DEFAULT NULL COMMENT '权限',
                        `create_by` varchar(255) DEFAULT NULL COMMENT '创建者',
                        `update_by` varchar(255) DEFAULT NULL COMMENT '更新者',
                        `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
                        `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                        PRIMARY KEY (`menu_id`),
                        UNIQUE KEY `uniq_title` (`title`),
                        UNIQUE KEY `uniq_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统菜单';

-- ----------------------------
-- Table structure for roles_menus
-- ----------------------------
DROP TABLE IF EXISTS `roles_menus`;
CREATE TABLE `roles_menus` (
                               `menu_id` bigint NOT NULL COMMENT '菜单ID',
                               `role_id` bigint NOT NULL COMMENT '角色ID',
                               PRIMARY KEY (`menu_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色菜单关联';

-- ----------------------------
-- Table structure for users_roles
-- ----------------------------
DROP TABLE IF EXISTS `users_roles`;
CREATE TABLE `users_roles` (
                               `user_id` bigint NOT NULL COMMENT '用户ID',
                               `role_id` bigint NOT NULL COMMENT '角色ID',
                               PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色关联';

-- ----------------------------
-- Table structure for posts
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post` (
                        `post_id` BIGINT NOT NULL AUTO_INCREMENT,
                        `user_id` BIGINT NOT NULL COMMENT '发布用户的ID',
                        `title` VARCHAR(255) NOT NULL COMMENT '帖子标题',
                        `content` TEXT NOT NULL COMMENT '帖子内容',
                        `status` TINYINT(1) DEFAULT 0 COMMENT '状态：1正常、0禁用',
                        `view_count` INT DEFAULT 0 COMMENT '帖子浏览量',
                        `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '帖子创建时间',
                        `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '帖子最后更新时间',
                        PRIMARY KEY (`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='帖子信息表';

BEGIN;
INSERT INTO `post` (`user_id`, `title`, `content`, `create_time`, `update_time`)
VALUES
    (1, '欢迎来到论坛', '这是一个用户1发表的帖子内容，欢迎大家积极讨论。', NOW(), NOW()),
    (1, '关于论坛的使用规则', '这里是一些论坛使用规则，请大家遵守。', NOW(), NOW()),
    (2, '如何提高编程技能', '这是用户2发表的关于如何提高编程技能的讨论帖，欢迎大家分享经验。', NOW(), NOW()),
    (2, '今日足球赛事讨论', '这里讨论今日的足球赛事，欢迎喜欢足球的朋友们加入。', NOW(), NOW());
COMMIT;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
                           `comment_id` BIGINT NOT NULL AUTO_INCREMENT,
                           `user_id` BIGINT NOT NULL COMMENT '发布评论用户的ID',
                           `target_id` BIGINT NOT NULL COMMENT '目标用户ID',
                           `entity_type` INT DEFAULT 0 COMMENT '评论类型：0评论、1回复',
                           `entity_id` BIGINT NOT NULL COMMENT '评论或者帖子ID',
                           `content` TEXT NOT NULL COMMENT '评论内容',
                           `status` TINYINT(1) DEFAULT 1 COMMENT '状态：1正常、0禁用',
                           `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                           PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='帖子信息表';

BEGIN;
INSERT INTO `comment` (`user_id`, `target_id`, `entity_type`, `entity_id`, `content`, `create_time`)
VALUES
    (2, 1, 0, 1, '这是对帖子1的第1个评论', NOW()),
    (1, 2, 1, 1, '这是对帖子1的第1个回复', NOW()),
    (2, 1, 1, 1, '这是对帖子1的第2个回复', NOW()),
    (1, 2, 0, 3, '这是对帖子3的第1个评论', NOW()),
    (2, 1, 1, 3, '这是对帖子3的第1个回复', NOW());
COMMIT;

-- ----------------------------
-- Table structure for praise
-- ----------------------------
DROP TABLE IF EXISTS `praise`;
CREATE TABLE `praise` (
                          `praise_id` BIGINT NOT NULL AUTO_INCREMENT,
                          `user_id` BIGINT NOT NULL COMMENT '点赞用户的ID',
                          `post_id` BIGINT DEFAULT NULL COMMENT '被点赞的帖子ID，与comment_id互斥',
                          `comment_id` BIGINT DEFAULT NULL COMMENT '被点赞的评论ID，与post_id互斥',
                          `create_time` DATETIME NOT NULL COMMENT '点赞时间',
                          PRIMARY KEY (`praise_id`),
                          UNIQUE KEY `unique_like` (`user_id`, `post_id`, `comment_id`) COMMENT '保证用户对同一帖子或评论只能点赞一次'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='点赞信息表';

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
                           `message_id` bigint NOT NULL AUTO_INCREMENT COMMENT '私信ID',
                           `sender_id` bigint NOT NULL COMMENT '发送者用户ID',
                           `receiver_id` bigint NOT NULL COMMENT '接收者用户ID',
                           `content` text NOT NULL COMMENT '消息内容',
                           `status` TINYINT(1) DEFAULT 0 COMMENT '消息状态（0: 未读, 1: 已读）',
                           `sender_remove` TINYINT(1) DEFAULT 0 COMMENT '发送人是否删除（0: 否, 1: 是）',
                           `receiver_remove` TINYINT(1) DEFAULT 0 COMMENT '接受人是否删除（0: 否, 1: 是）',
                           `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '发送时间',
                           PRIMARY KEY (`message_id`),
                           KEY `idx_sender_id` (`sender_id`),
                           KEY `idx_receiver_id` (`receiver_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='私信表';

INSERT INTO `message` (`sender_id`, `receiver_id`, `content`, `status`, `sender_remove`, `receiver_remove`, `create_time`)
VALUES
    (1, 2, '你好，很高兴认识你！', 0, 0, 0, NOW()),
    (2, 1, '我也很高兴认识你！', 0, 0, 0, NOW()),
    (1, 2, '最近怎么样？', 1, 0, 0, NOW()),
    (2, 1, '我很好，你呢？', 1, 0, 0, NOW()),
    (1, 2, '我也不错。', 1, 0, 0, NOW()),
    (2, 1, '有空一起出来玩啊。', 0, 0, 0, NOW()),
    (1, 2, '好的，这个周末吧。', 0, 0, 0, NOW()),
    (1, 2, '这条消息我会删除的。', 1, 1, 0, NOW()),
    (2, 1, '这条消息接收者会删除。', 1, 0, 1, NOW());


-- ----------------------------
-- Table structure for chat
-- ----------------------------
DROP TABLE IF EXISTS `chat`;
CREATE TABLE `chat` (
                        `chat_id` bigint NOT NULL AUTO_INCREMENT COMMENT '聊天室ID',
                        `user1_id` bigint NOT NULL COMMENT '用户ID',
                        `user2_id` bigint NOT NULL COMMENT '用户ID',
                        `last_message` text NOT NULL COMMENT '最后一条消息的内容',
                        `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                        PRIMARY KEY (`chat_id`),
                        KEY `idx_user1_id` (`user1_id`),
                        KEY `idx_user2_id` (`user2_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='聊天室表';

# INSERT INTO `chat` (`user1_id`, `user2_id`, `create_time`)
# VALUES
#   (1, 2,NOW());

-- ----------------------------
-- Table structure for event_remind
-- ----------------------------
DROP TABLE IF EXISTS `event_remind`;
CREATE TABLE `event_remind` (
                                `event_remind_id` bigint NOT NULL AUTO_INCREMENT COMMENT '消息 ID',
                                `action` TINYINT(1) NOT NULL COMMENT '动作类型，如0=评论、1=点赞',
                                `source_id` bigint NOT NULL COMMENT '事件源 ID，如评论 ID、文章 ID 等',
                                `source_type` varchar(20) NOT NULL COMMENT '事件源类型："Comment"、"Post"等',
                                `source_content` text not null COMMENT '事件源的内容，比如回复的内容，回复的评论等等',
                                `url` varchar(20) default NULL COMMENT '事件所发生的地点链接 url',
                                `status` TINYINT(1) DEFAULT 0 COMMENT '消息状态（0: 未读, 1: 已读）',
                                `sender_id` bigint NOT NULL COMMENT '操作者的 ID，即谁关注了你，at 了你',
                                `recipient_id` bigint NOT NULL COMMENT '接受通知的用户的 ID',
                                `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                PRIMARY KEY (`event_remind_id`),
                                KEY `idx_sender_id` (`sender_id`),
                                KEY `idx_recipient_id` (`recipient_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='事件提醒表';

INSERT INTO `event_remind` (`action`, `source_id`, `source_type`, `source_content`, `url`, `status`, `sender_id`, `recipient_id`, `create_time`)
VALUES
    (0, 1, 'Post', '这是一篇很棒的文章！', '/posts/1', 0, 1, 2, NOW()),
    (1, 1, 'Post', '为你的文章点赞了！', '/posts/1', 0, 2, 1, NOW()),
    (0, 2, 'Comment', '非常同意你的观点！', '/posts/1/comments/2', 0, 2, 1, NOW()),
    (1, 2, 'Comment', '为你的评论点赞了！', '/posts/1/comments/2', 0, 1, 2, NOW()),
    (0, 3, 'Post', '这篇文章写得真好。', '/posts/3', 0, 2, 1, NOW()),
    (1, 3, 'Post', '为你的文章点赞了！', '/posts/3', 0, 1, 2, NOW()),
    (0, 4, 'Comment', '这个观点我很赞同。', '/posts/2/comments/4', 0, 1, 2, NOW()),
    (1, 4, 'Comment', '为你的评论点赞了！', '/posts/2/comments/4', 0, 2, 1, NOW()),
    (0, 5, 'Post', '文章写得不错，学到了很多。', '/posts/5', 0, 1, 2, NOW()),
    (1, 5, 'Post', '为你的文章点赞了！', '/posts/5', 0, 2, 1, NOW());

