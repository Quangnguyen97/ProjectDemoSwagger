package com.example.demoswagger.basicauthen;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyBasicAuthenticationLogin {

    private String user;
    private String pass;

    public MyBasicAuthenticationLogin() {
    }

    public MyBasicAuthenticationLogin(String user, String pass) {
        super();
        this.user = user;
        this.pass = pass;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
