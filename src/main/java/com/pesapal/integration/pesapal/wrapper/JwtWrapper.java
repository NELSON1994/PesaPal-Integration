package com.pesapal.integration.pesapal.wrapper;

import lombok.Data;

@Data
public class JwtWrapper {
    private String username;
    private String token;
    private String tokenVality;
}
