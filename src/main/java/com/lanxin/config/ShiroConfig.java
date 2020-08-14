package com.lanxin.config;
import com.lanxin.custom.CustomRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.HashMap;
/**
 * Created by Administrator on 2020/8/12 0012.
 */
@Configuration
public class ShiroConfig {
    private Integer timeout;

    private String username;

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    public Integer port;


    //session持久化redis
    @Bean
    public RedisSessionDAO redisSessionDAO(RedisManager redisManager){

        RedisSessionDAO redisSessionDAO= new RedisSessionDAO();

        redisSessionDAO.setRedisManager(redisManager);

        return redisSessionDAO;
    }

    @Bean
    public DefaultWebSessionManager defaultWebSessionManager(RedisSessionDAO redisSessionDAO){

        DefaultWebSessionManager defaultWebSessionManager=new DefaultWebSessionManager();

        defaultWebSessionManager.setSessionDAO(redisSessionDAO);

        return defaultWebSessionManager;
    }

    @Bean
    public RedisManager redisManager(){
        RedisManager redisManager=new RedisManager();
        redisManager.setHost(host);
        redisManager.setPort(port);
        redisManager.setExpire(1800);
        return redisManager;
    }
    @Bean
    public RedisCacheManager redisCacheManager(RedisManager redisManager){

        RedisCacheManager redisCacheManager=new RedisCacheManager();

        redisCacheManager.setRedisManager(redisManager);

        return redisCacheManager;
    }


    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){

        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator=new DefaultAdvisorAutoProxyCreator();

        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);//可以代理一个没有实现接口的类

        return defaultAdvisorAutoProxyCreator;
    }

    //shiro aop注解支持
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager defaultWebSecurityManager){

        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor=new AuthorizationAttributeSourceAdvisor();

        authorizationAttributeSourceAdvisor.setSecurityManager(defaultWebSecurityManager);

        return authorizationAttributeSourceAdvisor;
    }
    //安全管理器



    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        //拦截路径
        HashMap<String,String> map=new HashMap<>();
        map.put("/login","anon"); //匿名访问
        map.put("/logout","anon");
        map.put("/**","authc");  // 认证了才能访问  /add
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

        //如果用户没有登录，跳转的路径
        shiroFilterFactoryBean.setLoginUrl("/unlogin");

        return shiroFilterFactoryBean;
    }

    @Bean
    public CustomRealm  customRealm(HashedCredentialsMatcher hashedCredentialsMatcher){

       CustomRealm customRealm= new CustomRealm();

       customRealm.setCredentialsMatcher(hashedCredentialsMatcher);

       return customRealm;
    }

    //<property value="customReal"  ref="customRleam">
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(CustomRealm customRealm,
                                                               RedisCacheManager redisCacheManager,
                                                               DefaultWebSessionManager defaultWebSessionManager){
        DefaultWebSecurityManager defaultWebSecurityManager=new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(customRealm);
        defaultWebSecurityManager.setCacheManager(redisCacheManager);
        defaultWebSecurityManager.setSessionManager(defaultWebSessionManager);
        return defaultWebSecurityManager;
    }

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher=new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(10);
        return hashedCredentialsMatcher;
    }

}
