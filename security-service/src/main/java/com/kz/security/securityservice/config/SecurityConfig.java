package com.kz.security.securityservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Description 配置密码
 * @Author KatieZ
 * @Date Created in 15:06  15:06
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 不给密码加密
     * @return
     */
    @Bean
    PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 开启在内存中定义用户
        auth.inMemoryAuthentication()
                .withUser("kz")
                .password("123")
                .roles("admin");
        // 如果需要配置多个用户 用 and 相连
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        //配置忽略掉的 URL 地址，一般对于静态文件，我们可以采用此操作
        web.ignoring().antMatchers("/js/**", "/css/**","/images/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login.html")
                // 登录相关的页面和接口不做拦截
                .permitAll()
                .and()
                .csrf().disable();
    }
}
