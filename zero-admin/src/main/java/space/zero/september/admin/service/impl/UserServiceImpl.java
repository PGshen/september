package space.zero.september.admin.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import space.zero.september.admin.entity.User;
import space.zero.september.admin.mapper.UserMapper;
import space.zero.september.admin.service.RoleService;
import space.zero.september.admin.service.UserRoleService;
import space.zero.september.admin.service.UserService;
import space.zero.september.admin.util.ResultGen;
import space.zero.september.common.core.Result;
import space.zero.september.common.core.param.ReqCond;
import space.zero.september.common.core.returncode.BusinessCode;
import space.zero.september.common.core.returncode.ErrorCode;
import space.zero.september.common.core.utils.CommonUtil;

import java.util.List;
import java.util.Map;

/**
 * @author : penggs
 * @program : september
 * @description : 用户实现类
 * @create : 2019-07-19 18:32
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    private Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private ResultGen<User> resultGen;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRoleService userRoleService;

    /**
     * 根据ID查
     *
     * @param id 用户ID
     * @return space.zero.september.common.core.Result<space.zero.september.admin.entity.User>
     * @author penggs
     * @date 2019-07-24
     */
    @Override
    public Result<User> getUserById(Long id) {
        log.info("Get user: id = " + id);
        User user = baseMapper.selectById(id);
        if (user == null){
            return resultGen.fail(BusinessCode.USER, ErrorCode.P301);
        }
        List<Long> roleIds = roleService.getRoleIdByUserId(id);
        user.setRoles(roleIds);
        return resultGen.success(BusinessCode.USER, user);
    }

    /**
     * 分页查询
     *
     * @param reqCond 条件
     * @return space.zero.september.common.core.Result<com.baomidou.mybatisplus.plugins.Page<space.zero.september.admin.entity.User>>
     * @author penggs
     * @date 2019-07-24
     */
    @Override
    public Result<Page<User>> getUser(ReqCond reqCond) {
        log.info("Get user: condition : " + JSONObject.toJSONString(reqCond));
        ResultGen<Page<User>> resultGen = new ResultGen<>();
        Page<User> page = new Page<>(reqCond.getPage(), reqCond.getSize(), reqCond.getSort());
        Map<String, Object> condition = CommonUtil.getReqCond(reqCond);
        page.setRecords(baseMapper.selectUser(page, condition));
        return resultGen.success(BusinessCode.USER, page);
    }

    /**
     * 保存
     *
     * @param user 用户
     * @return space.zero.september.common.core.Result<space.zero.september.admin.entity.User>
     * @author penggs
     * @date 2019-07-24
     */
    @Override
    public Result<User> saveUser(User user) {
        log.info("Save user: user = " + JSONObject.toJSONString(user));
        // 数据校验，不符就抛异常
        baseMapper.insert(user);
        // 角色分配
        userRoleService.assignRole(user.getUserId(), user.getRoles());
        return resultGen.success(BusinessCode.USER, user);
    }

    /**
     * 更新
     *
     * @param id ID
	 * @param user 用户
     * @return space.zero.september.common.core.Result<space.zero.september.admin.entity.User>
     * @author penggs
     * @date 2019-07-24
     */
    @Override
    public Result<User> updateUser(Long id, User user) {
        log.info("Update user: id = " + id + ", user = " + JSONObject.toJSONString(user));
        user.setUserId(id);
        baseMapper.updateById(user);
        userRoleService.assignRole(user.getUserId(), user.getRoles());
        return resultGen.success(BusinessCode.USER, user);
    }

    /**
     * 删除
     *
     * @param id 用户ID
     * @return space.zero.september.common.core.Result<java.lang.Boolean>
     * @author penggs
     * @date 2019-07-24
     */
    @Override
    public Result<Boolean> removeUser(Long id) {
        log.info("Remove user: id = " + id);
        ResultGen<Boolean> resultGen = new ResultGen<>();
        baseMapper.deleteById(id);
        return resultGen.success(BusinessCode.USER, Boolean.TRUE);
    }

    /**
     * 解禁/禁用用户
     *
     * @param id 用户ID
     * @return space.zero.september.common.core.Result<java.lang.Boolean>
     * @author penggs
     * @date 2019-08-02
     */
    @Override
    public Result<Boolean> enableUser(Long id){
        ResultGen<Boolean> resultGen = new ResultGen<>();
        if (1 == baseMapper.enableUser(id)){
            return resultGen.success(BusinessCode.USER, Boolean.TRUE);
        } else {
            return resultGen.fail(BusinessCode.USER, ErrorCode.P303, Boolean.FALSE);
        }
    }
}