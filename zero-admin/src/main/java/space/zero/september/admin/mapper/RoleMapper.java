package space.zero.september.admin.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import space.zero.september.admin.entity.Role;

import java.util.List;
import java.util.Map;

/**
 * @author : penggs
 * @program : september
 * @description : 角色mapper
 * @create : 2019-07-18 13:27
 */
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 分页查询
     *
     * @param page 分页
	 * @param condition 条件
     * @return java.util.List<space.zero.september.admin.entity.Role>
     * @author penggs
     * @date 2019-07-24
     */
    List<Role> selectRole(Pagination page, Map condition);
    
    /** 
     * 查询用户的角色列表
     * 
     * @param userId 用户ID 
     * @return java.util.List<space.zero.september.admin.entity.Role> 
     * @author penggs 
     * @date 2019-07-25 
     */
    List<Role> selectRoleByUserId(Long userId);
    
    /** 
     * 查询用户的角色ID列表
     * 
     * @param userId 用户ID 
     * @return java.util.List<java.lang.Long> 
     * @author penggs 
     * @date 2019-07-25 
     */
    List<Long> selectRoleIdByUserId(Long userId);

    /**
     * 统计当前角色关联的用户数
     *
     * @param roleId 角色ID
     * @return java.lang.Long
     * @author penggs
     * @date 2019-08-03
     */
    Long countUserByRoleId(Long roleId);
}