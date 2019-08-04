package space.zero.september.admin.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import space.zero.september.admin.entity.Role;
import space.zero.september.common.core.Result;
import space.zero.september.common.core.param.ReqCond;

import java.util.List;

/**
 * @author : penggs
 * @program : september
 * @description : 角色service
 * @create : 2019-07-19 18:20
 */
public interface RoleService extends IService<Role> {
    /**
     * 根据ID获取
     *
     * @param id 角色ID
     * @return space.zero.september.common.core.Result<space.zero.september.admin.entity.Role>
     * @author penggs
     * @date 2019-07-24
     */
    Result<Role> getRoleById(Long id);

    /**
     * 分页查询
     *
	 * @param reqCond 分页条件
     * @return space.zero.september.common.core.Result<com.baomidou.mybatisplus.plugins.Page<space.zero.september.admin.entity.Role>>
     * @author penggs
     * @date 2019-07-24
     */
    Result<Page<Role>> getRole(ReqCond reqCond);

    /**
     * 保存角色
     *
     * @param role 角色
     * @return space.zero.september.common.core.Result<space.zero.september.admin.entity.Role>
     * @author penggs
     * @date 2019-07-24
     */
    Result<Role> saveRole(Role role);

    /**
     * 更新角色
     *
     * @param id 角色ID
	 * @param role 角色
     * @return space.zero.september.common.core.Result<space.zero.september.admin.entity.Role>
     * @author penggs
     * @date 2019-07-24
     */
    Result<Role> updateRole(Long id, Role role);

    /**
     * 删除角色
     *
     * @param id 角色ID
     * @return space.zero.september.common.core.Result<java.lang.Boolean>
     * @author penggs
     * @date 2019-07-24
     */
    Result<Boolean> removeRole(Long id);

    /**
     * 获取用户的角色
     *
     * @param userId 用户ID
     * @return java.util.List<space.zero.september.admin.entity.Role>
     * @author penggs
     * @date 2019-07-25
     */
    List<Role> getRoleByUserId(Long userId);

    /**
     * 获取用户角色ID列表
     *
     * @param userId 用户ID
     * @return java.util.List<java.lang.Long>
     * @author penggs
     * @date 2019-07-25
     */
    List<Long> getRoleIdByUserId(Long userId);
}