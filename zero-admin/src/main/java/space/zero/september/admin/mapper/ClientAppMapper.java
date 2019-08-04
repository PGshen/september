package space.zero.september.admin.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import space.zero.september.admin.entity.ClientApp;

import java.util.List;
import java.util.Map;

/**
 * @author : penggs
 * @program : september
 * @description : 客户端mapper
 * @create : 2019-07-18 13:25
 */
public interface ClientAppMapper extends BaseMapper<ClientApp> {
    /**
     * 分页查询
     *
     * @param page 分页
	 * @param condition 条件
     * @return java.util.List<space.zero.september.admin.entity.ClientApp>
     * @author penggs
     * @date 2019-07-24
     */
    List<ClientApp> selectClientApp(Pagination page, Map condition);

    /**
     * 客户端绑定的IP
     *
     * @param clientAppId 客户端ID
     * @return java.util.List<java.lang.String>
     * @author penggs
     * @date 2019-08-04
     */
    List<String> selectIpByClientAppId(Long clientAppId);
}