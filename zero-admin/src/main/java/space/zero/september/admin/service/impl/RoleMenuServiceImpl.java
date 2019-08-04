package space.zero.september.admin.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import space.zero.september.admin.entity.RoleMenu;
import space.zero.september.admin.mapper.RoleMenuMapper;
import space.zero.september.admin.service.RoleMenuService;

import java.util.List;

/**
 * @author : penggs
 * @program : september
 * @description : 角色菜单关联实现类
 * @create : 2019-07-19 18:30
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {
    /**
     * 授权菜单
     *
     * @param roleId 角色ID
	 * @param menuIds 菜单ID列表
     * @author penggs
     * @date 2019-07-25
     */
    @Override
    public void authMenu(Long roleId, List<Long> menuIds) {
        disassociateRoleMenu(roleId);
        associateRoleMenu(roleId, menuIds);
    }

    /**
     * 取消关联
     *
     * @param roleId 角色ID
     * @author penggs
     * @date 2019-07-25
     */
    @Override
    public void disassociateRoleMenu(Long roleId) {
        baseMapper.delete(new EntityWrapper<RoleMenu>().eq("role_id", roleId));
    }

    /**
     * 新增关联
     *
     * @param roleId 角色ID
	 * @param menuIds 菜单ID列表
     * @author penggs
     * @date 2019-07-25
     */
    @Override
    public void associateRoleMenu(Long roleId, List<Long> menuIds) {
        menuIds.forEach(menuId -> {
            baseMapper.insert(new RoleMenu(roleId, menuId));
        });
    }
}