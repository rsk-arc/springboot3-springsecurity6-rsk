package com.rsk.security.config;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class LanguageLocaleResolver extends AcceptHeaderLocaleResolver implements WebMvcConfigurer {

	List<Locale> LOCALES = Arrays.asList(new Locale("en"), new Locale("fr"));
	
//	@Autowired
//	private LanguageRepository languageRepository;

	@Override
	public Locale resolveLocale(HttpServletRequest request) {
		String headerLang = request.getHeader("Accept-Language");
		return headerLang == null || headerLang.isEmpty() ? Locale.getDefault()
				: Locale.lookup(Locale.LanguageRange.parse(headerLang), LOCALES);
	}

	
	@Bean("messageSource")
	public ResourceBundleMessageSource messageSource() {
		log.info("CustomLocaleResolver... messageSource ");
		
		//Language message = languageRepository.findByKeyAndLocale("msg.text", "en" );
		//List<Language> list = languageRepository.findAll();
		
		ResourceBundleMessageSource rs = new ResourceBundleMessageSource();
		rs.setBasename("messages");
		rs.setDefaultEncoding("UTF-8");
		rs.setUseCodeAsDefaultMessage(true);
		
		rs.setCommonMessages(null);
		
		return rs;
	}
}