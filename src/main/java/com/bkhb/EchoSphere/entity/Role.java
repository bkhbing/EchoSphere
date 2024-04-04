package com.bkhb.EchoSphere.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author bkhb
 * @since 2024-03-28 00:05:57
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("role")
public class Role {

    /**
     * ID
     */
    @TableId(value = "role_id", type = IdType.AUTO)
    private Long roleId;

    /**
     * 名称
     */
    @TableField("`name`")
    private String name;

    /**
     * 角色级别
     */
    @TableField("`level`")
    private Integer level;

    /**
     * 描述
     */
    @TableField("`description`")
    private String description;

    /**
     * 数据权限
     */
    @TableField("data_scope")
    private String dataScope;

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
     * 创建日期
     */
      @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;
}
