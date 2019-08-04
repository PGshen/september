package space.zero.september.admin.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import space.zero.september.admin.entity.Tenant;
import space.zero.september.admin.mapper.TenantMapper;
import space.zero.september.admin.service.TenantService;
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
 * @description : 租户实现类
 * @create : 2019-07-19 18:31
 */
@Service
public class TenantServiceImpl extends ServiceImpl<TenantMapper, Tenant> implements TenantService {
    private Logger log = LoggerFactory.getLogger(TenantServiceImpl.class);

    @Autowired
    private ResultGen<Tenant> resultGen;

    /**
     * 通过ID
     *
     * @param id 租户ID
     * @return space.zero.september.common.core.Result<space.zero.september.admin.entity.Tenant>
     * @author penggs
     * @date 2019-07-21
     */
    @Override
    public Result<Tenant> getTenantById(Long id) {
        log.info("Get tenant: id = " + id);
        Tenant tenant = baseMapper.selectById(id);
        if (tenant == null){
            return resultGen.fail(BusinessCode.TENANT, ErrorCode.P301);
        }
        return resultGen.success(BusinessCode.TENANT, tenant);
    }

    /**
     * 分页查找
     *
     * @param reqCond 条件
     * @return space.zero.september.common.core.Result<com.baomidou.mybatisplus.extension.plugins.pagination.Page<space.zero.september.admin.entity.Tenant>>
     * @author penggs
     * @date 2019-07-21
     */
    @Override
    public Result<Page<Tenant>> getTenant(ReqCond reqCond) {
        log.info("Get tenant: condition : " + JSONObject.toJSONString(reqCond));
        ResultGen<Page<Tenant>> resultGen = new ResultGen<>();
        Page<Tenant> page = new Page<>(reqCond.getPage(), reqCond.getSize(), reqCond.getSort());
        Map<String, Object> condition = CommonUtil.getReqCond(reqCond);
        page.setRecords(baseMapper.selectTenant(page, condition));
        return resultGen.success(BusinessCode.TENANT, page);
    }

    /**
     * 保存
     *
     * @param tenant 租户
     * @return space.zero.september.common.core.Result<space.zero.september.admin.entity.Tenant>
     * @author penggs
     * @date 2019-07-21
     */
    @Override
    public Result<Tenant> saveTenant(Tenant tenant) {
        log.info("Save tenant: tenant = " + JSONObject.toJSONString(tenant));
        // 数据校验，不符就抛异常
        baseMapper.insert(tenant);
        return resultGen.success(BusinessCode.TENANT, tenant);
    }

    /**
     * 删除
     *
     * @param id 租户ID
     * @return space.zero.september.common.core.Result<java.lang.Boolean>
     * @author penggs
     * @date 2019-07-21
     */
    @Override
    public Result<Boolean> removeTenant(Long id) {
        log.info("Remove tenant: id = " + id);
        ResultGen<Boolean> resultGen = new ResultGen<>();
        baseMapper.deleteById(id);
        return resultGen.success(BusinessCode.TENANT, Boolean.TRUE);
    }

    /**
     * 更新
     *
     * @param id ID
	 * @param tenant 租户
     * @return space.zero.september.common.core.Result<space.zero.september.admin.entity.Tenant>
     * @author penggs
     * @date 2019-07-21
     */
    @Override
    public Result<Tenant> updateTenant(Long id, Tenant tenant) {
        log.info("Update tenant: id = " + id + ", tenant = " + JSONObject.toJSONString(tenant));
        tenant.setTenantId(id);
        baseMapper.updateById(tenant);
        return resultGen.success(BusinessCode.TENANT, tenant);
    }
}