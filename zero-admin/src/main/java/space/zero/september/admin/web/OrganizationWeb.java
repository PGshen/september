package space.zero.september.admin.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import space.zero.september.admin.entity.Organization;
import space.zero.september.admin.entity.Tenant;
import space.zero.september.admin.service.OrganizationService;
import space.zero.september.admin.service.TenantService;
import space.zero.september.admin.util.ResultGen;
import space.zero.september.common.core.Result;
import space.zero.september.common.core.param.ReqCond;
import space.zero.september.common.core.returncode.BusinessCode;

/**
 * @author : penggs
 * @program : september
 * @description : 组织机构web
 * @create : 2019-07-19 18:36
 */
@RestController
@RequestMapping("/org")
@Api(value = "org", tags = { "组织机构模块" })
public class OrganizationWeb {
    @Autowired
    private OrganizationService organizationService;

    @ApiOperation(value = "[组织机构]按ID获取组织机构", notes = "返回 指定ID的组织机构", produces = "application/xml,application/json", tags = { "组织机构模块" }, response = Result.class)
    @GetMapping("/{id}")
    public Result get(@PathVariable Long id){
        return organizationService.getOrgById(id);
    }

    @ApiOperation(value = "[组织机构]分页获取组织机构", notes = "返回 分页后的组织机构列表", produces = "application/xml,application/json", tags = { "组织机构模块" }, response = Result.class)
    @PostMapping("/list")
    public Result list(@RequestBody ReqCond reqCond){
        return organizationService.getOrg(reqCond);
    }

    @ApiOperation(value = "[组织机构]新增组织机构", notes = "返回 新增的组织机构", produces = "application/xml,application/json", tags = { "组织机构模块" }, response = Result.class)
    @PostMapping("")
    public Result add(@RequestBody Organization org) {
        return organizationService.saveOrg(org);
    }

    @ApiOperation(value = "[组织机构]删除组织机构", notes = "返回 是否成功删除", produces = "application/xml,application/json", tags = { "组织机构模块" }, response = Result.class)
    @DeleteMapping("/{id}")
    public Result remove(@PathVariable Long id) {
        return organizationService.removeOrg(id);
    }

    @ApiOperation(value = "[组织机构]按ID更新组织机构", notes = "返回 更新后的组织机构", produces = "application/xml,application/json", tags = { "组织机构模块" }, response = Result.class)
    @PutMapping("/{id}")
    public Result update(@PathVariable Long id, @RequestBody Organization org) {
        return organizationService.updateOrg(id, org);
    }

    @ApiOperation(value = "[组织机构]按ID获取组织树", notes = "返回 组织树结构", produces = "application/xml,application/json", tags = { "组织机构模块" }, response = Result.class)
    @GetMapping("/tree/{orgId}")
    public Result getTree(@PathVariable Long orgId){
        Organization organization = organizationService.getOrgTree(orgId);
        ResultGen<Organization> resultGen = new ResultGen<>();
        return resultGen.success(BusinessCode.ORGANIZATION, organization);
    }
}