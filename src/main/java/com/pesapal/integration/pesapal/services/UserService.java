package com.pesapal.integration.pesapal.services;

import com.pesapal.integration.pesapal.model.User;
import com.pesapal.integration.pesapal.repository.UserRepository;
import com.pesapal.integration.pesapal.wrapper.GenericResponseWrapper;
import com.pesapal.integration.pesapal.wrapper.UserLoginWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String encryptPassword(String plainTextPassword) {
        String encryptedPassword = null;
        encryptedPassword = passwordEncoder.encode(plainTextPassword);
        return encryptedPassword;
    }

    public GenericResponseWrapper userLogin(UserLoginWrapper userLoginWrapper){
        GenericResponseWrapper genericResponseWrapper=new GenericResponseWrapper();
        try{
            String enc=encryptPassword(userLoginWrapper.getPassword());
            User user=userRepository.findByUsername(userLoginWrapper.getUsername());
            if(user !=null){
                String pass=user.getPassword();
                if (passwordEncoder.matches(enc, pass)) {
                    genericResponseWrapper.setCode(200);
                    genericResponseWrapper.setStatus("SUCCESS");
                    genericResponseWrapper.setMessage("Login Successful");
                }
                else{
                    genericResponseWrapper.setCode(400);
                    genericResponseWrapper.setStatus("FAILED");
                    genericResponseWrapper.setMessage("Invalid Password");
                }
            }
            else{
                genericResponseWrapper.setCode(400);
                genericResponseWrapper.setStatus("FAILED");
                genericResponseWrapper.setMessage("User with Username :"+userLoginWrapper.getUsername() + " Not Found");
            }

        }
        catch(Exception e){
            genericResponseWrapper.setCode(400);
            genericResponseWrapper.setStatus("FAILED");
            genericResponseWrapper.setMessage("System error Occurred");
        }

        return genericResponseWrapper;
    }
}
