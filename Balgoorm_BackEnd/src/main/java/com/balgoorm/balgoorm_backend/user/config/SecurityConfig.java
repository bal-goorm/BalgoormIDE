package com.balgoorm.balgoorm_backend.user.config;

import com.balgoorm.balgoorm_backend.user.auth.CustomAuthenticationSuccessHandler;
import com.balgoorm.balgoorm_backend.user.auth.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;
    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService, CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler) {
        this.customUserDetailsService = customUserDetailsService;
        this.customAuthenticationSuccessHandler = customAuthenticationSuccessHandler;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(auth -> {
                    auth
                            .anyRequest().permitAll(); // 테스트를 위해 모든 요청 허용
                })
                .formLogin(login -> {
                    login.loginPage("/login.html") // 로그인 폼
                            .loginProcessingUrl("/login") // 로그인 폼이 제출될 URL 설정
                            .successHandler(customAuthenticationSuccessHandler) // 로그인 성공 시 커스텀 핸들러
                            .failureUrl("/login.html?error=true") // 실패했을 때 리턴하는 주소
                            .usernameParameter("userId") // 해당 메소드에 적은 userId를 login form에 적힌 userId와 일치 시킴
                            .passwordParameter("password") // 비밀번호 파라미터 설정
                            .permitAll();
                })
                .logout(logout -> {
                    logout.logoutUrl("/logout")
                            .logoutSuccessUrl("/login.html?logout")
                            .invalidateHttpSession(true)
                            .deleteCookies("JSESSIONID")
                            .permitAll()
                            .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")); // 로그아웃을 GET 방식으로 처리
                })
                .sessionManagement(session -> {
                    session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                            .maximumSessions(1)
                            .maxSessionsPreventsLogin(false);
                })
                .userDetailsService(customUserDetailsService)
                .cors(cors -> cors.disable())
                .csrf(csrf -> csrf.disable()); // 간단히 하기 위해 CSRF 비활성화. 실제 운영 환경에서는 활성화 고려

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
