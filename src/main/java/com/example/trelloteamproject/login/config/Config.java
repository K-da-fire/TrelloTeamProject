package com.example.trelloteamproject.login.config;

import com.example.trelloteamproject.login.jwt.JwtAuthenticationFilter;
import com.example.trelloteamproject.login.jwt.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class Config {

    private final JwtTokenProvider jwtTokenProvider;

    public Config(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
        return new JwtAuthenticationFilter(jwtTokenProvider);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        //csrf disable
        http
                .csrf((auth) -> auth.disable());

        //From 로그인 방식 disable
        http
                .formLogin((auth) -> auth.disable());

        //http basic 인증 방식 disable
        http
                .httpBasic((auth) -> auth.disable());

        // JWT 인증 필터 추가
        http.addFilterAt(jwtAuthenticationFilter(jwtTokenProvider), BasicAuthenticationFilter.class);

        //인가 설정
        http.authorizeHttpRequests((auth) -> auth
                        .requestMatchers("users","users/login","users/delete").permitAll() //인증 없이 허용
                        .requestMatchers("/admin").hasRole("ADMIN")             // ADMIN 권한 필요
                        .anyRequest().authenticated());                          // 나머지 인증 필요

        //세션 설정
        http.sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}
