package space.zero.september.admin.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import space.zero.september.admin.entity.ClientAppApi;
import space.zero.september.admin.mapper.ClientAppApiMapper;
import space.zero.september.admin.service.ClientAppApiService;

import java.util.List;

/**
 * @author : penggs
 * @program : september
 * @description : 客户端-API关联实现类
 * @create : 2019-07-19 18:25
 */
@Service
public class ClientAppApiServiceImpl extends ServiceImpl<ClientAppApiMapper, ClientAppApi> implements ClientAppApiService {
    /**
     * 客户端绑定IP和API
     *
     * @param clientAppId 客户端ID
	 * @param ip IP
	 * @param apiIds API ID列表
     * @author penggs
     * @date 2019-07-25
     */
    @Override
    public void bindApi(Long clientAppId, String ip, List<Long> apiIds) {
        disassociateClientAppApi(clientAppId, ip);
        associateClientAppApi(clientAppId, ip, apiIds);
    }

    /**
     * 取消关联
     *
     * @param clientAppId 客户端ID
     * @author penggs
     * @date 2019-07-25
     */
    @Override
    public void disassociateClientAppApi(Long clientAppId, String ip) {
        baseMapper.delete(new EntityWrapper<ClientAppApi>().eq("client_app_id", clientAppId).eq("ip", ip));
    }

    /**
     * 新增关联
     *
     * @param clientAppId 客户端ID
	 * @param ip IP
	 * @param apiIds API ID列表
     * @author penggs
     * @date 2019-07-25
     */
    @Override
    public void associateClientAppApi(Long clientAppId, String ip, List<Long> apiIds) {
        // 关联加上一个0 API，仅用于保存IP
        apiIds.add(0L);
        apiIds.forEach(apiId -> {
            baseMapper.insert(new ClientAppApi(clientAppId, ip, apiId));
        });
    }
}