package space.zero.september.admin.service;

import com.baomidou.mybatisplus.service.IService;
import space.zero.september.admin.entity.RoleMenu;

import java.util.List;

/**
 * @author : penggs
 * @program : september
 * @description : 角色菜单关联service
 * @create : 2019-07-19 18:21
 */
public interface RoleMenuService extends IService<RoleMenu> {
    /**
     * 授权菜单
     *
     * @param roleId 角色ID
	 * @param menuIds 菜单ID列表
     * @author penggs
     * @date 2019-07-25
     */
    void authMenu(Long roleId, List<Long> menuIds);

    /**
     * 解除关联
     *
     * @param roleId 角色ID
     * @author penggs
     * @date 2019-07-25
     */
    void disassociateRoleMenu(Long roleId);

    /**
     * 新增关联
     *
     * @param roleId 角色ID
	 * @param menuIds 菜单ID列表
     * @author penggs
     * @date 2019-07-25
     */
    void associateRoleMenu(Long roleId, List<Long> menuIds);
}