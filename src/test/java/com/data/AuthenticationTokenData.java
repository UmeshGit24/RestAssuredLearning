package com.data;

import lombok.Builder;
import lombok.Data;

/**
 Created By @Umesh Joshi
 **/

@Data
@Builder
public class AuthenticationTokenData {

    private  String username;
    private String password;


}
