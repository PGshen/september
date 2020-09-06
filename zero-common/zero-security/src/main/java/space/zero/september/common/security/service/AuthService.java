package space.zero.september.common.security.service;

import cn.hutool.core.util.StrUtil;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import space.zero.september.common.security.constant.SecurityConstant;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author : penggs
 * @program : september
 * @description :
 * @create : 2019-04-24
 */
@UtilityClass
public class AuthService {
    /**
     * 获取Authentication
     */
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取当前用户
     *
     * @return space.zero.september.common.security.service.AuthUser
     * @author penggs
     * @date 2020-04-24
     */
    public AuthUser getUser() {
        Authentication authentication = getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof AuthUser) {
            return (AuthUser) principal;
        }
        return null;
    }

    /**
     * 获取当前用户角色
     *
     * @return java.util.List<java.lang.Long>
     * @author penggs
     * @date 2020-04-24
     */
    public List<Long> getRoles() {
        Authentication authentication = getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        List<Long> roleIds = new ArrayList<>();
        authorities.stream()
                .filter(granted -> StrUtil.startWith(granted.getAuthority(), SecurityConstant.ROLE))
                .forEach(granted -> {
                    String id = StrUtil.removePrefix(granted.getAuthority(), SecurityConstant.ROLE);
                    roleIds.add(Long.parseLong(id));
                });
        return roleIds;
    }

    /**
     * 获取当前用户权限标识
     *
     * @return java.util.List<java.lang.String>
     * @author penggs
     * @date 2020-04-24
     */
    public List<String> getPerms() {
        Authentication authentication = getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        List<String> perms = new ArrayList<>();
        authorities.stream()
                .filter(granted -> !StrUtil.startWith(granted.getAuthority(), SecurityConstant.ROLE))
                .forEach(granted -> {
                    perms.add(granted.getAuthority());
                });
        return perms;
    }
}