package com.alive_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;

@Configuration
//@EnableWebSecurity(debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //放行register接口
                .antMatchers("/register").permitAll()
                .antMatchers("/login_email").permitAll()
                .antMatchers(HttpMethod.POST).permitAll()
                .antMatchers(HttpMethod.GET).permitAll()
                .anyRequest().authenticated()
//                .formLogin().permitAll()
                .and()
                //支持跨域访问
                .cors()
                .configurationSource(corsConfigurationSource())
                .and()
                //对跨域请求伪造进行防护---->csrf:利用用户带有登录状态的cookie进行攻击的手段
                .csrf().disable();
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
//        configuration.setAllowedOrigins(Collections.singletonList("*"));
        configuration.setAllowedMethods(Collections.singletonList("*"));
        configuration.setAllowedHeaders(Collections.singletonList("*"));
        configuration.setMaxAge(Duration.ofHours(1));
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    //配置采用哪种密码加密算法
    @Bean
    public PasswordEncoder passwordEncoder() {
        //不使用密码加密
        //return NoOpPasswordEncoder.getInstance();

        //使用默认的BCryptPasswordEncoder加密方案
        return new BCryptPasswordEncoder();

        //strength=10，即密钥的迭代次数(strength取值在4~31之间，默认为10)
        //return new BCryptPasswordEncoder(10);

        //利用工厂类PasswordEncoderFactories实现,工厂类内部采用的是委派密码编码方案.
        //return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
