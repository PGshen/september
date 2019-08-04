package space.zero.september.admin.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import space.zero.september.admin.entity.Tenant;
import space.zero.september.admin.service.TenantService;
import space.zero.september.common.core.Result;
import space.zero.september.common.core.param.ReqCond;

/**
 * @author : penggs
 * @program : september
 * @description : 租户web
 * @create : 2019-07-19 18:36
 */
@RestController
@RequestMapping("/tenant")
@Api(value = "tenant", tags = { "租户模块" })
public class TenantWeb {
    @Autowired
    private TenantService tenantService;

    @ApiOperation(value = "[租户]按ID获取租户", notes = "返回 指定ID的租户", produces = "application/xml,application/json", tags = { "租户模块" }, response = Result.class)
    @GetMapping("/{id}")
    public Result get(@PathVariable Long id){
        return tenantService.getTenantById(id);
    }

    @ApiOperation(value = "[租户]分页获取租户", notes = "返回 分页后的租户列表", produces = "application/xml,application/json", tags = { "租户模块" }, response = Result.class)
    @PostMapping("/list")
    public Result list(@RequestBody ReqCond reqCond){
        return tenantService.getTenant(reqCond);
    }

    @ApiOperation(value = "[租户]新增租户", notes = "返回 新增的租户", produces = "application/xml,application/json", tags = { "租户模块" }, response = Result.class)
    @PostMapping("")
    public Result add(@RequestBody Tenant tenant) {
        return tenantService.saveTenant(tenant);
    }

    @ApiOperation(value = "[租户]删除租户", notes = "返回 是否成功删除", produces = "application/xml,application/json", tags = { "租户模块" }, response = Result.class)
    @DeleteMapping("/{id}")
    public Result remove(@PathVariable Long id) {
        return tenantService.removeTenant(id);
    }

    @ApiOperation(value = "[租户]按ID更新租户", notes = "返回 更新后的租户", produces = "application/xml,application/json", tags = { "租户模块" }, response = Result.class)
    @PutMapping("/{id}")
    public Result update(@PathVariable Long id, @RequestBody Tenant tenant) {
        return tenantService.updateTenant(id, tenant);
    }
}