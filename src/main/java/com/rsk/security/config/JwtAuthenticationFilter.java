package com.rsk.security.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.rsk.security.exception.ApplicationException;
import com.rsk.security.exception.Unauthorized;
import com.rsk.security.repository.TokenRepository;
import com.rsk.security.service.JwtService;
import com.rsk.security.service.UserService;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtService jwtService;
	private final UserService userService;
	private final TokenRepository tokenRepository;
	
	@Autowired
	@Qualifier("handlerExceptionResolver")
	private HandlerExceptionResolver resolver;
	
	@Value("${app.request.header.validation}")
	private boolean requestHeaderValidation = false;

	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
			@NonNull FilterChain filterChain) throws ServletException, IOException {
		try {
			if (requestHeaderValidation && !isAuthRequest(request)) {
				response.setStatus(HttpStatus.UNAUTHORIZED.value());
				throw new Unauthorized("Invalied request Failed");
			}

			log.info("JwtAuthenticationFilter ServiceName {}", request.getServletPath());
			if (request.getServletPath().contains("/api/v1/auth")) {
				filterChain.doFilter(request, response);
				return;
			}
			final String authHeader = request.getHeader("Authorization");
			if (authHeader == null || !authHeader.startsWith("Bearer ")) {
				filterChain.doFilter(request, response);
				return;
			}
			
			final String jwt = authHeader.substring(7);
			log.info("JwtAuthenticationFilter ...jwt : {}", jwt);
			final String userEmail = jwtService.extractUserName(jwt);
			
			if(userEmail == null) {
				throw new Unauthorized("Invalid JWT token");
			}
//			log.info("JwtAuthenticationFilter ...userEmail : {}", userEmail);
			if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails = userService.userDetailsService().loadUserByUsername(userEmail);
				var isTokenValid = tokenRepository.findByToken(jwt).map(t -> !t.isExpired() && !t.isRevoked())
						.orElse(false);
				log.info("JwtAuthenticationFilter ...isTokenValid 1: {}", isTokenValid);
//				log.info("JwtAuthenticationFilter ...isTokenValid 2: {}", jwtService.isTokenValid(jwt, userDetails));
				if (isTokenValid && jwtService.isTokenValid(jwt, userDetails)) {
					UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
							null, userDetails.getAuthorities());
					authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authToken);
				}else {
					throw new ApplicationException("Expired JWT token");
				}
			}
			filterChain.doFilter(request, response);
		} catch (Exception e) {
			//e.printStackTrace();
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			resolver.resolveException(request, response, null, e);
		}
	}
	
	private boolean isAuthRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization-Owner");
		log.info("JwtAuthenticationFilter bearerToken {}", bearerToken);
		if (!StringUtils.isEmpty(bearerToken) && bearerToken.toLowerCase().startsWith("myrequest")) {
			return true;
		}
		return false;
	}
}
