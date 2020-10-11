package space.zero.september.auth.service;

import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import space.zero.september.auth.feign.UserService;
import space.zero.september.auth.feign.vo.User;
import space.zero.september.auth.feign.vo.UserInfoVO;
import space.zero.september.common.core.Result;
import space.zero.september.common.security.constant.SecurityConstant;
import space.zero.september.common.security.service.AuthUser;
import sun.security.util.SecurityConstants;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author : penggs
 * @program : september
 * @description :
 * @create : 2020-09-11
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Result<UserInfoVO> result = userService.findUserByLoginName(username);
        UserDetails userDetails = getUserDetails(result);
        // todo 考虑将用户信息写入缓存
        return userDetails;
    }

    private UserDetails getUserDetails(Result<UserInfoVO> result) {
        if (result == null || result.getData() == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        UserInfoVO info = result.getData();
        Set<String> dbAuthsSet = new HashSet<>();
        if (info.getRoles() != null && info.getRoles().size() > 0) {
            // 获取角色
            info.getRoles().forEach(roleId -> dbAuthsSet.add(SecurityConstant.ROLE + roleId));
            // 获取资源
            dbAuthsSet.addAll(info.getPermissions());

        }
        // 同时将用户权限信息写入
        Collection<? extends GrantedAuthority> authorities
                = AuthorityUtils.createAuthorityList(dbAuthsSet.toArray(new String[0]));
        User user = info.getUser();
        boolean enabled = StrUtil.equals(user.getIsEnable().toString(), SecurityConstant.STATUS_NORMAL);
        boolean notLockedFlag = StrUtil.equals(user.getIsEnable().toString(), SecurityConstant.STATUS_NORMAL);
        // 构造security用户

        // NOTE: name使用loginName
        return new AuthUser(user.getUserId(), 1L, user.getLoginName(), user.getPassword(), enabled,
                true, true, notLockedFlag, authorities);
    }
}