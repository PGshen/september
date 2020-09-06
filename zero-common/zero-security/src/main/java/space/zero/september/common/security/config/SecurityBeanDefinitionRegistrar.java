package space.zero.september.common.security.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import space.zero.september.common.security.constant.SecurityConstant;

/**
 * @author : penggs
 * @program : september
 * @description :
 * @create : 2019-04-01
 */
public class SecurityBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    private static final Logger log = LoggerFactory.getLogger(SecurityBeanDefinitionRegistrar.class);

    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
        if (registry.isBeanNameInUse(SecurityConstant.RESOURCE_SERVER_CONFIGURER)) {
            log.warn("本地存在资源服务器配置:" + SecurityConstant.RESOURCE_SERVER_CONFIGURER);
            return;
        }
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(ResourceServer3ConfigurerAdapter.class);
        registry.registerBeanDefinition(SecurityConstant.RESOURCE_SERVER_CONFIGURER, beanDefinition);
    }
}