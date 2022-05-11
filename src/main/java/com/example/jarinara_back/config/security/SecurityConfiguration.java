package com.example.jarinara_back.config.security;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.filter.CorsFilter;

@EnableWebSecurity
@Slf4j
@AllArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private final JwtAuthenticationFilter jwtAuthenticationFilter;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.cors()
			.and()
			.csrf()
				.disable()
			.httpBasic()
				.disable()
			.sessionManagement() // 세션 기반이 아님을 선언.
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authorizeRequests()
				.antMatchers("/", "/api/auth/**").permitAll()
			.anyRequest()
				.authenticated();

		http.addFilterAfter(
			jwtAuthenticationFilter,
			CorsFilter.class
		);
	}
}
