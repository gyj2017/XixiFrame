package com.gyj.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * @author gaoyijie
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] URL_WHITE_LIST = { "/login", "/logout", "/captcha", "/password", "/image/**","/test/**" };



    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("{noop}password").roles("USER")
                .and()
                .withUser("admin").password("{noop}password").roles("USER", "ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and()
            .csrf()
                .disable()
            // 登录登出
            .formLogin()
           /* .successHandler()
            .failureHandler()
            .and()
            .logout()
            .logoutSuccessHandler()*/
            // session禁用配置
                 .and()
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            // 拦截规则配置
                .and()
            .authorizeRequests()
            // 认证
                .antMatchers(URL_WHITE_LIST).permitAll()
            // 放行
                .anyRequest().authenticated();
            // 异常处理配置
            // 自定义处理配置
    }
}
