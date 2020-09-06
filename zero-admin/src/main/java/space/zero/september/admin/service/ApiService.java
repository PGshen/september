package space.zero.september.admin.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import space.zero.september.admin.entity.Api;
import space.zero.september.admin.vo.ClientApiVo;
import space.zero.september.admin.vo.MenuApiVo;
import space.zero.september.common.core.Result;
import space.zero.september.common.core.param.ReqCond;

import java.util.List;

/**
 * @author : penggs
 * @program : september
 * @description : 接口service
 * @create : 2019-07-18 13:32
 */
public interface ApiService extends IService<Api> {
    /**
     * 根据ID获取
     *
     * @param id API ID
     * @return space.zero.september.common.core.Result<space.zero.september.admin.entity.Api>
     * @author penggs
     * @date 2019-07-24
     */
    Result<Api> getApiById(Long id);

    /**
     * 分页获取
     *
     * @param reqCond 分页和条件
     * @return space.zero.september.common.core.Result<com.baomidou.mybatisplus.plugins.Page<space.zero.september.admin.entity.Api>>
     * @author penggs
     * @date 2019-07-24
     */
    Result<Page<Api>> getApi(ReqCond reqCond);

    /**
     * 保存API
     *
     * @param api API
     * @return space.zero.september.common.core.Result<space.zero.september.admin.entity.Api>
     * @author penggs
     * @date 2019-07-24
     */
    Result<Api> saveApi(Api api);

    /**
     * 删除API
     *
     * @param id API ID
     * @return space.zero.september.common.core.Result<java.lang.Boolean>
     * @author penggs
     * @date 2019-07-24
     */
    Result<Boolean> removeApi(Long id);

    /**
     * 更新API
     *
     * @param id API ID
	 * @param api API
     * @return space.zero.september.common.core.Result<space.zero.september.admin.entity.Api>
     * @author penggs
     * @date 2019-07-24
     */
    Result<Api> updateApi(Long id, Api api);

    /**
     * 获取菜单关联的API列表
     *
     * @param menuId 菜单ID
     * @return java.util.List<space.zero.september.admin.entity.Api>
     * @author penggs
     * @date 2019-07-25
     */
    List<Api> getApiByMenuId(Long menuId);

    /**
     * 获取菜单关联的API ID列表
     *
     * @param menuId 菜单ID
     * @return java.util.List<java.lang.Long>
     * @author penggs
     * @date 2019-07-25
     */
    List<Long> getApiIdByMenuId(Long menuId);

    /**
     * 获取客户端关联的API列表
     *
     * @param clientAppId 客户端ID
     * @return java.util.List<space.zero.september.admin.entity.Api>
     * @author penggs
     * @date 2019-07-25
     */
    List<Api> getApiByClientAppId(Long clientAppId);

    /**
     * 获取客户端关联的API ID列表
     *
     * @param clientAppId 客户端ID
     * @param ip ip
     * @return java.util.List<java.lang.Long>
     * @author penggs
     * @date 2019-07-25
     */
    List<Long> getApiIdByClientAppIdAndIp(Long clientAppId, String ip);

    /**
     * 获取用户关联的API列表
     *
     * @param userId 用户ID
     * @return java.util.List<Api>
     * @author penggs
     * @date 2020-07-25
     */
    List<Api> getApiByUserId(Long userId);

    /**
     * api 树
     *
     * @param apiId API ID
     * @return space.zero.september.admin.entity.Api
     * @author penggs
     * @date 2019-07-26
     */
    Api getApiTree(Long apiId);

    /**
     * 返回 已关联和未关联的API树结构
     *
     * @param menuId 菜单ID
     * @return space.zero.september.admin.vo.MenuApiVo
     * @author penggs
     * @date 2019-08-03
     */
    Result<MenuApiVo> getMenuApiVo(Long menuId);

    /**
     * 返回 已关联和未关联的API树结构
     *
     * @param clientAppId 客户端ID
     * @param ip ip
     * @return space.zero.september.admin.vo.MenuApiVo
     * @author penggs
     * @date 2019-08-03
     */
    Result<ClientApiVo> getClientApiVo(Long clientAppId, String ip);

    /**
     * 删除客户端 IP关联的全部API
     *
     * @param clientAppId 客户端ID
	 * @param ip IP
     * @return space.zero.september.common.core.Result<java.lang.Boolean>
     * @author penggs
     * @date 2019-08-04
     */
    Result<Boolean> deleteClientIp(Long clientAppId, String ip);
}