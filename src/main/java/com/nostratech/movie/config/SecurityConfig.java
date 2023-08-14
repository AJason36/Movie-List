package com.nostratech.movie.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nostratech.movie.security.filter.UsernamePasswordAuthProcessingFilter;
import com.nostratech.movie.security.handler.UsernamePasswordAuthFailureHandler;
import com.nostratech.movie.security.handler.UsernamePasswordAuthSucessHandler;
import com.nostratech.movie.security.provider.UsernamePasswordAuthProvider;
import com.nostratech.movie.security.util.JWTTokenFactory;
import com.nostratech.movie.service.UsersService;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class SecurityConfig {
    @Autowired
    private UsersService userService;

    private final static String AUTH_URL = "/v1/login";
	
	
	@Autowired
	private UsernamePasswordAuthProvider usernamePasswordAuthProvider;
	
	@Bean
	public AuthenticationSuccessHandler successHandler(ObjectMapper objectMapper, JWTTokenFactory factory) {
		return new UsernamePasswordAuthSucessHandler(objectMapper, factory);
	}
	
	@Bean
	public AuthenticationFailureHandler failureHandler(ObjectMapper objectMapper) {
		return new UsernamePasswordAuthFailureHandler(objectMapper);
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	public UsernamePasswordAuthProcessingFilter usernamePasswordAuthProcessingFilter(ObjectMapper objectMapper, 
			AuthenticationSuccessHandler successHandler, AuthenticationFailureHandler failureHandler, AuthenticationManager authManager ) {
		UsernamePasswordAuthProcessingFilter filter = new UsernamePasswordAuthProcessingFilter(AUTH_URL, objectMapper, successHandler, failureHandler);
		filter.setAuthenticationManager(authManager);
		return filter;
	}
	
	@Autowired
	void registerProvider(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(usernamePasswordAuthProvider);
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http, UsernamePasswordAuthProcessingFilter usernamePasswordAuthProcessingFilter) throws Exception {
		http.authorizeHttpRequests().anyRequest().authenticated()
		.and()
		.csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.httpBasic();
		
		http.addFilterBefore(usernamePasswordAuthProcessingFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
}
