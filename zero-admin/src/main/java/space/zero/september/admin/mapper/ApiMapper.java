package space.zero.september.admin.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;
import space.zero.september.admin.entity.Api;

import java.util.List;
import java.util.Map;

/**
 * @author : penggs
 * @program : september
 * @description : api mapper
 * @create : 2019-07-18 13:24
 */
public interface ApiMapper extends BaseMapper<Api> {
    /**
     * 分页查询
     *
     * @param page 分页
	 * @param condition 条件
     * @return java.util.List<space.zero.september.admin.entity.Api>
     * @author penggs
     * @date 2019-07-24
     */
    List<Api> selectApi(Pagination page, Map condition);

    /**
     * 获取菜单关联的API列表
     *
     * @param menuId 菜单ID
     * @return java.util.List<space.zero.september.admin.entity.Api>
     * @author penggs
     * @date 2019-07-25
     */
    List<Api> selectApiByMenuId(Long menuId);

    /**
     * 获取菜单关联的API ID列表
     *
     * @param menuId 菜单ID
     * @return java.util.List<java.lang.Long>
     * @author penggs
     * @date 2019-07-25
     */
    List<Long> selectApiIdByMenuId(Long menuId);

    /**
     * 获取客户端关联的API列表
     *
     * @param clientAppId 客户端ID
     * @return java.util.List<space.zero.september.admin.entity.Api>
     * @author penggs
     * @date 2019-07-25
     */
    List<Api> selectApiByClientAppId(Long clientAppId);

    /**
     * 获取客户端关联的API ID列表
     *
     * @param clientAppId 客户端ID
     * @param ip
     * @return java.util.List<java.lang.Long>
     * @author penggs
     * @date 2019-07-25
     */
    List<Long> selectApiIdByClientAppIdAndIp(@Param("clientAppId") Long clientAppId, @Param("ip") String ip);

    /**
     * 获取用户关联的API列表
     *
     * @param userId 用户ID
     * @return java.util.List<Api>
     * @author penggs
     * @date 2020-04-02
     */
    List<Api> selectApiByUserId(Long userId);

    /**
     * API子节点列表
     *
     * @param parentApiId 父节点ID
     * @return java.util.List<space.zero.september.admin.entity.Api>
     * @author penggs
     * @date 2019-07-26
     */
    List<Api> selectChildByParentId(Long parentApiId);

    /**
     * API树结构
     *
     * @param apiId API ID
     * @return space.zero.september.admin.entity.Api
     * @author penggs
     * @date 2019-07-26
     */
    Api selectApiTree(Long apiId);

    /**
     * 删除客户端IP关联的API
     *
     * @param clientAppId 客户端ID
	 * @param ip IP
     * @return int
     * @author penggs
     * @date 2019-08-04
     */
    int deleteClientIp(@Param("clientAppId")Long clientAppId, @Param("ip")String ip);
}