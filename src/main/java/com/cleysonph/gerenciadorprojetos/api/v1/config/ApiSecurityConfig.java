package com.cleysonph.gerenciadorprojetos.api.v1.config;

import com.cleysonph.gerenciadorprojetos.api.v1.shared.filters.AccessTokenRequestFilter;
import com.cleysonph.gerenciadorprojetos.core.services.auth.adapters.AuthenticationService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final AccessDeniedHandler accessDeniedHandler;
    private final AuthenticationService authenticationService;
    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final AccessTokenRequestFilter accessTokenRequestFilter;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authenticationService)
            .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatchers(requestMatcherCustomizer -> requestMatcherCustomizer
            .antMatchers("/api/v1/**")
        )
        .authorizeRequests(authorizeRequestsCustomizer -> authorizeRequestsCustomizer
            .anyRequest()
            .permitAll()
        )
        .csrf(csrfCustomizer -> csrfCustomizer
            .disable()
        )
        .sessionManagement(sessionManagementCustomizer -> sessionManagementCustomizer
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        )
        .exceptionHandling(exceptionHandlingCustomizer -> exceptionHandlingCustomizer
            .accessDeniedHandler(accessDeniedHandler)
            .authenticationEntryPoint(authenticationEntryPoint)
        )
        .addFilterBefore(accessTokenRequestFilter, UsernamePasswordAuthenticationFilter.class)
        .cors();
    }

}
