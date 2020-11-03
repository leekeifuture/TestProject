package com.example.TestProject.config.security;

import com.example.TestProject.config.PropertiesLoader;
import com.example.TestProject.dto.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    private final PropertiesLoader properties;

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest req,
            HttpServletResponse res
    ) throws AuthenticationException {
        try {
            User applicationUser =
                    new ObjectMapper()
                            .readValue(req.getInputStream(), User.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            applicationUser.getUsername(),
                            applicationUser.getPassword(),
                            new ArrayList<>()
                    )
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest req,
            HttpServletResponse res,
            FilterChain chain,
            Authentication auth
    ) {
        Date exp = new Date(System.currentTimeMillis() + 1000L * 60 * 30);
        Key key = Keys.hmacShaKeyFor(properties.getSecurityKey().getBytes());

        Claims claims = Jwts.claims()
                .setSubject(
                        ((org.springframework.security.core.userdetails.User) auth.getPrincipal())
                                .getUsername()
                );

        String token = Jwts.builder()
                .setClaims(claims)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(exp)
                .compact();

        res.addHeader("token", token);
    }
}
