package space.zero.september.admin.web;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import space.zero.september.admin.entity.Role;
import space.zero.september.admin.service.MenuService;
import space.zero.september.admin.service.RoleMenuService;
import space.zero.september.admin.service.RoleService;
import space.zero.september.admin.service.RoleService;
import space.zero.september.admin.util.ResultGen;
import space.zero.september.admin.vo.RoleAuth;
import space.zero.september.common.core.Result;
import space.zero.september.common.core.param.ReqCond;
import space.zero.september.common.core.returncode.BusinessCode;

import java.util.List;

/**
 * @author : penggs
 * @program : september
 * @description : 角色web
 * @create : 2019-07-19 18:35
 */
@RestController
@RequestMapping("/role")
@Api(value = "role", tags = { "角色模块" })
public class RoleWeb {
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private RoleMenuService roleMenuService;

    @ApiOperation(value = "[角色]按ID获取角色", notes = "返回 指定ID的角色", produces = "application/xml,application/json", tags = { "角色模块" }, response = Result.class)
    @GetMapping("/{id}")
    public Result get(@PathVariable Long id){
        return roleService.getRoleById(id);
    }

    @ApiOperation(value = "[角色]分页获取角色", notes = "返回 分页后的角色列表", produces = "application/xml,application/json", tags = { "角色模块" }, response = Result.class)
    @PostMapping("/list")
    public Result list(@RequestBody ReqCond reqCond){
        return roleService.getRole(reqCond);
    }

    @ApiOperation(value = "[角色]获取全部角色", notes = "返回 角色列表", produces = "application/xml,application/json", tags = { "角色模块" }, response = Result.class)
    @GetMapping("/all")
    public Result all(){
        ResultGen<List<Role>> resultGen = new ResultGen<>();
        return resultGen.success(BusinessCode.ROLE, roleService.selectList(new EntityWrapper<>()));
    }

    @ApiOperation(value = "[角色]新增角色", notes = "返回 新增的角色", produces = "application/xml,application/json", tags = { "角色模块" }, response = Result.class)
    @PostMapping("")
    public Result add(@RequestBody Role role) {
        return roleService.saveRole(role);
    }

    @ApiOperation(value = "[角色]删除角色", notes = "返回 是否成功删除", produces = "application/xml,application/json", tags = { "角色模块" }, response = Result.class)
    @DeleteMapping("/{id}")
    public Result remove(@PathVariable Long id) {
        return roleService.removeRole(id);
    }

    @ApiOperation(value = "[角色]按ID更新角色", notes = "返回 更新后的角色", produces = "application/xml,application/json", tags = { "角色模块" }, response = Result.class)
    @PutMapping("/{id}")
    public Result update(@PathVariable Long id, @RequestBody Role role) {
        return roleService.updateRole(id, role);
    }

    @ApiOperation(value = "[角色]按ID获取角色关联的菜单", notes = "返回 角色关联的菜单（包括按钮）", produces = "application/xml,application/json", tags = { "角色模块" }, response = Result.class)
    @GetMapping("/menu/{id}")
    public Result roleMenu(@PathVariable Long id){
        ResultGen<List<Long>> resultGen = new ResultGen<>();
        return resultGen.success(BusinessCode.ROLE, menuService.getMenuIdByRoleId(id));
    }

    @ApiOperation(value = "[角色]角色授权", notes = "返回 授权成功", produces = "application/xml,application/json", tags = { "角色模块" }, response = Result.class)
    @PutMapping("/auth")
    public Result roleAuth(@RequestBody RoleAuth roleAuth){
        ResultGen<Boolean> resultGen = new ResultGen<>();
        roleMenuService.authMenu(roleAuth.getRoleId(), roleAuth.getMenus());
        return resultGen.success(BusinessCode.ROLE, Boolean.TRUE);
    }
}