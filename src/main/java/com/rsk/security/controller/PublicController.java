package com.rsk.security.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rsk.security.component.Translator;
import com.rsk.security.constant.App;
import com.rsk.security.dao.ResponseBean;
import com.rsk.security.exception.InvalidInputException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/public/v1/")
@RequiredArgsConstructor
@Slf4j
public class PublicController {

	@PostMapping("test")
	public ResponseEntity<ResponseBean> sayHello() {
		log.info("APIController ... public Say Hello");
		try {
			Map<String, Object> ret = new HashMap<String, Object>();
			String englishMessage = Translator.toLocale("home.welcome");
			log.info("{}",englishMessage);
			ret.put("welcomeMessage", englishMessage);
			ResponseBean returnMap = ResponseBean.builder()
					.withResponseMap(ret)
					.withStatus(App.Return.SUCCESS)
					.withMessage("success").build();
			//throw new InvalidInputException(englishMessage);
			return ResponseEntity.ok(returnMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw new InvalidInputException(e.getMessage());
		}
	}
}
