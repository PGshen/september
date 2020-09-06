package space.zero.september.common.security.service;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import space.zero.september.common.security.config.SecurityIgnoreProperties;
import space.zero.september.common.security.constant.SecurityConstant;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/**
 * @author : penggs
 * @program : september
 * @description :
 * @create : 2019-04-03
 */
@Service("permissionService")
public class PermissionService {
    private static final Logger log = LoggerFactory.getLogger(PermissionService.class);

    @Autowired
    private SecurityIgnoreProperties securityIgnoreProperties;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        log.debug("Check perm: " + request.getMethod() + ":" + request.getRequestURI());
        log.debug("Authentication: " + JSONObject.toJSONString(authentication));
        // NOTE: 通过feign调用的，不再校验权限，但是token在此之前校验过了，feign调用时会将token进行传递
        String feignRequest = request.getHeader("feignRequest");
        if (!StringUtils.isEmpty(feignRequest) && "yes".equals(feignRequest)) {
            return true;
        }
        // permitAll配置不能生效时，可以加下面逻辑
        AtomicBoolean hasPermission = new AtomicBoolean(false);
        //securityIgnoreProperties.getUrls().stream().filter(uri -> antPathMatcher.match(uri, request.getRequestURI())).findFirst().ifPresent(x ->hasPermission.set(true));
        //if (hasPermission.get()) {
        //    return true;
        //}
        Object principal = authentication.getPrincipal();
        List<SimpleGrantedAuthority> authorityList = (List<SimpleGrantedAuthority>) authentication.getAuthorities();
        if (principal != null) {
            if (authorityList == null || authorityList.isEmpty()) {
                log.warn("authorities list is empty: {}", authentication.getPrincipal());
                return false;
            }
            // 获取权限列表
            List<Perm> perms = authorityList.stream()
                    .filter(auth -> !auth.getAuthority().startsWith(SecurityConstant.ROLE))
                    .filter(auth -> auth.getAuthority().contains(":"))
                    .map(auth -> {
                        String[] perm = auth.getAuthority().split(":");
                        return new Perm(perm[0], perm[1]);
                    }).collect(Collectors.toList());
            // 匹配当前请求，同时项目名前缀可不理
            perms.stream().filter(perm -> antPathMatcher.match(perm.getUri(), request.getRequestURI()) || antPathMatcher.match(perm.getUri(), request.getRequestURI().substring(request.getRequestURI().indexOf('/', 1)))
                    && request.getMethod().equalsIgnoreCase(perm.getMethod())).findFirst().ifPresent(x -> hasPermission.set(true));
        }
        return hasPermission.get();
    }

    @Data
    @AllArgsConstructor
    static
    class Perm {
        String method;
        String uri;
    }
}