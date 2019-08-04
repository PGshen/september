package space.zero.september.admin.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import space.zero.september.admin.entity.Organization;

import java.util.List;
import java.util.Map;

/**
 * @author : penggs
 * @program : september
 * @description : 组织机构mapper
 * @create : 2019-07-18 13:26
 */
public interface OrganizationMapper extends BaseMapper<Organization> {
    /**
     * 分页查询
     *
     * @param page 分页
	 * @param condition 条件
     * @return java.util.List<space.zero.september.admin.entity.Organization>
     * @author penggs
     * @date 2019-07-24
     */
    List<Organization> selectOrganization(Pagination page, Map condition);

    /**
     * 递归子节点列表
     *
     * @param parentOrgId 父节点ID
     * @return java.util.List<space.zero.september.admin.entity.Organization>
     * @author penggs
     * @date 2019-07-26
     */
    List<Organization> selectChildByParentId(Long parentOrgId);

    /**
     * 获取组织树
     *
     * @param orgId 组织ID
     * @return space.zero.september.admin.entity.Organization
     * @author penggs
     * @date 2019-07-26
     */
    Organization selectOrgTree(Long orgId);
}