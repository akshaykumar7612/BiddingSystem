package com.akshay.util;

import java.util.UUID;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public class Context {
	
	private UUID traceId;
	private String userAgent;
	private Long userId;
	
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getTraceId() {
		return traceId.toString();
	}

	public void setTraceId(UUID traceId) {
		this.traceId = traceId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long nUserId) {
		this.userId = nUserId;
	}

	@Override
	public String toString() {
		return "Context [traceId=" + traceId + ", userAgent=" + userAgent + ", userId=" + userId  + "]";
	}	

}