package com.pesapal.integration.pesapal.controller;

import com.pesapal.integration.pesapal.services.UserService;
import com.pesapal.integration.pesapal.wrapper.GenericResponseWrapper;
import com.pesapal.integration.pesapal.wrapper.UserLoginWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<GenericResponseWrapper> login(@RequestBody UserLoginWrapper userLoginWrapper) {
        GenericResponseWrapper genericResponseWrapper = userService.userLogin(userLoginWrapper);
        return ResponseEntity.status(genericResponseWrapper.getCode()).body(genericResponseWrapper);
    }

}
