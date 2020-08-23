package com.akshay.biddingsystem.util;

import java.util.Locale;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;


@Component
public class Messages {
	@Autowired
	private MessageSource messageSource;
	
	private MessageSourceAccessor accessor;
	
	@PostConstruct
    private void init() {
        accessor = new MessageSourceAccessor(messageSource, Locale.ENGLISH);
    }
	
    public String get(String code) {
        return accessor.getMessage(code);
    }
    
    public String getStastusCode(String code){
    	code = code.trim();
    	return accessor.getMessage(code).trim();
    }
    
    public void setLocale(String keyLang){
        accessor = new MessageSourceAccessor(messageSource, Locale.forLanguageTag(keyLang));
    }
	
}