package com.abd.spring_boot_audiobooks.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthorizationFilter extends OncePerRequestFilter {


    @Autowired
    private JwtProvider jwtProvider;


    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException{

        String requestURI = request.getRequestURI();
        return requestURI.startsWith("/api/authentication/") || requestURI.startsWith("/api/internal") ||
                (request.getMethod().equals("GET") && requestURI.startsWith("/api/audiobook"));



    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        Authentication authentication = jwtProvider.getAuthentication(request);

        if (authentication != null && jwtProvider.validateToken(request)){

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);

    }
}
