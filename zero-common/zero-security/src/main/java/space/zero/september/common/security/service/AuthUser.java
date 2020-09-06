package space.zero.september.common.security.service;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @author : penggs
 * @program : september
 * @description :
 * @create : 2019-09-09
 */
public class AuthUser extends User {
    /**
     * 用户ID
     */
    @Getter
    private Long id;
    /**
     * 组织机构ID
     */
    @Getter
    private Long orgId;

    public AuthUser(Long id, Long orgId, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.id = id;
        this.orgId = orgId;
    }

    public AuthUser(Long id, Long orgId, String username, String password, boolean enabled,
                    boolean accountNonExpired, boolean credentialsNonExpired,
                    boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.id = id;
        this.orgId = orgId;
    }


}