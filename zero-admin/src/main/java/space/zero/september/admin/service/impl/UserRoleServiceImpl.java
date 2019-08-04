package space.zero.september.admin.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import space.zero.september.admin.entity.UserRole;
import space.zero.september.admin.mapper.UserRoleMapper;
import space.zero.september.admin.service.UserRoleService;

import java.util.List;

/**
 * @author : penggs
 * @program : september
 * @description : 用户角色关联实现类
 * @create : 2019-07-19 18:32
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
    /**
     * 给用户分配角色
     *
     * @param userId 用户ID
	 * @param roleIds 角色ID列表
     * @author penggs
     * @date 2019-07-25
     */
    @Override
    public void assignRole(Long userId, List<Long> roleIds) {
        disassociateUserRole(userId);
        associateUserRole(userId, roleIds);
    }

    /**
     * 删除关联
     *
     * @param userId 用户ID
     * @author penggs
     * @date 2019-07-25
     */
    @Override
    public void disassociateUserRole(Long userId) {
        baseMapper.delete(new EntityWrapper<UserRole>().eq("user_id", userId));
    }

    /**
     * 新增关联
     *
     * @param userId 用户ID
	 * @param roleIds 角色ID列表
     * @author penggs
     * @date 2019-07-25
     */
    @Override
    public void associateUserRole(Long userId, List<Long> roleIds) {
        roleIds.forEach(roleId -> {
            baseMapper.insert(new UserRole(userId, roleId));
        });
    }
}