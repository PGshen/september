package space.zero.september.admin.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author : penggs
 * @program : september
 * @description : 菜单
 * @create : 2019-07-15 20:57
 */
@Data
@TableName("sys_menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = -873714269853734416L;
    /**
     * 菜单ID
     */
    @TableId(value = "menu_id", type = IdType.AUTO)
    private Long menuId;
    /**
     * 父菜单ID
     */
    private Long parentMenuId;
    /**
     * 级联路径
     */
    private String cascadePath;
    /**
     * 菜单名称（英文）
     */
    private String menuName;
    /**
     * 菜单显示名称（中文）
     */
    private String title;
    /**
     * 图标的URL
     */
    private String icon;
    /**
     * 权限标识（用于页面，格式如sys:user:list,sys:user:save）
     */
    private String perm;
    /**
     * 菜单类型（0：目录   1：菜单   2：按钮或其他可点击的元素）
     */
    private Integer type;
    /**
     * 菜单排序
     */
    private Integer orderNum;
    /**
     * 是否隐藏菜单(1:隐藏 0:不隐藏)
     */
    private Integer hidden;
    /**
     * 是否作为子菜单显示(1:是 0:否)
     */
    private Integer alwaysShow;
    /**
     * 组件路径
     */
    private String component;
    /**
     * 菜单的path（vue的）
     */
    private String path;
    /**
     * 重定向路径，默认不重定向'noredirect'
     */
    private String redirect;
    /**
     * 删除标识
     */
    @TableLogic
    @JsonIgnore
    private Integer isDel;
    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createdTime;
    /**
     * 更新时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 子节点列表
     */
    @TableField(exist = false)
    private List<Menu> children;
}