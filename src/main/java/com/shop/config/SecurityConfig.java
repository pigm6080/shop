package com.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration //설정 클래스임을 나타낸다.
@EnableWebSecurity // 스프링 시큐리티를 웹기반 보안 구성을 활성화 하는데 사용된다.
public class SecurityConfig{

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.formLogin(Customizer.withDefaults()); //기본 로그인 페이지를 사용하여 인증을 구성한다.
        http.logout(Customizer.withDefaults()); //로그아웃 구성을 설정한다.
        return http.build(); //구성이 완료된 httpSecurity 객체를 반환한다.
    }

    @Bean
    public PasswordEncoder passwordEncoder(){ //암호를 암호화 하는데 사용될 PasswordEncoder 빈 설정
        return new BCryptPasswordEncoder(); //BCrypt 알고리즘을 사용하여 암호화한다.
    }
}
