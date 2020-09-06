package space.zero.september.common.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.web.client.RestTemplate;

/**
 * @author : penggs
 * @program : september
 * @description : 资源服务器配置
 * @create : 2020-04-01
 */
public class ResourceServer3ConfigurerAdapter extends ResourceServerConfigurerAdapter {
    @Autowired
    private SecurityIgnoreProperties securityIgnoreProperties;
    @Autowired
    private CustomAccessDeniedHandler accessDeniedHandler;
    @Autowired
    private CustomAuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private OAuth2WebSecurityExpressionHandler expressionHandler;
    @Autowired
    protected CustomRemoteTokenServices remoteTokenServices;
    @Autowired
    private RestTemplate lbRestTemplate;

    @Override
    public void configure(HttpSecurity http) throws Exception{
        http.headers().frameOptions().disable();
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http.authorizeRequests();
        securityIgnoreProperties.getUrls().forEach(url -> registry.antMatchers(url).permitAll());
        registry.anyRequest().access("@permissionService.hasPermission(request,authentication)");
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer configurer){
        DefaultAccessTokenConverter accessTokenConverter = new DefaultAccessTokenConverter();
        CustomUserAuthenticationConverter userAuthenticationConverter = new CustomUserAuthenticationConverter();
        accessTokenConverter.setUserTokenConverter(userAuthenticationConverter);
        remoteTokenServices.setRestTemplate(lbRestTemplate);
        remoteTokenServices.setAccessTokenConverter(accessTokenConverter);
        configurer.expressionHandler(expressionHandler)
                .accessDeniedHandler(accessDeniedHandler)
                .authenticationEntryPoint(authenticationEntryPoint)
                .tokenServices(remoteTokenServices);
    }

    @Bean
    public OAuth2WebSecurityExpressionHandler oAuth2WebSecurityExpressionHandler(ApplicationContext applicationContext) {
        OAuth2WebSecurityExpressionHandler expressionHandler = new OAuth2WebSecurityExpressionHandler();
        expressionHandler.setApplicationContext(applicationContext);
        return expressionHandler;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Md5PasswordEncoder("MD5");
    }
}