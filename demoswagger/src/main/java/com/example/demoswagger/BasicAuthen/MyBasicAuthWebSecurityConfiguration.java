package com.example.demoswagger.BasicAuthen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demoswagger.Module.*;

@Configuration
@EnableWebSecurity
@ComponentScan("com.example.demoswagger.BasicAuthen")
public class MyBasicAuthWebSecurityConfiguration {

    @Autowired
    private MyBasicAuthenticationProvider authenticationProvider;

    @Autowired
    private MyBasicAuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authentication) throws Exception {
        try {
            PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
            authentication.authenticationProvider(authenticationProvider);
            authentication
                    .inMemoryAuthentication()
                    .withUser("admin")
                    .password(encoder.encode("123456"))
                    .authorities("ROLE_ADMIN");
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
