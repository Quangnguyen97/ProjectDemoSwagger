package com.example.demoswagger.BasicAuthen;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

@Component
public class MyBasicAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        try {
            String username = auth.getName();
            String password = auth.getCredentials()
                    .toString();

            String sql = "SELECT TOP 1 MaNguoiDung, MatKhau FROM COM_systbl_NguoiDung " +
                    "WHERE MaNguoiDung=N'" + username + "' AND MatKhau=N'" + password + "' AND KhongHienHoatHT=0";
            List<MyBasicAuthenticationLogin> ListLogin = jdbcTemplate.query(sql,
                    BeanPropertyRowMapper.newInstance(MyBasicAuthenticationLogin.class));

            if (ListLogin.isEmpty()) {
                throw new BadCredentialsException("Login information " + HttpStatus.NOT_FOUND.getReasonPhrase());
            }
            MyBasicAuthenticationLogin Login = ListLogin.stream()
                    .filter(myBasicAuthenticationLogin -> username.equals(myBasicAuthenticationLogin.getMaNguoiDung()))
                    .filter(myBasicAuthenticationLogin -> password.equals(myBasicAuthenticationLogin.getMatKhau()))
                    .findAny()
                    .orElse(null);
            if (Login == null) {
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
