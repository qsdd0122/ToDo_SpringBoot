package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.filter.CorsFilter;

import com.example.demo.security.JwtAuthenticationFilter;

import lombok.extern.slf4j.Slf4j;

@EnableWebSecurity
@Slf4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		// http ��ť��Ƽ ����
		http.cors() // WebMvcConfig���� �̹� ���������Ƿ� �⺻ cors ����
			.and()
			.csrf()
				.disable()
			.httpBasic()
				.disable()
			.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // session����� �ƴ��� ����
			.and()
			.authorizeRequests()
				.antMatchers("/", "/auth/**").permitAll() // /�� /auth/** ��δ� ���� ���ص� ��
			.anyRequest().authenticated(); // �̿��� ��� ��δ� �����ؾ���
		
		// filter ���, �� ��û���� CorsFilter ������ �� jwtAuthenticationFilter ����
		http.addFilterAfter(jwtAuthenticationFilter, CorsFilter.class);
	}
	
	
}
