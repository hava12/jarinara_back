package com.example.jarinara_back.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 기본 AuthenticationFilter 는 UsernamePasswordAuthenticationFilter를 사용한다.
		http.formLogin()
			// 로그인 즉 인증 처리를 하는 URL을 설정합니다.
			// 호출되면 인증처리를 수행하는 필터가 호출됩니다.
			.loginProcessingUrl("/login")
			.usernameParameter("userId")
			.passwordParameter("password");

		http.logout()
			.logoutUrl("/logout")
			.deleteCookies("JSESSIONID");

		http.authorizeRequests()
			.antMatchers("/**").permitAll()
			.antMatchers("/h2-console/**").permitAll()
		.and()
			.csrf()
			.ignoringAntMatchers("/h2-console/**").disable()
			.httpBasic();

		// 세션 고정 보호, 세션 정책 설정
		http.sessionManagement()
			.sessionFixation().changeSessionId()
			.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
	}
}
