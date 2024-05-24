package ru.kpfu.itis.skillshare.mainservice.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.XXssProtectionHeaderWriter;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.kpfu.itis.skillshare.mainservice.dto.Roles;
import ru.kpfu.itis.skillshare.mainservice.security.fliter.JwtTokenAuthenticationFilter;
import ru.kpfu.itis.skillshare.mainservice.security.provider.JwtAccessTokenProvider;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAccessTokenProvider jwtAccessTokenProvider;

    private static final String[] AUTHENTICATED = {
            "/api/v1/questions/answers/*/mark", "/api/v1/questions/answers/*/unmark",
            "/api/v1//questions/*/answers", "/api/v1/files/upload/image",
            "/api/v1/ratings/**", "/api/v1/users/settings/**", "/api/v1/users/me/**",
            "/questions/{id}/close", "/api/v1/auth/check", "/api/v1/chats/*/messages",
            "/api/v1/chats/me", "/api/v1/chats"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterAt(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(auth -> {
                    auth
                            .requestMatchers("/login", "/register", "/api/v1/auth/login").anonymous()
                            .requestMatchers(AUTHENTICATED).authenticated()
                            .requestMatchers(HttpMethod.POST, "/create/question").authenticated()
                            .requestMatchers(HttpMethod.POST, "/api/v1/users").anonymous()
                            .requestMatchers("/api/v1/users/**").hasRole(Roles.ADMIN.getName())
                            .requestMatchers(HttpMethod.POST, "/create/article").hasAnyRole(Roles.AUTHOR.getName(), Roles.ADMIN.getName(), Roles.MODER.getName())
                            .requestMatchers("/api/v1/articles/moderation", "/api/v1/articles/*/moderation/**").hasAnyRole(Roles.ADMIN.getName(), Roles.MODER.getName())
                            .requestMatchers("/**").permitAll();
                })
                .logout(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );
        return httpSecurity.build();
    }

    @Bean
    public OncePerRequestFilter tokenAuthenticationFilter() {
        return new JwtTokenAuthenticationFilter(jwtAccessTokenProvider);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}