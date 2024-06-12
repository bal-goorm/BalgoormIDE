package com.balgoorm.balgoorm_backend.user.config;

import com.balgoorm.balgoorm_backend.user.auth.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(auth -> {
                    auth
                            .requestMatchers("/", "/index.html", "/login.html", "/signup.html", "/js/**", "/css/**").permitAll() // 정적 리소스 및 특정 페이지 허용
                            .requestMatchers("/signup", "/login").permitAll()
                            .anyRequest().authenticated(); // 모든 다른 요청은 인증이 필요함
                })
                .formLogin(login -> {
                    login.loginPage("/login.html")
                            .loginProcessingUrl("/login") // 로그인 폼이 제출될 URL 설정
                            .defaultSuccessUrl("/user-info.html", true)
                            .failureUrl("/login.html?error=true")
                            .permitAll();
                })
                .logout(logout -> {
                    logout.logoutUrl("/logout")
                            .logoutSuccessUrl("/login.html?logout")
                            .invalidateHttpSession(true)
                            .deleteCookies("JSESSIONID")
                            .permitAll();
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
