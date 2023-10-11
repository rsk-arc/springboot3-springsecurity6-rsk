package com.rsk.security.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rsk.security.constant.App;
import com.rsk.security.dao.ResponseBean;
import com.rsk.security.dao.SignUpRequest;
import com.rsk.security.dao.SigninRequest;
import com.rsk.security.exception.ApplicationException;
import com.rsk.security.service.AuthenticationService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/auth/")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {

	private final AuthenticationService authenticationService;

	@PostMapping("signup")
	public ResponseEntity<ResponseBean> signup(@RequestBody SignUpRequest request) {
		log.info("AuthenticationController... signup");
		try {
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("payload", authenticationService.signup(request));
		ResponseBean returnMap = ResponseBean.builder()
				.withResponseMap(ret)
				.withStatus(App.Return.SUCCESS)
				.withMessage("success").build();
		
		return ResponseEntity.ok(returnMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}
	}

	@PostMapping("signin")
	public ResponseEntity<ResponseBean> signin(@RequestBody SigninRequest request) {
		try {
			log.info("AuthenticationController... signin");
			Map<String, Object> ret = new HashMap<String, Object>();
			ret.putAll(authenticationService.signin(request));
			ResponseBean returnMap = ResponseBean.builder()
					.withResponseMap(ret)
					.withStatus(App.Return.SUCCESS)
					.withMessage("success").build();
			
			return ResponseEntity.ok(returnMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}
	}

//	@PostMapping("refresh-token")
//	public void refreshToken(HttpServletRequest request, HttpServletResponse response) {
//		log.info("AuthenticationController ... refresh-token");
//		try {
//			authenticationService.refreshToken(request, response);
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new ApplicationException(e.getMessage());
//		}
//	}

	@PostMapping("refresh-token")
	public ResponseEntity<ResponseBean> refreshToken(HttpServletRequest request, HttpServletResponse response) {
		log.info("AuthenticationController ... refresh-token");
		try {
			
			Map<String, Object> ret = new HashMap<String, Object>();
			ret.putAll(authenticationService.refreshToken(request, response));
			ResponseBean returnMap = ResponseBean.builder()
					.withResponseMap(ret)
					.withStatus(App.Return.SUCCESS)
					.withMessage("success").build();
			
			return ResponseEntity.ok(returnMap);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}
	}
	
	@PostMapping("logout")
	public ResponseEntity<ResponseBean> logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		log.info("AuthenticationController ... logout");
		try {
			
			Map<String, Object> ret = new HashMap<String, Object>();
			ret.put("payload", authenticationService.logout(request, response));
			ResponseBean returnMap = ResponseBean.builder()
					.withResponseMap(ret)
					.withStatus(App.Return.SUCCESS)
					.withMessage("success").build();
			
			return ResponseEntity.ok(returnMap);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}
	}
}
