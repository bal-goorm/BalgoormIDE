package com.balgoorm.balgoorm_backend.chat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * 웹 소켓 연결 요청인 /chatting/** 에 대한 인증 비 활성화 설정
 */
@Configuration
@EnableWebSecurity
public class WebSocketSecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/chat/**", "/h2-console/**").permitAll()
                                .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.disable()) // CSRF 비활성화
                .headers(headers -> headers
                        .frameOptions(frameOptions -> frameOptions.disable()) // X-Frame-Options 비활성화
                )
                .formLogin(withDefaults()); // 기본 로그인 폼 사용

        return httpSecurity.build();
    }
}
