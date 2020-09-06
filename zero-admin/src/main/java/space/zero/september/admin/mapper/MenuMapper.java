package space.zero.september.admin.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Select;
import space.zero.september.admin.entity.Menu;

import java.util.List;
import java.util.Map;

/**
 * @author : penggs
 * @program : september
 * @description : 菜单mapper
 * @create : 2019-07-18 13:25
 */
public interface MenuMapper extends BaseMapper<Menu> {
    /**
     * 分页查询
     *
     * @param page 分页
	 * @param condition 条件
     * @return java.util.List<space.zero.september.admin.entity.Menu>
     * @author penggs
     * @date 2019-07-24
     */
    List<Menu> selectMenu(Pagination page, Map condition);

    /**
     * 查询角色关联的菜单列表
     *
     * @param roleId 角色ID
     * @return java.util.List<space.zero.september.admin.entity.Menu>
     * @author penggs
     * @date 2019-07-25
     */
    List<Menu> selectMenuByRoleId(Long roleId);

    /**
     * 查询角色关联的菜单ID列表
     *
     * @param roleId 角色ID
     * @return java.util.List<java.lang.Long>
     * @author penggs
     * @date 2019-07-25
     */
    List<Long> selectMenuIdByRoleId(Long roleId);

    /**
     * 递归获取子节点
     *
     * @param parentMenuId 父节点ID
     * @return java.util.List<space.zero.september.admin.entity.Menu>
     * @author penggs
     * @date 2019-07-26
     */
    List<Menu> selectChildByParentId(Long parentMenuId);

    /**
     * 获取菜单树
     *
     * @param menuId 菜单ID
     * @return space.zero.september.admin.entity.Menu
     * @author penggs
     * @date 2019-07-26
     */
    Menu selectMenuTree(Long menuId);

    /**
     * 获取用关联的菜单ID
     *
     * @param userId 用户ID
     * @return java.util.List<java.lang.String>
     * @author penggs
     * @date 2019-07-27
     */
    @Select("select distinct m.menu_id from t_sys_menu m, t_sys_user_role ur, t_sys_role_menu rm where ur.role_id = rm.role_id and rm.menu_id = m.menu_id and m.is_del = 0 and ur.user_id = #{userId}")
    List<Long> selectUserMenuId(Long userId);

    /**
     * 获取用关联的菜单权限标识
     *
     * @param userId 用户ID
     * @return java.util.List<java.lang.String>
     * @author penggs
     * @date 2019-07-27
     */
    @Select("select distinct m.perm from t_sys_menu m, t_sys_user_role ur, t_sys_role_menu rm where ur.role_id = rm.role_id and rm.menu_id = m.menu_id and m.is_del = 0 and ur.user_id = #{userId}")
    List<String> selectUserMenuPerm(Long userId);
}