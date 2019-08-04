package space.zero.september.admin.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import space.zero.september.admin.entity.Role;
import space.zero.september.admin.mapper.RoleMapper;
import space.zero.september.admin.service.RoleService;
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
 * @description : 角色实现类
 * @create : 2019-07-19 18:29
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    private Logger log = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private ResultGen<Role> resultGen;

    /**
     * 根据ID查
     *
     * @param id 角色ID
     * @return space.zero.september.common.core.Result<space.zero.september.admin.entity.Role>
     * @author penggs
     * @date 2019-07-24
     */
    @Override
    public Result<Role> getRoleById(Long id) {
        log.info("Get role: id = " + id);
        Role role = baseMapper.selectById(id);
        if (role == null){
            return resultGen.fail(BusinessCode.ROLE, ErrorCode.P301);
        }
        return resultGen.success(BusinessCode.ROLE, role);
    }

    /**
     * 分页查询
     *
     * @param reqCond 条件
     * @return space.zero.september.common.core.Result<com.baomidou.mybatisplus.plugins.Page<space.zero.september.admin.entity.Role>>
     * @author penggs
     * @date 2019-07-24
     */
    @Override
    public Result<Page<Role>> getRole(ReqCond reqCond) {
        log.info("Get role: condition : " + JSONObject.toJSONString(reqCond));
        ResultGen<Page<Role>> resultGen = new ResultGen<>();
        Page<Role> page = new Page<>(reqCond.getPage(), reqCond.getSize(), reqCond.getSort());
        Map<String, Object> condition = CommonUtil.getReqCond(reqCond);
        page.setRecords(baseMapper.selectRole(page, condition));
        return resultGen.success(BusinessCode.ROLE, page);
    }

    /**
     * 保存
     *
     * @param role 角色
     * @return space.zero.september.common.core.Result<space.zero.september.admin.entity.Role>
     * @author penggs
     * @date 2019-07-24
     */
    @Override
    public Result<Role> saveRole(Role role) {
        log.info("Save role: role = " + JSONObject.toJSONString(role));
        // 数据校验，不符就抛异常
        baseMapper.insert(role);
        return resultGen.success(BusinessCode.ROLE, role);
    }

    /**
     * 更新角色
     *
     * @param id 角色ID
	 * @param role 角色
     * @return space.zero.september.common.core.Result<space.zero.september.admin.entity.Role>
     * @author penggs
     * @date 2019-07-24
     */
    @Override
    public Result<Role> updateRole(Long id, Role role) {
        log.info("Update role: id = " + id + ", role = " + JSONObject.toJSONString(role));
        role.setRoleId(id);
        baseMapper.updateById(role);
        return resultGen.success(BusinessCode.ROLE, role);
    }

    /**
     * 删除角色
     *
     * @param id 角色ID
     * @return space.zero.september.common.core.Result<java.lang.Boolean>
     * @author penggs
     * @date 2019-07-24
     */
    @Override
    public Result<Boolean> removeRole(Long id) {
        log.info("Remove role: id = " + id);
        ResultGen<Boolean> resultGen = new ResultGen<>();
        // 判断是否还有用户关联该角色，若有则不能删除
        if (baseMapper.countUserByRoleId(id) > 0){
            return resultGen.fail(BusinessCode.ROLE, ErrorCode.P304, Boolean.FALSE);
        }
        baseMapper.deleteById(id);
        return resultGen.success(BusinessCode.ROLE, Boolean.TRUE);
    }

    /**
     * 获取用户的角色列表
     *
     * @param userId 用户ID
     * @return java.util.List<space.zero.september.admin.entity.Role>
     * @author penggs
     * @date 2019-07-25
     */
    @Override
    public List<Role> getRoleByUserId(Long userId) {
        return baseMapper.selectRoleByUserId(userId);
    }

    /**
     * 获取用户的角色ID列表
     *
     * @param userId 用户ID
     * @return java.util.List<java.lang.Long>
     * @author penggs
     * @date 2019-07-25
     */
    @Override
    public List<Long> getRoleIdByUserId(Long userId) {
        return baseMapper.selectRoleIdByUserId(userId);
    }
}