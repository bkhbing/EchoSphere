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
 * 系统菜单
 * </p>
 *
 * @author bkhb
 * @since 2024-03-28 00:05:57
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("menu")
public class Menu {

    /**
     * ID
     */
    @TableId(value = "menu_id", type = IdType.AUTO)
    private Long menuId;

    /**
     * 上级菜单ID
     */
    @TableField("pid")
    private Long pid;

    /**
     * 子菜单数目
     */
    @TableField("sub_count")
    private Integer subCount;

    /**
     * 菜单类型
     */
    @TableField("`type`")
    private Integer type;

    /**
     * 菜单标题
     */
    @TableField("title")
    private String title;

    /**
     * 组件名称
     */
    @TableField("`name`")
    private String name;

    /**
     * 组件
     */
    @TableField("`component`")
    private String component;

    /**
     * 排序
     */
    @TableField("menu_sort")
    private Integer menuSort;

    /**
     * 图标
     */
    @TableField("icon")
    private String icon;

    /**
     * 链接地址
     */
    @TableField("`path`")
    private String path;

    /**
     * 是否外链
     */
    @TableField("i_frame")
    private Boolean iFrame;

    /**
     * 缓存
     */
    @TableField("`cache`")
    private Boolean cache;

    /**
     * 隐藏
     */
    @TableField("hidden")
    private Boolean hidden;

    /**
     * 权限
     */
    @TableField("permission")
    private String permission;

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
