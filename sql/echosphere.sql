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
INSERT INTO `user` VALUES (1, 'admin', '管理员', 0, '201507802@qq.com', 'avatar-20200806032259161.png', '/Users/jie/Documents/work/me/admin/eladmin/~/avatar/avatar-20200806032259161.png', '$2a$10$.fvUlg749k35zrxC0eSF.uDI1E0saKJ1BKoUpXZ/TMoDTspToBs2y', b'1', 1, NULL, 'admin', '2020-05-03 16:38:31', '2018-08-23 09:11:56', '2020-09-05 10:43:31');
INSERT INTO `user` VALUES (2, 'test', '测试', 1, '231@qq.com', NULL, NULL, '$2a$10$4XcyudOYTSz6fue6KFNMHeUQnCX5jbBQypLEnGk1PmekXt5c95JcK', b'0', 1, 'admin', 'admin', NULL, '2020-05-05 11:15:49', '2020-09-05 10:43:38');
COMMIT;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
                        `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                        `name` varchar(100) NOT NULL COMMENT '名称',
                        `level` int(50) DEFAULT NULL COMMENT '角色级别',
                        `description` varchar(255) DEFAULT NULL COMMENT '描述',
                        `data_scope` varchar(255) DEFAULT NULL COMMENT '数据权限',
                        `create_by` varchar(255) DEFAULT NULL COMMENT '创建者',
                        `update_by` varchar(255) DEFAULT NULL COMMENT '更新者',
                        `create_time` datetime DEFAULT NULL COMMENT '创建日期',
                        `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                        PRIMARY KEY (`role_id`) USING BTREE,
                        UNIQUE KEY `uniq_name` (`name`),
                        KEY `role_name_index` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 ROW_FORMAT=COMPACT COMMENT='角色表';

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
                        `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
                        `pid` bigint(20) DEFAULT NULL COMMENT '上级菜单ID',
                        `sub_count` int(5) DEFAULT 0 COMMENT '子菜单数目',
                        `type` int(11) DEFAULT NULL COMMENT '菜单类型',
                        `title` varchar(100) DEFAULT NULL COMMENT '菜单标题',
                        `name` varchar(100) DEFAULT NULL COMMENT '组件名称',
                        `component` varchar(255) DEFAULT NULL COMMENT '组件',
                        `menu_sort` int(5) DEFAULT NULL COMMENT '排序',
                        `icon` varchar(255) DEFAULT NULL COMMENT '图标',
                        `path` varchar(255) DEFAULT NULL COMMENT '链接地址',
                        `i_frame` bit(1) DEFAULT NULL COMMENT '是否外链',
                        `cache` bit(1) DEFAULT b'0' COMMENT '缓存',
                        `hidden` bit(1) DEFAULT b'0' COMMENT '隐藏',
                        `permission` varchar(255) DEFAULT NULL COMMENT '权限',
                        `create_by` varchar(255) DEFAULT NULL COMMENT '创建者',
                        `update_by` varchar(255) DEFAULT NULL COMMENT '更新者',
                        `create_time` datetime DEFAULT NULL COMMENT '创建日期',
                        `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                        PRIMARY KEY (`menu_id`) USING BTREE,
                        UNIQUE KEY `uniq_title` (`title`),
                        UNIQUE KEY `uniq_name` (`name`),
                        KEY `inx_pid` (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=118 ROW_FORMAT=COMPACT COMMENT='系统菜单';

-- ----------------------------
-- Table structure for roles_menus
-- ----------------------------
DROP TABLE IF EXISTS `roles_menus`;
CREATE TABLE `roles_menus` (
                               `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
                               `role_id` bigint(20) NOT NULL COMMENT '角色ID',
                               PRIMARY KEY (`menu_id`,`role_id`) USING BTREE,
                               KEY `FKcngg2qadojhi3a651a5adkvbq` (`role_id`) USING BTREE
) ENGINE=InnoDB ROW_FORMAT=COMPACT COMMENT='角色菜单关联';

-- ----------------------------
-- Table structure for users_roles
-- ----------------------------
DROP TABLE IF EXISTS `users_roles`;
CREATE TABLE `users_roles` (
                               `user_id` bigint(20) NOT NULL COMMENT '用户ID',
                               `role_id` bigint(20) NOT NULL COMMENT '角色ID',
                               PRIMARY KEY (`user_id`,`role_id`) USING BTREE,
                               KEY `FKq4eq273l04bpu4efj0jd0jb98` (`role_id`) USING BTREE
) ENGINE=InnoDB ROW_FORMAT=COMPACT COMMENT='用户角色关联';

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
-- Table structure for like
-- ----------------------------
DROP TABLE IF EXISTS `user_like`;
CREATE TABLE `user_like` (
                        `id` BIGINT NOT NULL AUTO_INCREMENT,
                        `user_id` BIGINT NOT NULL COMMENT '点赞用户的ID',
                        `post_id` BIGINT DEFAULT NULL COMMENT '被点赞的帖子ID，与comment_id互斥',
                        `comment_id` BIGINT DEFAULT NULL COMMENT '被点赞的评论ID，与post_id互斥',
                        `create_time` DATETIME NOT NULL COMMENT '点赞时间',
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `unique_like` (`user_id`, `post_id`, `comment_id`) COMMENT '保证用户对同一帖子或评论只能点赞一次'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='点赞信息表';