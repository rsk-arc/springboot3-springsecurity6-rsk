package com.rsk.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class SecurityConfiguration {

	private final JwtAuthenticationFilter jwtAuthFilter;
	private final AuthenticationProvider authenticationProvider;

	private static final String[] AUTH_WHITELIST = {
			"/public/v1/**",
			"/api/v1/auth/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html"
	};
	
	@Bean
	@Order(1)
	public SecurityFilterChain configurePublicEndpoints(HttpSecurity http) throws Exception {
		log.info("SecurityConfiguration... configurePublicEndpoints ");
		http.csrf(csrf -> csrf.disable()).securityMatcher(AUTH_WHITELIST)
				.authorizeHttpRequests((request) -> request.anyRequest().permitAll());

		return http.build();
	}
	  
	@Bean
	@Order(2)
	public SecurityFilterChain applicationAuthFilterChain(HttpSecurity http) throws Exception {
		log.info("SecurityConfiguration... applicationAuthFilterChain ");
		http.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(request -> request.requestMatchers("/app/v1/**").permitAll().anyRequest().authenticated())
			.sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authenticationProvider(authenticationProvider)
			.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
		
}
