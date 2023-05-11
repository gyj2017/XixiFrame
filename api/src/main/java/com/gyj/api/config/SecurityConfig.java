package com.gyj.api.config;

import com.gyj.api.common.security.JwtAuthenticationFilter;
import com.gyj.api.common.security.LoginFailureHandler;
import com.gyj.api.common.security.LoginSuccessHandler;
import com.gyj.api.common.security.MyUserDetailsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author gaoyijie
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] URL_WHITE_LIST = { "/login", "/logout", "/captcha", "/password", "/image/**","/test/**" };

    @Autowired
    private LoginSuccessHandler loginSuccessHandler;
    @Autowired
    private LoginFailureHandler loginFailureHandler;
    @Autowired
    private MyUserDetailsServiceImp myUserDetailsService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.userDetailsService(myUserDetailsService);
    }


    /**
     * 密码加密
     * @return
     */
    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        return new JwtAuthenticationFilter(authenticationManager());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and()
            .csrf()
                .disable()
            // 登录登出
            .formLogin()
                .successHandler(loginSuccessHandler)
                .failureHandler(loginFailureHandler)
                /*.and()
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
                .anyRequest().authenticated()
                .and()
            // 异常处理配置
            // 自定义过滤器配置
            .addFilter(jwtAuthenticationFilter());

    }
}
