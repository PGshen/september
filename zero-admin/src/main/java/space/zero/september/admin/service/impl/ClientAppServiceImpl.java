package space.zero.september.admin.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import space.zero.september.admin.entity.ClientApp;
import space.zero.september.admin.mapper.ClientAppMapper;
import space.zero.september.admin.service.ClientAppService;
import space.zero.september.admin.util.ResultGen;
import space.zero.september.admin.vo.ClientIpVo;
import space.zero.september.common.core.Result;
import space.zero.september.common.core.param.ReqCond;
import space.zero.september.common.core.returncode.BusinessCode;
import space.zero.september.common.core.returncode.ErrorCode;
import space.zero.september.common.core.utils.CommonUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author : penggs
 * @program : september
 * @description : 客户端实现类
 * @create : 2019-07-19 18:24
 */
@Service
public class ClientAppServiceImpl extends ServiceImpl<ClientAppMapper, ClientApp> implements ClientAppService {
    private Logger log = LoggerFactory.getLogger(ClientAppServiceImpl.class);

    @Autowired
    private ResultGen<ClientApp> resultGen;

    /**
     * 根据ID获取
     *
     * @param id 客户端id
     * @return space.zero.september.common.core.Result<space.zero.september.admin.entity.ClientApp>
     * @author penggs
     * @date 2019-07-24
     */
    @Override
    public Result<ClientApp> getClientAppById(Long id) {
        log.info("Get clientApp: id = " + id);
        ClientApp clientApp = baseMapper.selectById(id);
        if (clientApp == null){
            return resultGen.fail(BusinessCode.CLIENT_APP, ErrorCode.P301);
        }
        return resultGen.success(BusinessCode.CLIENT_APP, clientApp);
    }

    /**
     * 分页查询
     *
     * @param reqCond 条件
     * @return space.zero.september.common.core.Result<com.baomidou.mybatisplus.plugins.Page<space.zero.september.admin.entity.ClientApp>>
     * @author penggs
     * @date 2019-07-24
     */
    @Override
    public Result<Page<ClientApp>> getClientApp(ReqCond reqCond) {
        log.info("Get clientApp: condition : " + JSONObject.toJSONString(reqCond));
        ResultGen<Page<ClientApp>> resultGen = new ResultGen<>();
        Page<ClientApp> page = new Page<>(reqCond.getPage(), reqCond.getSize(), reqCond.getSort());
        Map<String, Object> condition = CommonUtil.getReqCond(reqCond);
        page.setRecords(baseMapper.selectClientApp(page, condition));
        return resultGen.success(BusinessCode.CLIENT_APP, page);
    }

    /**
     * 保存
     *
     * @param clientApp 客户端
     * @return space.zero.september.common.core.Result<space.zero.september.admin.entity.ClientApp>
     * @author penggs
     * @date 2019-07-24
     */
    @Override
    public Result<ClientApp> saveClientApp(ClientApp clientApp) {
        log.info("Save clientApp: clientApp = " + JSONObject.toJSONString(clientApp));
        // 数据校验，不符就抛异常
        baseMapper.insert(clientApp);
        return resultGen.success(BusinessCode.CLIENT_APP, clientApp);
    }

    /**
     * 更新
     *
     * @param id 客户端ID
	 * @param clientApp 客户端
     * @return space.zero.september.common.core.Result<space.zero.september.admin.entity.ClientApp>
     * @author penggs
     * @date 2019-07-24
     */
    @Override
    public Result<ClientApp> updateClientApp(Long id, ClientApp clientApp) {
        log.info("Update clientApp: id = " + id + ", clientApp = " + JSONObject.toJSONString(clientApp));
        clientApp.setClientAppId(id);
        baseMapper.updateById(clientApp);
        return resultGen.success(BusinessCode.CLIENT_APP, clientApp);
    }

    /**
     * 删除
     *
     * @param id 客户端ID
     * @return space.zero.september.common.core.Result<java.lang.Boolean>
     * @author penggs
     * @date 2019-07-24
     */
    @Override
    public Result<Boolean> removeClientApp(Long id) {
        log.info("Remove clientApp: id = " + id);
        ResultGen<Boolean> resultGen = new ResultGen<>();
        baseMapper.deleteById(id);
        return resultGen.success(BusinessCode.CLIENT_APP, Boolean.TRUE);
    }

    /**
     * 客户端绑定的IP
     *
     * @param id 客户端ID
     * @return space.zero.september.common.core.Result<space.zero.september.admin.vo.ClientIpVo>
     * @author penggs
     * @date 2019-08-04
     */
    @Override
    public Result<List<ClientIpVo>> getClientIp(Long id) {
        ResultGen<List<ClientIpVo>> resultGen = new ResultGen<>();
        List<String> ips = baseMapper.selectIpByClientAppId(id);
        List<ClientIpVo> clientIpVos = new ArrayList<>();
        ips.forEach(ip -> {
            clientIpVos.add(new ClientIpVo(ip));
        });
        return resultGen.success(BusinessCode.CLIENT_APP, clientIpVos);
    }
}