package space.zero.september.admin.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import space.zero.september.admin.entity.Role;
import space.zero.september.admin.entity.User;
import space.zero.september.admin.service.RoleService;
import space.zero.september.admin.service.UserService;
import space.zero.september.admin.service.UserService;
import space.zero.september.admin.util.ResultGen;
import space.zero.september.common.core.Result;
import space.zero.september.common.core.param.ReqCond;
import space.zero.september.common.core.returncode.BusinessCode;

import java.util.List;

/**
 * @author : penggs
 * @program : september
 * @description : 用户web
 * @create : 2019-07-19 18:35
 */
@RestController
@RequestMapping("/user")
@Api(value = "user", tags = { "用户模块" })
public class UserWeb {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "[用户]按ID获取用户", notes = "返回 指定ID的用户", produces = "application/xml,application/json", tags = { "用户模块" }, response = Result.class)
    @GetMapping("/{id}")
    public Result get(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @ApiOperation(value = "[用户]分页获取用户", notes = "返回 分页后的用户列表", produces = "application/xml,application/json", tags = { "用户模块" }, response = Result.class)
    @PostMapping("/list")
    public Result list(@RequestBody ReqCond reqCond){
        return userService.getUser(reqCond);
    }

    @ApiOperation(value = "[用户]新增用户", notes = "返回 新增的用户", produces = "application/xml,application/json", tags = { "用户模块" }, response = Result.class)
    @PostMapping("")
    public Result add(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @ApiOperation(value = "[用户]删除用户", notes = "返回 是否成功删除", produces = "application/xml,application/json", tags = { "用户模块" }, response = Result.class)
    @DeleteMapping("/{id}")
    public Result remove(@PathVariable Long id) {
        return userService.removeUser(id);
    }

    @ApiOperation(value = "[用户]按ID更新用户", notes = "返回 更新后的用户", produces = "application/xml,application/json", tags = { "用户模块" }, response = Result.class)
    @PutMapping("/{id}")
    public Result update(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @ApiOperation(value = "[用户]按ID获取用户", notes = "返回 指定ID的用户", produces = "application/xml,application/json", tags = { "用户模块" }, response = Result.class)
    @GetMapping("/role/{userId}")
    public Result userRole(@PathVariable Long userId){
        ResultGen<List<Long>> resultGen = new ResultGen<>();
        return resultGen.success(BusinessCode.USER, roleService.getRoleIdByUserId(userId));
    }

    @ApiOperation(value = "[用户]按ID禁用/解禁用户", notes = "返回 是否成功", produces = "application/xml,application/json", tags = { "用户模块" }, response = Result.class)
    @PutMapping("/ban/{userId}")
    public Result enableUser(@PathVariable Long userId){
        return userService.enableUser(userId);
    }


    /**
     * 临时模拟返回当前用户
     *
     * @return space.zero.september.common.core.Result
     * @author penggs
     * @date 2019-07-27
     */
    @PostMapping("/now")
    public Result nowUser(){
        return userService.getUserById(1L);
    }
}