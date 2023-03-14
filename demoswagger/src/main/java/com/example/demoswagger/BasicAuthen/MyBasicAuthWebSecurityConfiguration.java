package com.example.demoswagger.BasicAuthen;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demoswagger.Module.*;

@Configuration
@EnableWebSecurity
public class MyBasicAuthWebSecurityConfiguration {

    @Autowired
    private MyBasicAuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authentication) throws Exception {
        try {
            PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
            authentication
                    .inMemoryAuthentication()
                    .withUser("admin")
                    .password(encoder.encode("123456"))
                    .authorities("ROLE_ADMIN");
            authentication
                    .jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                    .dataSource(dataSource)
                    .usersByUsernameQuery(
                            "SELECT MaNguoiDung, MatKhau FROM COM_systbl_NguoiDung where MaNguoiDung=? AND KhongHienHoatHT=0")
                    .authoritiesByUsernameQuery(
                            "SELECT MaNguoiDung, MatKhau FROM users COM_systbl_NguoiDung MaNguoiDung=? AND KhongHienHoatHT=0");
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        try {
            httpSecurity
                    .csrf().disable()
                    .cors().disable()
                    .httpBasic()
                    .and()
                    .authorizeRequests()
                    .antMatchers("/").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .formLogin().permitAll()
                    .and()
                    .httpBasic().authenticationEntryPoint(authenticationEntryPoint);
            return httpSecurity.build();
        } catch (Exception e) {
            throw new ResourceException(e.getMessage());
        }
    }
}
