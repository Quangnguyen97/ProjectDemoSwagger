package com.example.demoswagger.BasicAuthen;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MyBasicAuthenticationLoginRowMapper implements RowMapper<MyBasicAuthenticationLogin> {
    @Override
    public MyBasicAuthenticationLogin mapRow(ResultSet rs, int rowNum) throws SQLException {

        MyBasicAuthenticationLogin myBasicAuthenticationLogin = new MyBasicAuthenticationLogin();
        myBasicAuthenticationLogin.setMaNguoiDung(rs.getString("MaNguoiDung"));
        myBasicAuthenticationLogin.setMatKhau(rs.getString("MatKhau"));

        return myBasicAuthenticationLogin;

    }
}
