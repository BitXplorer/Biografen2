package com.example.Biografen.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final String LOGIN_PROCESSING_URL = "/login";
    private static final String LOGIN_FAILURE_URL = "/login?error";
    private static final String LOGIN_URL = "/login";
    private static final String LOGOUT_SUCCESS_URL = "/login";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()

                // Register our CoustomRequestCash, that saves unauthorized access and
                // the user is redirected after login.
                .requestCache().requestCache(new CustomRequestCache())

                // Restrict access to our application.
                .and().authorizeRequests()

                // Allow all Vaadin internal requests.
                .requestMatchers(SecurityUtils::isFrameworkInternalRequest).permitAll()

                // Allow all requests by logged in users.
                .anyRequest().authenticated()

                // Configure the login page.
                .and().formLogin()
                        .loginPage(LOGIN_URL).permitAll()
                        .loginProcessingUrl(LOGIN_PROCESSING_URL)
                        .failureUrl(LOGIN_FAILURE_URL)

                        // Configure logout.
                        .and().logout().logoutSuccessUrl(LOGOUT_SUCCESS_URL);
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        // typical logged in user with some privileges
        UserDetails normalUser =
                User.withUsername("user")
                        .password("{noop}password")
                        .roles("User")
                        .build();

        // admin user with all privileges
        UserDetails adminUser =
                User.withUsername("admin")
                        .password("{noop}admin")
                        .roles("User", "Admin")
                        .build();

        return new InMemoryUserDetailsManager(normalUser, adminUser);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(
                // Vaadin static resources
                "/VAADIN/**",

                // the standard favicon URI
                "/favicon.ico",

                // the robots exclusion standard
                "/robots.txt",

                // web application manifest
                "/manifest.webmanifest",
                "/sw.js",
                "/offline.html",

                // Icon and images
                "/icons/**",
                "/images/**",
                "/styles/**",

                // (development mode) static resources
                "/frontend/**",

                "/mysql/**",

                // (development mode) H2 debuging console
                "/h2-console/**",

                // (production mode) static resources
                "/frontend-es5/**", "/frontend-es6/**");
    }
}
