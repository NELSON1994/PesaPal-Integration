package com.pesapal.integration.pesapal.controller;

import com.pesapal.integration.pesapal.jwt.JwtUtil;
import com.pesapal.integration.pesapal.wrapper.GenericResponseWrapper;
import com.pesapal.integration.pesapal.wrapper.JwtWrapper;
import com.pesapal.integration.pesapal.wrapper.UserLoginWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class AuthorizationController {
   @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/")
    public String welcome() {
        return "WELCOME TO PESAPAL AUTHENTICATION OF APIs!!";
    }
    @PostMapping("/authenticate")
    public GenericResponseWrapper generateToken2(@RequestBody UserLoginWrapper userLoginWrapper) throws Exception {
        GenericResponseWrapper genericResponseWrapper=new GenericResponseWrapper();
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userLoginWrapper.getUsername(), userLoginWrapper.getPassword())
            );
            String token=jwtUtil.generateToken(userLoginWrapper.getUsername());
            JwtWrapper jwtWrapper=new JwtWrapper();
            jwtWrapper.setUsername(userLoginWrapper.getUsername());
            jwtWrapper.setToken(token);
            jwtWrapper.setTokenVality("10 HRS");
            genericResponseWrapper.setData(jwtWrapper);
            genericResponseWrapper.setStatus("SUCCESS");
            genericResponseWrapper.setCode(200);
            genericResponseWrapper.setMessage("Token Generated Successfully");

        } catch (Exception ex) {
            genericResponseWrapper.setData(null);
            genericResponseWrapper.setStatus("FAILED");
            genericResponseWrapper.setCode(301);
            genericResponseWrapper.setMessage("Invalid Username/Password");

        }
        return genericResponseWrapper;
    }
}
