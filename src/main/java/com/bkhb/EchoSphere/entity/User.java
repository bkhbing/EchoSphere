package com.bkhb.EchoSphere.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统用户
 * </p>
 *
 * @author bkhb
 * @since 2024-03-26 00:57:57
 */
@Builder
@Getter
@Setter
@Accessors(chain = true)
@TableName("sys_user")
public class User {

    /**
     * ID
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    /**
     * 用户名
     */
    @TableField("username")
    private String username;

    /**
     * 昵称
     */
    @TableField("nick_name")
    private String nickName;

    /**
     * 性别
     */
    @TableField("gender")
    private String gender;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 头像地址
     */
    @TableField("avatar_name")
    private String avatarName;

    /**
     * 头像真实路径
     */
    @TableField("avatar_path")
    private String avatarPath;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 是否为admin账号
     */
    @TableField("is_admin")
    private Boolean isAdmin;

    /**
     * 状态：1启用、0禁用
     */
    @TableField("enabled")
    private Boolean enabled;

    /**
     * 创建者
     */
    @TableField("create_by")
    private String createBy;

    /**
     * 更新者
     */
    @TableField("update_by")
    private String updateBy;

    /**
     * 修改密码的时间
     */
    @TableField("pwd_reset_time")
    private LocalDateTime pwdResetTime;

    /**
     * 创建日期
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;
}
