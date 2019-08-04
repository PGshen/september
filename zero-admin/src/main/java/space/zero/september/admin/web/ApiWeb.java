package space.zero.september.admin.web;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import space.zero.september.admin.entity.Api;
import space.zero.september.admin.service.ApiService;
import space.zero.september.admin.util.ResultGen;
import space.zero.september.common.core.Result;
import space.zero.september.common.core.param.ReqCond;
import space.zero.september.common.core.returncode.BusinessCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : penggs
 * @program : september
 * @description : api接口
 * @create : 2019-07-18 13:36
 */
@RestController
@RequestMapping("/api")
@io.swagger.annotations.Api(value = "api", tags = "API接口模块")
public class ApiWeb {
    @Autowired
    private ApiService apiService;

    @ApiOperation(value = "[API接口]按ID获取API接口", notes = "返回 指定ID的API接口", produces = "application/xml,application/json", tags = { "API接口模块" }, response = Result.class)
    @GetMapping("/{id}")
    public Result get(@PathVariable Long id){
        return apiService.getApiById(id);
    }

    @ApiOperation(value = "[API接口]分页获取API接口", notes = "返回 分页后的API接口列表", produces = "application/xml,application/json", tags = { "API接口模块" }, response = Result.class)
    @PostMapping("/list")
    public Result list(@RequestBody ReqCond reqCond){
        return apiService.getApi(reqCond);
    }

    @ApiOperation(value = "[API接口]新增API接口", notes = "返回 新增的API接口", produces = "application/xml,application/json", tags = { "API接口模块" }, response = Result.class)
    @PostMapping("")
    public Result add(@RequestBody Api api) {
        return apiService.saveApi(api);
    }

    @ApiOperation(value = "[API接口]删除API接口", notes = "返回 是否成功删除", produces = "application/xml,application/json", tags = { "API接口模块" }, response = Result.class)
    @DeleteMapping("/{id}")
    public Result remove(@PathVariable Long id) {
        return apiService.removeApi(id);
    }

    @ApiOperation(value = "[API接口]按ID更新API接口", notes = "返回 更新后的API接口", produces = "application/xml,application/json", tags = { "API接口模块" }, response = Result.class)
    @PutMapping("/{id}")
    public Result update(@PathVariable Long id, @RequestBody Api api) {
        return apiService.updateApi(id, api);
    }

    @ApiOperation(value = "[API接口]按ID获取API接口树", notes = "返回 API接口树结构", produces = "application/xml,application/json", tags = { "API接口模块" }, response = Result.class)
    @GetMapping("/tree/{apiId}")
    public Result getTree(@PathVariable Long apiId){
        Api api = apiService.getApiTree(apiId);
        ResultGen<Api> resultGen = new ResultGen<>();
        return resultGen.success(BusinessCode.API, api);
    }

    @ApiOperation(value = "[API接口]获取完整API接口树", notes = "返回 API接口树结构", produces = "application/xml,application/json", tags = { "API接口模块" }, response = Result.class)
    @GetMapping("/tree")
    public Result getAllApi(){
        Api api = apiService.getApiTree(1L);
        List<Api> apiList = new ArrayList<>();
        apiList.add(api);
        ResultGen<List<Api>> resultGen = new ResultGen<>();
        return resultGen.success(BusinessCode.API, apiList);
    }
}