package com.bkhb.EchoSphere.dto;

import com.bkhb.EchoSphere.entity.User;
import com.bkhb.EchoSphere.enums.GenderEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {
    /**
     * ID
     */
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 性别
     */
    private GenderEnum gender;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 头像地址
     */
    private String avatarName;

    /**
     * 头像真实路径
     */
    private String avatarPath;

    /**
     * 密码
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    /**
     * 是否为admin账号
     */
    private Boolean isAdmin;

    /**
     * 状态：1启用、0禁用
     */
    private Boolean enabled;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 修改密码的时间
     */
    private LocalDateTime pwdResetTime;

    /**
     * 创建日期
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 权限列表
     */
    private List<String> permissions;

    public UserDto(User user) {
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.nickName = user.getNickName();
        this.gender = user.getGender();
        this.email = user.getEmail();
        this.avatarName = user.getAvatarName();
        this.avatarPath = user.getAvatarPath();
        this.password = user.getPassword();
        this.isAdmin = user.getIsAdmin();
        this.enabled = user.getEnabled();
        this.createBy = user.getCreateBy();
        this.updateBy = user.getUpdateBy();
        this.pwdResetTime = user.getPwdResetTime();
        this.createTime = user.getCreateTime();
        this.updateTime = user.getUpdateTime();
    }
}
