package com.germant.springPractice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.web.SecurityFilterChain;

public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        ((HttpSecurity)(
                (HttpSecurity)((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)
                        http.authorizeRequests()
                                .anyRequest())
                        .authenticated()
                        .and())
                .formLogin()
                .and())
                .httpBasic();
        return (SecurityFilterChain)http.build();
    }
}
