package space.zero.september.auth.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import space.zero.september.auth.feign.fallback.UserServiceFallbackImpl;
import space.zero.september.auth.feign.vo.UserInfoVO;
import space.zero.september.common.core.Result;

/**
 * @author : penggs
 * @program : september
 * @description : 用户接口
 * @create : 2019-09-08
 */
@FeignClient(name = "zero-admin", fallback = UserServiceFallbackImpl.class)
@Service
public interface UserService {
    /**
     * 通过登录名获取用户信息
     *
     * @param loginName 登录名
     * @return space.zero.september.common.core.response.Result
     * @author penggs
     * @date 2019-09-08
     */
    @GetMapping("/admin/user/login/{loginName}")
    Result<UserInfoVO> findUserByLoginName(@PathVariable(value = "loginName") String loginName);

    /**
     * 通过登录名禁用用户
     *
     * @param loginName 登录名
     * @return space.zero.september.common.core.response.Result
     * @author penggs
     * @date 2020-09-08
     */
    @PutMapping("/admin/user/banbyusername/{loginName}")
    Result banUserByUsername(@PathVariable(value = "loginName") String loginName);
}