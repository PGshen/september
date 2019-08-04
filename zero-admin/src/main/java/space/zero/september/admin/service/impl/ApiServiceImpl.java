package space.zero.september.admin.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import space.zero.september.admin.entity.Api;
import space.zero.september.admin.mapper.ApiMapper;
import space.zero.september.admin.service.ApiService;
import space.zero.september.admin.util.CollectionUtil;
import space.zero.september.admin.util.ResultGen;
import space.zero.september.admin.vo.ClientApiVo;
import space.zero.september.admin.vo.MenuApiVo;
import space.zero.september.common.core.Result;
import space.zero.september.common.core.param.ReqCond;
import space.zero.september.common.core.returncode.BusinessCode;
import space.zero.september.common.core.returncode.ErrorCode;
import space.zero.september.common.core.utils.CommonUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author : penggs
 * @program : september
 * @description : 接口service 实现类
 * @create : 2019-07-18 13:34
 */
@Service
public class ApiServiceImpl extends ServiceImpl<ApiMapper, Api> implements ApiService {
    private Logger log = LoggerFactory.getLogger(ApiServiceImpl.class);

    @Autowired
    private ResultGen<Api> resultGen;

    /**
     * 根据ID获取
     *
     * @param id API ID
     * @return space.zero.september.common.core.Result<space.zero.september.admin.entity.Api>
     * @author penggs
     * @date 2019-07-24
     */
    @Override
    public Result<Api> getApiById(Long id) {
        log.info("Get Api: id = " + id);
        Api api = baseMapper.selectById(id);
        if (api == null){
            return resultGen.fail(BusinessCode.API, ErrorCode.P301);
        }
        return resultGen.success(BusinessCode.API, api);
    }

    /**
     * 分页获取
     *
     * @param reqCond 条件
     * @return space.zero.september.common.core.Result<com.baomidou.mybatisplus.plugins.Page<space.zero.september.admin.entity.Api>>
     * @author penggs
     * @date 2019-07-24
     */
    @Override
    public Result<Page<Api>> getApi(ReqCond reqCond) {
        log.info("Get api: condition : " + JSONObject.toJSONString(reqCond));
        ResultGen<Page<Api>> resultGen = new ResultGen<>();
        Page<Api> page = new Page<>(reqCond.getPage(), reqCond.getSize(), reqCond.getSort());
        Map<String, Object> condition = CommonUtil.getReqCond(reqCond);
        page.setRecords(baseMapper.selectApi(page, condition));
        return resultGen.success(BusinessCode.API, page);
    }

    /**
     * 保存API
     *
     * @param api API
     * @return space.zero.september.common.core.Result<space.zero.september.admin.entity.Api>
     * @author penggs
     * @date 2019-07-24
     */
    @Override
    public Result<Api> saveApi(Api api) {
        log.info("Save api: api = " + JSONObject.toJSONString(api));
        // 数据校验，不符就抛异常
        baseMapper.insert(api);
        return resultGen.success(BusinessCode.API, api);
    }

    /**
     * 删除API
     *
     * @param id API id
     * @return space.zero.september.common.core.Result<java.lang.Boolean>
     * @author penggs
     * @date 2019-07-24
     */
    @Override
    public Result<Boolean> removeApi(Long id) {
        log.info("Remove api: id = " + id);
        ResultGen<Boolean> resultGen = new ResultGen<>();
        if (1 == baseMapper.deleteById(id)){
            return resultGen.success(BusinessCode.API, Boolean.TRUE);
        } else {
            return resultGen.fail(BusinessCode.API, ErrorCode.P302);
        }
    }

    /**
     * 更新API
     *
     * @param id ID
	 * @param api API
     * @return space.zero.september.common.core.Result<space.zero.september.admin.entity.Api>
     * @author penggs
     * @date 2019-07-24
     */
    @Override
    public Result<Api> updateApi(Long id, Api api) {
        log.info("Update api: id = " + id + ", api = " + JSONObject.toJSONString(api));
        api.setApiId(id);
        baseMapper.updateById(api);
        return resultGen.success(BusinessCode.API, api);
    }

    /**
     * 获取菜单关联的API列表
     *
     * @param menuId 菜单ID
     * @return java.util.List<space.zero.september.admin.entity.Api>
     * @author penggs
     * @date 2019-07-25
     */
    @Override
    public List<Api> getApiByMenuId(Long menuId) {
        return baseMapper.selectApiByMenuId(menuId);
    }

    /**
     * 获取菜单关联的API ID列表
     *
     * @param menuId 菜单ID
     * @return java.util.List<java.lang.Long>
     * @author penggs
     * @date 2019-07-25
     */
    @Override
    public List<Long> getApiIdByMenuId(Long menuId) {
        return baseMapper.selectApiIdByMenuId(menuId);
    }

    /**
     * 获取客户端关联的API列表
     *
     * @param clientAppId 客户端ID
     * @return java.util.List<space.zero.september.admin.entity.Api>
     * @author penggs
     * @date 2019-07-25
     */
    @Override
    public List<Api> getApiByClientAppId(Long clientAppId) {
        return baseMapper.selectApiByClientAppId(clientAppId);
    }

    /**
     * 获取客户端关联的API ID列表
     *
     * @param clientAppId 客户端ID
     * @param ip
     * @return java.util.List<java.lang.Long>
     * @author penggs
     * @date 2019-07-25
     */
    @Override
    public List<Long> getApiIdByClientAppIdAndIp(Long clientAppId, String ip) {
        return baseMapper.selectApiIdByClientAppIdAndIp(clientAppId, ip);
    }

    /**
     * api 树结构
     *
     * @param apiId API ID
     * @return space.zero.september.admin.entity.Api
     * @author penggs
     * @date 2019-07-26
     */
    @Override
    public Api getApiTree(Long apiId) {
        log.info("Get Api tree: apiId = " + apiId);
        return baseMapper.selectApiTree(apiId);
    }

    /**
     * 返回 已关联和未关联的API树结构
     *
     * @param menuId 菜单ID
     * @return space.zero.september.admin.vo.MenuApiVo
     * @author penggs
     * @date 2019-08-03
     */
    @Override
    public Result<MenuApiVo> getMenuApiVo(Long menuId){
        ResultGen<MenuApiVo> resultGen = new ResultGen<>();
        Api apiTree = getApiTree(1L);
        List<Api> apiList = new ArrayList<>();
        apiList.add(apiTree);
        List<Long> menuApiIds = baseMapper.selectApiIdByMenuId(menuId);
        List<Api> fromData;
        List<Api> toData;
        try {
            fromData = CollectionUtil.deepCopy(apiList);
            toData = CollectionUtil.deepCopy(apiList);
        } catch (IOException | ClassNotFoundException e) {
            return resultGen.fail(BusinessCode.MENU, ErrorCode.P305);
        }
        cutApiTree(false,fromData, menuApiIds);
        cutApiTree(true, toData, menuApiIds);
        MenuApiVo menuApiVo = new MenuApiVo();
        menuApiVo.setFromData(fromData);
        menuApiVo.setToData(toData);
        return resultGen.success(BusinessCode.MENU, menuApiVo);
    }

    /**
     * 根据客户端ID和IP 获取 已关联和未关联的API树结构
     *
     * @param clientAppId 客户端ID
	 * @param ip IP
     * @return space.zero.september.common.core.Result<space.zero.september.admin.vo.ClientApiVo>
     * @author penggs
     * @date 2019-08-04
     */
    @Override
    public Result<ClientApiVo> getClientApiVo(Long clientAppId, String ip) {
        List<Long> menuApiIds = baseMapper.selectApiIdByClientAppIdAndIp(clientAppId, ip);
        List<Api> fromData;
        List<Api> toData;
        ResultGen<ClientApiVo> resultGen = new ResultGen<>();
        Api apiTree = getApiTree(1L);
        List<Api> apiList = new ArrayList<>();
        apiList.add(apiTree);
        try {
            fromData = CollectionUtil.deepCopy(apiList);
            toData = CollectionUtil.deepCopy(apiList);
        } catch (IOException | ClassNotFoundException e) {
            return resultGen.fail(BusinessCode.CLIENT_APP, ErrorCode.P305);
        }
        cutApiTree(false,fromData, menuApiIds);
        cutApiTree(true, toData, menuApiIds);
        ClientApiVo clientApiVo = new ClientApiVo();
        clientApiVo.setFromData(fromData);
        clientApiVo.setToData(toData);
        return resultGen.success(BusinessCode.CLIENT_APP, clientApiVo);
    }

    /**
     * 删除客户端IP关联的全部API
     *
     * @param clientAppId 客户端ID
	 * @param ip IP
     * @return space.zero.september.common.core.Result<java.lang.Boolean>
     * @author penggs
     * @date 2019-08-04
     */
    @Override
    public Result<Boolean> deleteClientIp(Long clientAppId, String ip) {
        ResultGen<Boolean> resultGen = new ResultGen<>();
        int resultCount = baseMapper.deleteClientIp(clientAppId, ip);
        if (resultCount > 0){
            return resultGen.success(BusinessCode.CLIENT_APP, Boolean.TRUE);
        } else {
            return resultGen.fail(BusinessCode.CLIENT_APP, ErrorCode.P301, Boolean.FALSE);
        }
    }

    /**
     * 裁剪API树，保留当前菜单关联的API
     *
     * @param flag true：保留关联的API  false：保留未关联的
     * @param apis API树
     * @param apiIds 菜单关联的API
     * @author penggs
     * @date 2019-08-03
     */
    private void cutApiTree(boolean flag, List<Api> apis, List<Long> apiIds){
        apis.forEach(api -> {
            if (api.getChildren() != null) {
                cutApiTree(flag, api.getChildren(), apiIds);
            }
        });
        if (flag) {
            apis.removeIf(api -> api.getChildren().size() == 0 && !apiIds.contains(api.getApiId()));
        } else {
            apis.removeIf(api -> api.getChildren().size() == 0 && apiIds.contains(api.getApiId()));
        }
    }
}