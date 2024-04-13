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
       // http.formLogin(Customizer.withDefaults()); //기본 로그인 페이지를 사용하여 인증을 구성한다.

        //로그인 페이지 및 로그인 처리를 구성한다.
        http.formLogin(form -> form
                .loginPage("/members/login") // 로그인 페이지의 url을 설정한다.
                .defaultSuccessUrl("/", true) //로그인 성공시 리다이렉트될 url생성
                .failureUrl("/members/login/error") //로그인 실패시 리다이렉트 될 url을 설정
                .usernameParameter("email") //로그인 폼에서 이메이 ㄹ입력 필드의 이름을 설저ㅇ한다.
                .passwordParameter("password") //로그인 폼에서 비밀번호 입력 필드의 이름을 설정한다.
                .permitAll()); //로그인 페이지에 대한 접근을 모든 사용자에게 허용한다.




        //httop 요청에 대한 권한을 설정한다.
        http.authorizeHttpRequests(request -> request
                .requestMatchers("/css/**").permitAll() //css파일에 대한 접근을 모든사용자에게 허용
                .requestMatchers("/", "/members/**").permitAll() //홈페이지 및 /member 경로에 대하 접근모두허용
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()); //나머지 모든 요청에 대해서는 인증된 사용자만 접근 가능.
        //


        http.exceptionHandling(exception -> exception
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint()));

        http.logout(Customizer.withDefaults()); //로그아웃 구성을 설정한다.
        return http.build(); //구성이 완료된 httpSecurity 객체를 반환한다.
    }

    @Bean
    public PasswordEncoder passwordEncoder(){ //암호를 암호화 하는데 사용될 PasswordEncoder 빈 설정
        return new BCryptPasswordEncoder(); //BCrypt 알고리즘을 사용하여 암호화한다.
    }
}
