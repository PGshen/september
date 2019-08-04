package space.zero.september.admin.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import space.zero.september.admin.entity.Organization;
import space.zero.september.admin.mapper.OrganizationMapper;
import space.zero.september.admin.service.OrganizationService;
import space.zero.september.admin.util.ResultGen;
import space.zero.september.common.core.Result;
import space.zero.september.common.core.param.ReqCond;
import space.zero.september.common.core.returncode.BusinessCode;
import space.zero.september.common.core.returncode.ErrorCode;
import space.zero.september.common.core.utils.CommonUtil;

import java.util.Map;

/**
 * @author : penggs
 * @program : september
 * @description : 组织机构实现类
 * @create : 2019-07-19 18:28
 */
@Service
public class OrganizationServiceImpl extends ServiceImpl<OrganizationMapper, Organization> implements OrganizationService {
    private Logger log = LoggerFactory.getLogger(OrganizationServiceImpl.class);

    @Autowired
    private ResultGen<Organization> resultGen;

    /**
     * 根据ID查
     *
     * @param id 组织ID
     * @return space.zero.september.common.core.Result<space.zero.september.admin.entity.Organization>
     * @author penggs
     * @date 2019-07-24
     */
    @Override
    public Result<Organization> getOrgById(Long id) {
        log.info("Get organization: id = " + id);
        Organization organization = baseMapper.selectById(id);
        if (organization == null){
            return resultGen.fail(BusinessCode.ORGANIZATION, ErrorCode.P301);
        }
        return resultGen.success(BusinessCode.ORGANIZATION, organization);
    }

    /**
     * 分页查询
     *
     * @param reqCond 条件
     * @return space.zero.september.common.core.Result<com.baomidou.mybatisplus.plugins.Page<space.zero.september.admin.entity.Organization>>
     * @author penggs
     * @date 2019-07-24
     */
    @Override
    public Result<Page<Organization>> getOrg(ReqCond reqCond) {
        log.info("Get organization: condition : " + JSONObject.toJSONString(reqCond));
        ResultGen<Page<Organization>> resultGen = new ResultGen<>();
        Page<Organization> page = new Page<>(reqCond.getPage(), reqCond.getSize(), reqCond.getSort());
        Map<String, Object> condition = CommonUtil.getReqCond(reqCond);
        page.setRecords(baseMapper.selectOrganization(page, condition));
        return resultGen.success(BusinessCode.ORGANIZATION, page);
    }

    /**
     * 保存组织
     *
     * @param organization 组织
     * @return space.zero.september.common.core.Result<space.zero.september.admin.entity.Organization>
     * @author penggs
     * @date 2019-07-24
     */
    @Override
    public Result<Organization> saveOrg(Organization organization) {
        log.info("Save organization: organization = " + JSONObject.toJSONString(organization));
        // 数据校验，不符就抛异常
        baseMapper.insert(organization);
        return resultGen.success(BusinessCode.ORGANIZATION, organization);
    }

    /**
     * 更新组织
     *
     * @param id 组织ID
	 * @param organization 组织
     * @return space.zero.september.common.core.Result<space.zero.september.admin.entity.Organization>
     * @author penggs
     * @date 2019-07-24
     */
    @Override
    public Result<Organization> updateOrg(Long id, Organization organization) {
        log.info("Update organization: id = " + id + ", organization = " + JSONObject.toJSONString(organization));
        organization.setOrgId(id);
        baseMapper.updateById(organization);
        return resultGen.success(BusinessCode.ORGANIZATION, organization);
    }

    /**
     * 删除组织
     *
     * @param id 组织ID
     * @return space.zero.september.common.core.Result<java.lang.Boolean>
     * @author penggs
     * @date 2019-07-24
     */
    @Override
    public Result<Boolean> removeOrg(Long id) {
        log.info("Remove organization: id = " + id);
        ResultGen<Boolean> resultGen = new ResultGen<>();
        baseMapper.deleteById(id);
        return resultGen.success(BusinessCode.ORGANIZATION, Boolean.TRUE);
    }

    /**
     * 组织树
     *
     * @param orgId 组织ID
     * @return space.zero.september.admin.entity.Organization
     * @author penggs
     * @date 2019-07-26
     */
    @Override
    public Organization getOrgTree(Long orgId) {
        log.info("Get Organization tree: orgId = " + orgId);
        return baseMapper.selectOrgTree(orgId);
    }
}