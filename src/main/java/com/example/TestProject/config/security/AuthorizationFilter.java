package com.example.TestProject.config.security;

import com.example.TestProject.config.PropertiesLoader;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class AuthorizationFilter extends BasicAuthenticationFilter {

    private final PropertiesLoader properties;

    public AuthorizationFilter(
            AuthenticationManager authManager,
            PropertiesLoader properties
    ) {
        super(authManager);
        this.properties = properties;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain
    ) throws ServletException, IOException {
        String header = request.getHeader(properties.getSecurityHeader());

        if (header == null) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = authenticate(request);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken authenticate(
            HttpServletRequest request
    ) {
        String token = request.getHeader(properties.getSecurityHeader());
        if (token != null) {
            Claims user = Jwts.parser()
                    .setSigningKey(
                            Keys.hmacShaKeyFor(
                                    properties.getSecurityKey().getBytes()
                            )
                    )
                    .parseClaimsJws(token)
                    .getBody();

            if (user != null) {
                return new UsernamePasswordAuthenticationToken(
                        user, null, new ArrayList<>()
                );
            } else {
                return null;
            }

        }
        return null;
    }
}
