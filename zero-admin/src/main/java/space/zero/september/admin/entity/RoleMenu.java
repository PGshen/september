package space.zero.september.admin.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author : penggs
 * @program : september
 * @description : 角色菜单关联
 * @create : 2019-07-16 19:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_sys_role_menu")
public class RoleMenu implements Serializable {

    private static final long serialVersionUID = -2117290346189301499L;
    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * 菜单ID
     */
    private Long menuId;
}