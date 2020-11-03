package com.example.TestProject.config.security;

import com.example.TestProject.config.PropertiesLoader;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.AllArgsConstructor;

@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    private final PropertiesLoader properties;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, "/users/singUp").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new AuthenticationFilter(authenticationManager(), properties))
                .addFilter(new AuthorizationFilter(authenticationManager(), properties))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(
                "/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**"
        );
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration(
                "/**",
                new CorsConfiguration().applyPermitDefaultValues()
        );
        return source;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
