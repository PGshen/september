package space.zero.september.admin.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import space.zero.september.admin.entity.ClientApp;
import space.zero.september.admin.vo.ClientIpVo;
import space.zero.september.common.core.Result;
import space.zero.september.common.core.param.ReqCond;

import java.util.List;

/**
 * @author : penggs
 * @program : september
 * @description : 外部客户端service
 * @create : 2019-07-19 18:17
 */
public interface ClientAppService extends IService<ClientApp> {
    /**
     * 根据ID获取
     *
     * @param id 客户端ID
     * @return space.zero.september.common.core.Result<space.zero.september.admin.entity.ClientApp>
     * @author penggs
     * @date 2019-07-24
     */
    Result<ClientApp> getClientAppById(Long id);

    /**
     * 分页查询
     *
     * @param reqCond 分页和条件
     * @return space.zero.september.common.core.Result<com.baomidou.mybatisplus.plugins.Page<space.zero.september.admin.entity.ClientApp>>
     * @author penggs
     * @date 2019-07-24
     */
    Result<Page<ClientApp>> getClientApp(ReqCond reqCond);

    /**
     * 保存客户端
     *
     * @param clientApp 客户端
     * @return space.zero.september.common.core.Result<space.zero.september.admin.entity.ClientApp>
     * @author penggs
     * @date 2019-07-24
     */
    Result<ClientApp> saveClientApp(ClientApp clientApp);

    /**
     * 更新客户端
     *
     * @param id 客户端ID
	 * @param clientApp 客户端
     * @return space.zero.september.common.core.Result<space.zero.september.admin.entity.ClientApp>
     * @author penggs
     * @date 2019-07-24
     */
    Result<ClientApp> updateClientApp(Long id, ClientApp clientApp);

    /**
     * 删除客户端
     *
     * @param id 客户端ID
     * @return space.zero.september.common.core.Result<java.lang.Boolean>
     * @author penggs
     * @date 2019-07-24
     */
    Result<Boolean> removeClientApp(Long id);

    /**
     * 获取客户端绑定的IP
     *
     * @param id 客户端ID
     * @return space.zero.september.common.core.Result<space.zero.september.admin.vo.ClientIpVo>
     * @author penggs
     * @date 2019-08-04
     */
    Result<List<ClientIpVo>> getClientIp(Long id);
}