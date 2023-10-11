package com.rsk.security.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rsk.security.component.SessionService;
import com.rsk.security.dao.SessionBean;
import com.rsk.security.dao.SignUpRequest;
import com.rsk.security.dao.SigninRequest;
import com.rsk.security.entities.Role;
import com.rsk.security.entities.Token;
import com.rsk.security.entities.TokenType;
import com.rsk.security.entities.User;
import com.rsk.security.exception.DatabaseException;
import com.rsk.security.exception.InvalidInputException;
import com.rsk.security.repository.TokenRepository;
import com.rsk.security.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {

	private final UserRepository userRepository;
	private final TokenRepository tokenRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authManager;
	private final SessionService sessionService;

	public User signup(SignUpRequest request) {
		log.info("UserService... signup->start");
		try {
			var user = User.builder().firstName(request.getFirstName()).lastName(request.getLastName())
					.email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
					.role(Role.findByRole(request.getRole().toUpperCase())).build();

			return userRepository.save(user);
		} catch (Exception ex) {
			throw new DatabaseException(ex.getMessage());
		}
	}

	public Map<String, Object> signin(SigninRequest request) {
		log.info("AuthenticationService... signin->start");
		authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		var user = userRepository.findByEmail(request.getEmail())
				.orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
		var accessToken = jwtService.generateToken(user);
		var refreshToken = jwtService.generateRefreshToken(user);
		saveUserToken(user, accessToken);

		List<String> roles = new ArrayList<String>();
		roles.add(user.getRole().getRole());

		var session = SessionBean.builder().sessionToken(accessToken).refreshToken(refreshToken)
				.employeeId(user.getId()).employeeEmail(user.getEmail()).roles(roles).employeeName(user.getUsername())
				.build();

		sessionService.addSession(session);

		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.put("accessToken", accessToken);
		retMap.put("refreshToken", refreshToken);

		return retMap;
	}

	public Map<String, Object> refreshToken(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		final String refreshToken;
		final String userEmail;
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			return null;
		}
		refreshToken = authHeader.substring(7);

		userEmail = jwtService.extractUserName(refreshToken);

		if (userEmail != null) {
			var user = userRepository.findByEmail(userEmail).orElseThrow();

			if (jwtService.isTokenValid(refreshToken, user)) {
				var accessToken = jwtService.generateToken(user);
				revokeAllUserTokens(user);
				saveUserToken(user, accessToken);

				Map<String, Object> ret = new HashMap<String, Object>();
				ret.put("accessToken", accessToken);
				ret.put("refreshToken", refreshToken);
				return ret;
			}
		}
		return null;
	}

	public boolean logout(HttpServletRequest request, HttpServletResponse response) {
		log.info("LogoutService ... logout");

		final String authHeader = request.getHeader("Authorization");
		final String jwt;
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			throw new InvalidInputException("InvalidInput token");
		}
		jwt = authHeader.substring(7);
		var storedToken = tokenRepository.findByToken(jwt).orElse(null);
		if (storedToken != null) {
			storedToken.setExpired(true);
			storedToken.setRevoked(true);
			tokenRepository.save(storedToken);
			SecurityContextHolder.clearContext();
			return true;
		}
		throw new InvalidInputException("InvalidInput for logout");
	}

	private void saveUserToken(User user, String jwtToken) {
		try {
			var token = Token.builder().user(user).token(jwtToken).tokenType(TokenType.BEARER).expired(false)
					.revoked(false).build();
			tokenRepository.save(token);
		} catch (org.hibernate.exception.ConstraintViolationException ex) {

		}
	}

	private void revokeAllUserTokens(User user) {
		var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
		if (validUserTokens.isEmpty()) {
			return;
		}
		validUserTokens.forEach(token -> {
			token.setExpired(true);
			token.setRevoked(true);
		});
		tokenRepository.saveAll(validUserTokens);
	}
}
