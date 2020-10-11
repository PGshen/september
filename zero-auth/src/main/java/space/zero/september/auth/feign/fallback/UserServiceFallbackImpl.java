package space.zero.september.auth.feign.fallback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import space.zero.september.auth.feign.UserService;
import space.zero.september.auth.feign.vo.UserInfoVO;
import space.zero.september.common.core.Result;

/**
 * @author : penggs
 * @program : september
 * @description : 用户接口熔断
 * @create : 2019-09-08
 */
@Service
public class UserServiceFallbackImpl implements UserService {
    private static final Logger log = LoggerFactory.getLogger(UserServiceFallbackImpl.class);

    @Override
    public Result<UserInfoVO> findUserByLoginName(String username) {
        log.error("调用{}异常:{}", "findUserByUsername", username);
        return new Result<>();
    }

    @Override
    public Result banUserByUsername(String username) {
        log.error("调用{}异常:{}", "banUserByUsername", username);
        return new Result();
    }
}