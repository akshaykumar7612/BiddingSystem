package com.akshay.biddingsystem.util;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public class Context {
	
	private String traceId;
	private String userAgent;
	private Long userId;
	private String languageCode;
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long nUserId) {
		this.userId = nUserId;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getTraceId() {
		return traceId.toString();
	}

	public void setTraceId(String traceId) {
		this.traceId = traceId;
	}
	
	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	@Override
	public String toString() {
		return "Context [traceId=" + traceId + ", userAgent=" + userAgent + ", userId=" + userId + ", languageCode="
				+ languageCode + "]";
	}
	
}