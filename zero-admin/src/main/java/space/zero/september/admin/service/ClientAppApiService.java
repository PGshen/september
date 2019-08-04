package space.zero.september.admin.service;

import com.baomidou.mybatisplus.service.IService;
import space.zero.september.admin.entity.ClientAppApi;

import java.util.List;

/**
 * @author : penggs
 * @program : september
 * @description : 客户端-API关联 service
 * @create : 2019-07-19 18:18
 */
public interface ClientAppApiService extends IService<ClientAppApi> {
    /**
     * 客户端绑定IP和API
     *
     * @param clientAppId 客户端ID
     * @param ip IP
	 * @param apiIds API ID列表
     * @author penggs
     * @date 2019-07-25
     */
    void bindApi(Long clientAppId, String ip, List<Long> apiIds);

    /**
     * 取消关联
     *
     * @param clientAppId 客户端ID
     * @param ip ip
     * @author penggs
     * @date 2019-07-25
     */
    void disassociateClientAppApi(Long clientAppId, String ip);

    /**
     * 新增关联
     *
     * @param clientAppId 客户端ID
     * @param ip IP
	 * @param apiIds API ID列表
     * @author penggs
     * @date 2019-07-25
     */
    void associateClientAppApi(Long clientAppId, String ip, List<Long> apiIds);
}