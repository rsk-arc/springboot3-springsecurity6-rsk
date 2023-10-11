package com.rsk.security.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rsk.security.constant.App;
import com.rsk.security.dao.ResponseBean;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
@Slf4j
public class ApplicationController {

	
	
	@PostMapping("welcome")
	public ResponseEntity<ResponseBean> sayHelloWelcome() {
		
		log.info("ApplicationController ... start");
		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put("welcome", "Hello! Welcome");
		ResponseBean returnMap = ResponseBean.builder()
				.withResponseMap(ret)
				.withStatus(App.Return.SUCCESS)
				.withMessage("success").build();
		return ResponseEntity.ok(returnMap);
	}
	
}