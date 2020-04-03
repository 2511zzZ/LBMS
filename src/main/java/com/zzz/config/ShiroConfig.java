package com.zzz.config;

import com.zzz.shiro.UserRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;


@Configuration
public class ShiroConfig {

    //创建ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //设置SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //添加filter
        /*
        * 常用过滤器：
        * anno:无需认证
        * authc:需要认证
        * user:使用RememberMe功能可以直接访问
        * perms:该资源必须得到资源权限才可以访问
        * roles：该资源必须得到角色权限才可以访问
        */
        Map<String, String> filterMap = new LinkedHashMap<>();
        //可使用通配符
        filterMap.put("/login", "anon");
        filterMap.put("/druid/stat", "authc");
        //授权过滤器
        filterMap.put("/structure", "roles[structure:query]");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        //修改认证失败的跳转页面
        shiroFilterFactoryBean.setLoginUrl("/loginFailed");
        shiroFilterFactoryBean.setUnauthorizedUrl("/unAuthorized");

        return shiroFilterFactoryBean;
    }

    //创建DefaultWebSecurityManager
    @Bean(name="securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    // 创建Realm
    @Bean(name="userRealm")
    public UserRealm getUserRealm(){
        return new UserRealm();
    }

}
