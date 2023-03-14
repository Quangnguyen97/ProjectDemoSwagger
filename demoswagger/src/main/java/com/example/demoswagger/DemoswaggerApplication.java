package com.example.demoswagger;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.example.demoswagger.Module.*;

@SpringBootApplication
@ComponentScan(basePackages = { "com.example.demoswagger.BasicAuthen" })
@ComponentScan(basePackages = { "com.example.demoswagger.Module" })
@ComponentScan(basePackages = { "com.example.demoswagger.Account" })
@ComponentScan(basePackages = { "com.example.demoswagger.User" })
@ComponentScan(basePackages = { "com.example.demoswagger.Notification" })
@ComponentScan(basePackages = { "com.example.demoswagger.MapStruct" })
@ComponentScan(basePackages = { "com.example.demoswagger.Response" })
@ComponentScan(basePackages = { "com.example.demoswagger.Controller" })
@ComponentScan(basePackages = { "com.example.demoswagger.Swagger" })
@ComponentScan(basePackages = { "com.example.demoswagger.SQLServer" })
public class DemoswaggerApplication {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		try {
			SpringApplication.run(DemoswaggerApplication.class, args);
		} catch (Exception e) {
			throw new ResourceException(e.getMessage());
		}
	}
}
