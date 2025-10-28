package com.example.goatTutorats.config;

import com.example.goatTutorats.services.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


/**
 * Spring security class configuration.
 * Configure password encoder to encode and decode user password during authentication.
 * Configure login page url, urls that need authentication and other not.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    /**
     * Specify encoder used to encode and decode passwords.
     * @return Password encoder to use.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Define the AuthenticationManager bean
     * @param http HttpSecurity object for configuring authentication
     * @return The AuthenticationManager
     * @throws Exception If an error occurs while configuring the AuthenticationManager
     */
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(customUserDetailsService)
                .passwordEncoder(this.passwordEncoder());
        return authenticationManagerBuilder.build();
    }

    /**
     * Configure login form such as login url, url when user is connected.
     * Configure logout format such as logout url and url when user is not connected.
     * Configure url access depending on user authenticated.
     * @param http Incoming http query.
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .formLogin(form -> form
                        .loginPage("/auth/get-login-page") // when authentication, redirect to this endpoint, redirect to /auth/login?error or /auth/login?logout
                        .loginProcessingUrl("/auth/login")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/apprentice/get-dashboard", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/auth/logout")
                        .logoutSuccessUrl("/auth/get-login-page")
                )
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/error/**").permitAll()
                        .requestMatchers("/css/**", "/img/**", "/js/**", "/errors/**").permitAll()
                        .requestMatchers("/docs/**").permitAll()
                        .requestMatchers("/user/**").permitAll()
                        .anyRequest().authenticated()
                )
                .csrf( cr -> cr
                        .ignoringRequestMatchers("/error/**")
                        .ignoringRequestMatchers("/user/**")
                )
                .exceptionHandling(exc -> exc
                        .accessDeniedPage("/auth/login")
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}