package com.data;

/**
 Created By @Umesh Joshi
 **/

public class AuthenticationTokenDataBuilder {

    public AuthenticationTokenData authenticationDataBuilder(){

        return AuthenticationTokenData.builder()
                .username("admin")
                .password("password123")
                .build();

    }
}
