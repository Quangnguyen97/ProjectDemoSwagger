package com.example.demoswagger.basicauthen;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyBasicAuthenticationLogin {

    private String user;
    private String pass;

    public MyBasicAuthenticationLogin(String user, String pass) {
        super();
        this.user = user;
        this.pass = pass;
    }

}
