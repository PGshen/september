package space.zero.september.auth.config;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import space.zero.september.auth.custom.CustomWebResponseExceptionTranslator;
import space.zero.september.auth.service.UserDetailsServiceImpl;
import space.zero.september.common.security.config.Md5PasswordEncoder;
import space.zero.september.common.security.constant.SecurityConstant;
import space.zero.september.common.security.service.AuthUser;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : penggs
 * @program : september
 * @description : 认证服务器配置
 * @create : 2019-08-11 18:41
 */
@Configuration
@AllArgsConstructor
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    private final UserDetailsServiceImpl userDetailsService;
    private final AuthenticationManager authenticationManagerBean;
    private final RedisConnectionFactory redisConnectionFactory;
    private final CustomWebResponseExceptionTranslator customWebResponseExceptionTranslator;

    @Override
    @SneakyThrows
    public void configure(ClientDetailsServiceConfigurer clients) {
        clients.withClientDetails(clientDetails());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer
                .allowFormAuthenticationForClients()
                .checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
                .tokenStore(tokenStore())
                .tokenEnhancer(tokenEnhancer())
                .userDetailsService(userDetailsService)
                .authenticationManager(authenticationManagerBean)
                .reuseRefreshTokens(false)
                .exceptionTranslator(customWebResponseExceptionTranslator);
    }


    @Bean
    public TokenStore tokenStore() {
        RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
        tokenStore.setPrefix(SecurityConstant.AUTH_PREFIX);
        tokenStore.setAuthenticationKeyGenerator(new DefaultAuthenticationKeyGenerator() {
            @Override
            public String extractKey(OAuth2Authentication authentication) {
                return super.extractKey(authentication);
            }
        });
        return tokenStore;
    }

    /**
     * token增强，客户端模式不增强。
     *
     * @return TokenEnhancer
     */
    @Bean
    public TokenEnhancer tokenEnhancer() {
        return (accessToken, authentication) -> {
            if (SecurityConstant.CLIENT_CREDENTIALS
                    .equals(authentication.getOAuth2Request().getGrantType())) {
                return accessToken;
            }

            final Map<String, Object> additionalInfo = new HashMap<>(8);
            AuthUser authUser = (AuthUser) authentication.getUserAuthentication().getPrincipal();
            additionalInfo.put(SecurityConstant.DETAILS_USER_ID, authUser.getId());
            additionalInfo.put(SecurityConstant.DETAILS_USERNAME, authUser.getUsername());
            additionalInfo.put(SecurityConstant.DETAILS_ORG_ID, authUser.getOrgId());
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
            return accessToken;
        };
    }

    @Bean
    public ClientDetailsService clientDetails() {
        JdbcClientDetailsService clientDetailsService = new JdbcClientDetailsService(dataSource);
        clientDetailsService.setSelectClientDetailsSql(SecurityConstant.DEFAULT_SELECT_STATEMENT);
        clientDetailsService.setFindClientDetailsSql(SecurityConstant.DEFAULT_FIND_STATEMENT);
        return clientDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Md5PasswordEncoder("MD5");
    }
}