package space.zero.september.admin.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import space.zero.september.admin.entity.Organization;
import space.zero.september.common.core.Result;
import space.zero.september.common.core.param.ReqCond;

/**
 * @author : penggs
 * @program : september
 * @description : 组织机构service
 * @create : 2019-07-19 18:20
 */
public interface OrganizationService extends IService<Organization> {
    /**
     * 根据ID获取
     *
     * @param id 组织ID
     * @return space.zero.september.common.core.Result<space.zero.september.admin.entity.Organization>
     * @author penggs
     * @date 2019-07-24
     */
    Result<Organization> getOrgById(Long id);

    /**
     * 分页查询
     *
     * @param reqCond 分页条件
     * @return space.zero.september.common.core.Result<com.baomidou.mybatisplus.plugins.Page<space.zero.september.admin.entity.Organization>>
     * @author penggs
     * @date 2019-07-24
     */
    Result<Page<Organization>> getOrg(ReqCond reqCond);

    /**
     * 保存组织机构
     *
     * @param organization 组织机构
     * @return space.zero.september.common.core.Result<space.zero.september.admin.entity.Organization>
     * @author penggs
     * @date 2019-07-24
     */
    Result<Organization> saveOrg(Organization organization);

    /**
     * 更新组织机构
     *
     * @param id 组织机构ID
	 * @param organization 组织机构
     * @return space.zero.september.common.core.Result<space.zero.september.admin.entity.Organization>
     * @author penggs
     * @date 2019-07-24
     */
    Result<Organization> updateOrg(Long id, Organization organization);

    /**
     * 删除组织机构
     *
     * @param id 组织机构ID
     * @return space.zero.september.common.core.Result<java.lang.Boolean>
     * @author penggs
     * @date 2019-07-24
     */
    Result<Boolean> removeOrg(Long id);

    /**
     * 组织机构树
     *
     * @param orgId 组织ID
     * @return space.zero.september.admin.entity.Organization
     * @author penggs
     * @date 2019-07-26
     */
    Organization getOrgTree(Long orgId);
}