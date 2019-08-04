package space.zero.september.admin.vo;

import lombok.Data;

import java.util.List;

/**
 * @author : penggs
 * @program : september
 * @description : 角色-菜单关联
 * @create : 2019-08-03 11:53
 */
@Data
public class RoleAuth {
    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * 菜单ID列表
     */
    private List<Long> menus;
}