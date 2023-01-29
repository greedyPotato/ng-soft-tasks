package com.tasks.tasksandusers.controller;
import com.tasks.tasksandusers.services.JwtUserDetailsService;
import com.tasks.tasksandusers.services.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.tasks.tasksandusers.dto.AuthenticationRequest;
import com.tasks.tasksandusers.dto.AuthenticationResponse;

import jakarta.validation.Valid;

@RestController
public class AuthenticationController {
    
    private AuthenticationManager authenticationManager;

    private JwtUserDetailsService JwtUserDetailsService;

    @Autowired
    private JwtTokenService JwtTokenService;

    public AuthenticationController(AuthenticationManager authenticationManager, JwtUserDetailsService JwtUserDetailsService){
        this.authenticationManager = authenticationManager;
        this.JwtUserDetailsService = JwtUserDetailsService;
    }

    @PostMapping("/authenticate")
    public AuthenticationResponse authenticate(@RequestBody @Valid final AuthenticationRequest authenticationRequest){
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(), authenticationRequest.getPassword()));
        }catch (final BadCredentialsException badCredentialsException){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "invalid username or password", badCredentialsException);
        }
        final UserDetails userDetails = JwtUserDetailsService.loadUserByUsername(authenticationRequest.getUserName());
        final AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setAccessToken(JwtTokenService.generateToken(userDetails));
        return authenticationResponse;
    }
}
