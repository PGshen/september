package space.zero.september.admin.service;

import com.baomidou.mybatisplus.service.IService;
import space.zero.september.admin.entity.UserRole;

import java.util.List;

/**
 * @author : penggs
 * @program : september
 * @description : 用户角色关联service
 * @create : 2019-07-19 18:23
 */
public interface UserRoleService extends IService<UserRole> {
    /**
     * 分配角色
     *
     * @param userId 用户ID
	 * @param roleIds 角色ID列表
     * @author penggs
     * @date 2019-07-25
     */
    void assignRole(Long userId, List<Long> roleIds);

    /**
     * 删除用户-角色关联关系
     *
     * @param userId 用户ID
     * @author penggs
     * @date 2019-07-25
     */
    void disassociateUserRole(Long userId);

    /**
     * 新增用户-角色关联
     *
     * @param userId 用户ID
	 * @param roleIds 角色ID列表
     * @author penggs
     * @date 2019-07-25
     */
    void associateUserRole(Long userId, List<Long> roleIds);
}