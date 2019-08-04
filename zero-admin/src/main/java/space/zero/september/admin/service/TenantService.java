package space.zero.september.admin.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import space.zero.september.admin.entity.Tenant;
import space.zero.september.common.core.Result;
import space.zero.september.common.core.param.ReqCond;

/**
 * @author : penggs
 * @program : september
 * @description : 租户service
 * @create : 2019-07-19 18:22
 */
public interface TenantService extends IService<Tenant> {
    /**
     * 通过ID获取租户
     *
     * @param id 租户ID
     * @return space.zero.september.common.core.Result<space.zero.september.admin.entity.Tenant>
     * @author penggs
     * @date 2019-07-21
     */
    Result<Tenant> getTenantById(Long id);

    /**
     * 分页获取
     *
     * @param reqCond 分页和条件
     * @return space.zero.september.common.core.Result<com.baomidou.mybatisplus.extension.plugins.pagination.Page<space.zero.september.admin.entity.Tenant>>
     * @author penggs
     * @date 2019-07-21
     */
    Result<Page<Tenant>> getTenant(ReqCond reqCond);

    /**
     * 保存租户
     *
     * @param tenant 租户
     * @return space.zero.september.common.core.Result<space.zero.september.admin.entity.Tenant>
     * @author penggs
     * @date 2019-07-21
     */
    Result<Tenant> saveTenant(Tenant tenant);

    /**
     * 删除租户
     *
     * @param id 租户ID
     * @return space.zero.september.common.core.Result<java.lang.Boolean>
     * @author penggs
     * @date 2019-07-21
     */
    Result<Boolean> removeTenant(Long id);

    /**
     * 更新租户
     *
     * @param id 租户ID
	 * @param tenant 租户
     * @return space.zero.september.common.core.Result<space.zero.september.admin.entity.Tenant>
     * @author penggs
     * @date 2019-07-21
     */
    Result<Tenant> updateTenant(Long id, Tenant tenant);
}