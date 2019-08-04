package space.zero.september.admin.web;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import space.zero.september.admin.entity.ClientApp;
import space.zero.september.admin.entity.ClientAppApi;
import space.zero.september.admin.service.ApiService;
import space.zero.september.admin.service.ClientAppApiService;
import space.zero.september.admin.service.ClientAppService;
import space.zero.september.admin.util.ResultGen;
import space.zero.september.admin.vo.ClientAuth;
import space.zero.september.common.core.Result;
import space.zero.september.common.core.param.ReqCond;
import space.zero.september.common.core.returncode.BusinessCode;
import space.zero.september.common.core.returncode.ErrorCode;

import java.util.ArrayList;

/**
 * @author : penggs
 * @program : september
 * @description : 客户端web
 * @create : 2019-07-19 18:34
 */
@RestController
@RequestMapping("/client")
@Api(value = "client", tags = {"客户端模块"})
public class ClientAppWeb {
    @Autowired
    private ClientAppService clientAppService;
    @Autowired
    private ApiService apiService;
    @Autowired
    private ClientAppApiService clientAppApiService;

    @ApiOperation(value = "[客户端]按ID获取客户端", notes = "返回 指定ID的客户端", produces = "application/xml,application/json", tags = { "客户端模块" }, response = Result.class)
    @GetMapping("/{id}")
    public Result get(@PathVariable Long id){
        return clientAppService.getClientAppById(id);
    }

    @ApiOperation(value = "[客户端]分页获取客户端", notes = "返回 分页后的客户端列表", produces = "application/xml,application/json", tags = { "客户端模块" }, response = Result.class)
    @PostMapping("/list")
    public Result list(@RequestBody ReqCond reqCond){
        return clientAppService.getClientApp(reqCond);
    }

    @ApiOperation(value = "[客户端]新增客户端", notes = "返回 新增的客户端", produces = "application/xml,application/json", tags = { "客户端模块" }, response = Result.class)
    @PostMapping("")
    public Result add(@RequestBody ClientApp clientApp) {
        return clientAppService.saveClientApp(clientApp);
    }

    @ApiOperation(value = "[客户端]删除客户端", notes = "返回 是否成功删除", produces = "application/xml,application/json", tags = { "客户端模块" }, response = Result.class)
    @DeleteMapping("/{id}")
    public Result remove(@PathVariable Long id) {
        return clientAppService.removeClientApp(id);
    }

    @ApiOperation(value = "[客户端]按ID更新客户端", notes = "返回 更新后的客户端", produces = "application/xml,application/json", tags = { "客户端模块" }, response = Result.class)
    @PutMapping("/{id}")
    public Result update(@PathVariable Long id, @RequestBody ClientApp clientApp) {
        return clientAppService.updateClientApp(id, clientApp);
    }

    @ApiOperation(value = "[客户端]获取客户端关联的API", notes = "返回 客户端关联API", produces = "application/xml,application/json", tags = { "客户端模块" }, response = Result.class)
    @PostMapping("/api/{clientAppId}")
    public Result getClientApiVo(@PathVariable Long clientAppId, @RequestBody ClientAuth clientAuth){
        return apiService.getClientApiVo(clientAppId, clientAuth.getIp());
    }

    @ApiOperation(value = "[客户端]删除客户端IP关联的API", notes = "返回 是否成功", produces = "application/xml,application/json", tags = { "客户端模块" }, response = Result.class)
    @PostMapping("/api/ip/{clientAppId}")
    public Result delClientApiVo(@PathVariable Long clientAppId, @RequestBody ClientAuth clientAuth){
        return apiService.deleteClientIp(clientAppId, clientAuth.getIp());
    }

    @ApiOperation(value = "[客户端]客户端绑定API", notes = "返回 是否成功", produces = "application/xml,application/json", tags = { "客户端模块" }, response = Result.class)
    @PutMapping("/api")
    public Result menuAuth(@RequestBody ClientAuth clientAuth){
        ResultGen<Boolean> resultGen = new ResultGen<>();
        clientAppApiService.bindApi(clientAuth.getClientAppId(), clientAuth.getIp(),clientAuth.getApiIds());
        return resultGen.success(BusinessCode.CLIENT_APP, Boolean.TRUE);
    }

    @ApiOperation(value = "[客户端]获取客户端绑定的IP", notes = "返回 客户端绑定的IP", produces = "application/xml,application/json", tags = { "客户端模块" }, response = Result.class)
    @GetMapping("/ip/{clientAppId}")
    public Result getClientIp(@PathVariable Long clientAppId){
        return clientAppService.getClientIp(clientAppId);
    }

    @ApiOperation(value = "[客户端]客户端绑定IP", notes = "返回 是否成功", produces = "application/xml,application/json", tags = { "客户端模块" }, response = Result.class)
    @PostMapping("/ip")
    public Result addClientIp(@RequestBody ClientAuth clientAuth){
        ResultGen<Boolean> resultGen = new ResultGen<>();
        // 校验IP是否已存在
        int count = clientAppApiService.selectCount(new EntityWrapper<ClientAppApi>().eq("client_app_id", clientAuth.getClientAppId()).eq("ip", clientAuth.getIp()));
        if (count > 0){
            return resultGen.fail(BusinessCode.CLIENT_APP, ErrorCode.P306, Boolean.FALSE);
        }
        // 添加一个apiId为零的记录
        clientAuth.setApiIds(new ArrayList<>());
        return menuAuth(clientAuth);
    }
}