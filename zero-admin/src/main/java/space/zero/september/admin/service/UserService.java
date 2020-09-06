package space.zero.september.admin.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import space.zero.september.admin.entity.User;
import space.zero.september.admin.vo.UserInfoVO;
import space.zero.september.common.core.Result;
import space.zero.september.common.core.param.ReqCond;

/**
 * @author : penggs
 * @program : september
 * @description : 用户service
 * @create : 2019-07-19 18:22
 */
public interface UserService extends IService<User> {
    /**
     * 根据ID获取
     *
     * @param id 用户ID
     * @return space.zero.september.common.core.Result<space.zero.september.admin.entity.User>
     * @author penggs
     * @date 2019-07-24
     */
    Result<User> getUserById(Long id);

    /**
     * 通过登录名获取用户信息，包含角色和权限，仅用于认证服务使用！！！
     *
     * @param loginName 登录名
     * @return Result<User>
     * @author penggs
     * @date 2019-07-24
     */
    Result<UserInfoVO> getUserByLoginName(String loginName);

    /**
     * 分页查询
     *
     * @param reqCond 分页条件
     * @return space.zero.september.common.core.Result<com.baomidou.mybatisplus.plugins.Page<space.zero.september.admin.entity.User>>
     * @author penggs
     * @date 2019-07-24
     */
    Result<Page<User>> getUser(ReqCond reqCond);

    /**
     * 保存用户
     *
     * @param user 用户
     * @return space.zero.september.common.core.Result<space.zero.september.admin.entity.User>
     * @author penggs
     * @date 2019-07-24
     */
    Result<User> saveUser(User user);

    /**
     * 更新用户
     *
     * @param id 用户ID
	 * @param user 用户
     * @return space.zero.september.common.core.Result<space.zero.september.admin.entity.User>
     * @author penggs
     * @date 2019-07-24
     */
    Result<User> updateUser(Long id, User user);

    /**
     * 删除用户
     *
     * @param id 用户ID
     * @return space.zero.september.common.core.Result<java.lang.Boolean>
     * @author penggs
     * @date 2019-07-24
     */
    Result<Boolean> removeUser(Long id);

    /**
     * 禁用/解禁用户
     *
     * @param id 用户ID
     * @return space.zero.september.common.core.Result<java.lang.Boolean>
     * @author penggs
     * @date 2019-08-02
     */
    Result<Boolean> enableUser(Long id);
}