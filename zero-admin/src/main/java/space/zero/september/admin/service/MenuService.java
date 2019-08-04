package space.zero.september.admin.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import space.zero.september.admin.entity.Menu;
import space.zero.september.common.core.Result;
import space.zero.september.common.core.param.ReqCond;

import java.util.List;

/**
 * @author : penggs
 * @program : september
 * @description : 菜单 service
 * @create : 2019-07-19 18:19
 */
public interface MenuService extends IService<Menu> {
    /**
     * 根据ID获取
     *
     * @param id 菜单ID
     * @return space.zero.september.common.core.Result<space.zero.september.admin.entity.Menu>
     * @author penggs
     * @date 2019-07-24
     */
    Result<Menu> getMenuById(Long id);

    /**
     * 分页获取
     *
     * @param reqCond 分页条件
     * @return space.zero.september.common.core.Result<com.baomidou.mybatisplus.plugins.Page<space.zero.september.admin.entity.Menu>>
     * @author penggs
     * @date 2019-07-24
     */
    Result<Page<Menu>> getMenu(ReqCond reqCond);

    /**
     * 保存菜单
     *
     * @param menu 菜单
     * @return space.zero.september.common.core.Result<space.zero.september.admin.entity.Menu>
     * @author penggs
     * @date 2019-07-24
     */
    Result<Menu> saveMenu(Menu menu);

    /**
     * 更新菜单
     *
     * @param id 菜单ID
	 * @param menu 菜单
     * @return space.zero.september.common.core.Result<space.zero.september.admin.entity.Menu>
     * @author penggs
     * @date 2019-07-24
     */
    Result<Menu> updateMenu(Long id, Menu menu);

    /**
     * 删除菜单
     *
     * @param id 菜单ID
     * @return space.zero.september.common.core.Result<java.lang.Boolean>
     * @author penggs
     * @date 2019-07-24
     */
    Result<Boolean> removeMenu(Long id);

    /**
     * 获取角色关联的菜单
     *
     * @param roleId 角色ID
     * @return java.util.List<space.zero.september.admin.entity.Menu>
     * @author penggs
     * @date 2019-07-25
     */
    List<Menu> getMenuByRoleId(Long roleId);

    /**
     * 获取角色关联的菜单ID
     *
     * @param roleId 角色ID
     * @return java.util.List<java.lang.Long>
     * @author penggs
     * @date 2019-07-25
     */
    List<Long> getMenuIdByRoleId(Long roleId);

    /**
     * 获取菜单树
     *
     * @param menuId 菜单节点ID
     * @return java.util.List<space.zero.september.admin.entity.Menu>
     * @author penggs
     * @date 2019-07-26
     */
    Menu getMenuTree(Long menuId);

    /**
     * 根据用户ID获取用户的菜单
     *
     * @param userId 用户ID
     * @return java.util.List<space.zero.september.admin.entity.Menu>
     * @author penggs
     * @date 2019-07-27
     */
    List<Menu> getUserMenuId(Long userId);

    /**
     * 获取指定用户的菜单权限标识
     *
     * @param userId 用户ID
     * @return java.util.List<java.lang.String>
     * @author penggs
     * @date 2019-07-28
     */
    List<String> getUserMenuPerm(Long userId);

}