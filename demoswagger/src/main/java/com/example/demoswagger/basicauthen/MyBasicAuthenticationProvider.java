package com.example.demoswagger.basicauthen;

import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class MyBasicAuthenticationProvider implements AuthenticationProvider {

    private final JdbcTemplate jdbcTemplate;

    public MyBasicAuthenticationProvider(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        try {
            String username = auth.getName();
            String password = auth.getCredentials().toString();

            String sql = "SELECT TOP 1 MaNguoiDung, MatKhau FROM COM_systbl_NguoiDung " +
                    "WHERE MaNguoiDung=N'" + username + "' AND MatKhau=N'" + password + "' AND KhongHienHoatHT=0";
            List<MyBasicAuthenticationLogin> listLogin = jdbcTemplate.query(sql,
                    (resource, rowNum) -> new MyBasicAuthenticationLogin(
                            resource.getString("MaNguoiDung"),
                            resource.getString("MatKhau")));
            if (listLogin.isEmpty()) {
                throw new BadCredentialsException("Login information " + HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            MyBasicAuthenticationLogin login = listLogin.stream()
                    .filter(myLogin -> username.equals(myLogin.getUser()))
                    .filter(myLogin -> password.equals(myLogin.getPass()))
                    .findAny()
                    .orElse(null);
            if (login == null) {
                throw new BadCredentialsException("External system authentication failed");
            }
            return new UsernamePasswordAuthenticationToken(username, password, Collections.emptyList());
        } catch (Exception e) {
            throw new BadCredentialsException(e.getMessage());
        }
    }

    @Override
    public boolean supports(Class<?> auth) {
        return auth.equals(UsernamePasswordAuthenticationToken.class);
    }
}
