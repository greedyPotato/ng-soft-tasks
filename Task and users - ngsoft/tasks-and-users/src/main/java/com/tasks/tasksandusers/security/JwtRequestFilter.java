package com.tasks.tasksandusers.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.tasks.tasksandusers.services.JwtTokenService;
import com.tasks.tasksandusers.services.JwtUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtRequestFilter extends OncePerRequestFilter{
    
    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
                final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
                if(header == null || !header.startsWith("Bearer")){
                    filterChain.doFilter(request, response);
                    return ;
                }

                final String token = header.substring(7);
                final String userName = jwtTokenService.validateTokenAndGetUsername(token);
                if(userName==null){
                    filterChain.doFilter(request, response);
                    return;
                }
                final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(userName);
                final UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                filterChain.doFilter(request, response);
        
    }


}
