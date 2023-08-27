package com.cards.cards.configs;

import java.nio.charset.StandardCharsets;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class MessageSourceConfig {

	@Bean
	@Primary
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
		messageSource.setUseCodeAsDefaultMessage(true);
		messageSource.setBasenames("classpath:/message/error", "classpath:/message/validation");
		messageSource.setCacheSeconds(300);
		return messageSource;
	}

}
