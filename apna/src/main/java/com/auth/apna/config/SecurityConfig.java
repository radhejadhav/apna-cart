package com.auth.apna.config;

import com.auth.apna.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    private AuthFilter authFilter;

    public SecurityConfig(CustomUserDetailsService userDetailsService, AuthFilter authFilter) {
        this.userDetailsService = userDetailsService;
        this.authFilter = authFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(csrf->csrf.disable())
                .authorizeHttpRequests(auth -> auth.requestMatchers("/auth/welcome", "/auth/add-user").permitAll())
                .authorizeHttpRequests(auth-> auth.requestMatchers(HttpMethod.POST, "/auth/authenticate", "/auth/login").permitAll())
                .authorizeHttpRequests(auth -> auth.requestMatchers("/user/**").hasAnyAuthority("ADMIN"))
                .authorizeHttpRequests(auth -> auth.requestMatchers("/user/admin/**").hasAnyAuthority("ADMIN"))
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exception->exception.accessDeniedHandler(new CustomAccessDeniedHandler()))
                .exceptionHandling(exception->exception.authenticationEntryPoint(new CustomAuthenticationEntryPoint()))
                .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}