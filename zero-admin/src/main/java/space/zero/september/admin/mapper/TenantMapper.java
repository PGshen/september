package space.zero.september.admin.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import space.zero.september.admin.entity.Tenant;

import java.util.List;
import java.util.Map;

/**
 * @author : penggs
 * @program : september
 * @description : 租户mapper
 * @create : 2019-07-18 13:27
 */
public interface TenantMapper extends BaseMapper<Tenant> {
    /**
     * 分页查询
     *
     * @param page 分页
     * @param condition 条件
     * @return java.util.List<com.eshore.broadband.exhibition.entity.BoothDO>
     * @author penggs
     * @date 2019-03-28
     */
    List<Tenant> selectTenant(Pagination page, Map condition);
}