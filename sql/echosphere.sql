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