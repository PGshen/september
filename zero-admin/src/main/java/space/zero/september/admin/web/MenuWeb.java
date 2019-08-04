package space.zero.september.admin.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import space.zero.september.admin.entity.Menu;
import space.zero.september.admin.service.ApiService;
import space.zero.september.admin.service.MenuApiService;
import space.zero.september.admin.service.MenuService;
import space.zero.september.admin.util.ResultGen;
import space.zero.september.admin.vo.MenuAuth;
import space.zero.september.common.core.Result;
import space.zero.september.common.core.param.ReqCond;
import space.zero.september.common.core.returncode.BusinessCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : penggs
 * @program : september
 * @description : 菜单web
 * @create : 2019-07-19 18:34
 */
@RestController
@RequestMapping("/menu")
@Api(value = "menu", tags = { "菜单模块" })
public class MenuWeb {
    @Autowired
    private MenuService menuService;
    @Autowired
    private ApiService apiService;
    @Autowired
    private MenuApiService menuApiService;

    @ApiOperation(value = "[菜单]按ID获取菜单", notes = "返回 指定ID的菜单", produces = "application/xml,application/json", tags = { "菜单模块" }, response = Result.class)
    @GetMapping("/{id}")
    public Result get(@PathVariable Long id){
        return menuService.getMenuById(id);
    }

    @ApiOperation(value = "[菜单]分页获取菜单", notes = "返回 分页后的菜单列表", produces = "application/xml,application/json", tags = { "菜单模块" }, response = Result.class)
    @PostMapping("/list")
    public Result list(@RequestBody ReqCond reqCond){
        return menuService.getMenu(reqCond);
    }

    @ApiOperation(value = "[菜单]新增菜单", notes = "返回 新增的菜单", produces = "application/xml,application/json", tags = { "菜单模块" }, response = Result.class)
    @PostMapping("")
    public Result add(@RequestBody Menu menu) {
        return menuService.saveMenu(menu);
    }

    @ApiOperation(value = "[菜单]删除菜单", notes = "返回 是否成功删除", produces = "application/xml,application/json", tags = { "菜单模块" }, response = Result.class)
    @DeleteMapping("/{id}")
    public Result remove(@PathVariable Long id) {
        return menuService.removeMenu(id);
    }

    @ApiOperation(value = "[菜单]按ID更新菜单", notes = "返回 更新后的菜单", produces = "application/xml,application/json", tags = { "菜单模块" }, response = Result.class)
    @PutMapping("/{id}")
    public Result update(@PathVariable Long id, @RequestBody Menu menu) {
        return menuService.updateMenu(id, menu);
    }

    @ApiOperation(value = "[菜单]按ID获取菜单树", notes = "返回 菜单树结构", produces = "application/xml,application/json", tags = { "菜单模块" }, response = Result.class)
    @GetMapping("/tree/{menuId}")
    public Result getTree(@PathVariable Long menuId){
        Menu menu = menuService.getMenuTree(menuId);
        ResultGen<Menu> resultGen = new ResultGen<>();
        return resultGen.success(BusinessCode.MENU, menu);
    }

    @ApiOperation(value = "[菜单]获取菜单树列表", notes = "返回 菜单列表", produces = "application/xml,application/json", tags = { "菜单模块" }, response = Result.class)
    @GetMapping("/tree")
    public Result getAllMenu(){
        Menu menuTree = menuService.getMenuTree(0L);
        List<Menu> menuList = new ArrayList<>();
        menuList.add(menuTree);
        ResultGen<List<Menu>> resultGen = new ResultGen<>();
        return resultGen.success(BusinessCode.MENU, menuList);
    }

    @ApiOperation(value = "[菜单]获取菜单关联的API", notes = "返回 菜单关联API", produces = "application/xml,application/json", tags = { "菜单模块" }, response = Result.class)
    @GetMapping("/api/{menuId}")
    public Result getMenuApiVo(@PathVariable Long menuId){
        return apiService.getMenuApiVo(menuId);
    }

    @ApiOperation(value = "[菜单]菜单绑定API", notes = "返回 是否成功", produces = "application/xml,application/json", tags = { "菜单模块" }, response = Result.class)
    @PutMapping("/api")
    public Result menuAuth(@RequestBody MenuAuth menuAuth){
        ResultGen<Boolean> resultGen = new ResultGen<>();
        menuApiService.bindApi(menuAuth.getMenuId(), menuAuth.getApiIds());
        return resultGen.success(BusinessCode.MENU, Boolean.TRUE);
    }









    /**
     * 获取当前用户的菜单树
     *
     * @return space.zero.september.common.core.Result
     * @author penggs
     * @date 2019-07-27
     */
    @GetMapping("/route")
    public Result getMenuTree(){
        // 用户ID用Spring Security 的缓存获取
        List<Menu> menuList = menuService.getUserMenuId(1L);
        ResultGen<List<Menu>> resultGen = new ResultGen<>();
        return resultGen.success(BusinessCode.MENU, menuList);
    }

    /**
     * 获取用户的菜单权限标识
     *
     * @return space.zero.september.common.core.Result
     * @author penggs
     * @date 2019-07-28
     */
    @GetMapping("/perm")
    public Result getMenuList(){
        List<String> menuPermList = menuService.getUserMenuPerm(1L);
        ResultGen<List<String>> resultGen = new ResultGen<>();
        return resultGen.success(BusinessCode.MENU, menuPermList);
    }
}