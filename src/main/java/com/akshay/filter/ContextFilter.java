package com.akshay.filter;

import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.akshay.dto.OutputResponseDto;
import com.akshay.util.Context;
import com.akshay.util.Messages;

@Component
@WebFilter(urlPatterns = "/*")
public class ContextFilter implements Filter {

	@Autowired
	private Context context;

	@Autowired
	Messages messages;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		final HttpServletRequest httpRequest = (HttpServletRequest) request;
		final HttpServletResponse httpResponse = (HttpServletResponse) response;

		try {
			logger.info("Input Request : {}" + ((HttpServletRequest) request).getServletPath());
			context.setTraceId(UUID.randomUUID());
			context.setUserAgent("Chrome");
			context.setUserId(0L);
			logger.info("Context: {}" + context);
			MDC.put("Context", context.toString());
		} catch (Exception e) {
			logger.error("Exception " + e);
			httpResponse.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
			OutputResponseDto outputResponseDto = new OutputResponseDto(false, null,
					messages.getStastusCode("common.filter.exception.invalidParameters.id"),
					messages.get("common.filter.exception.invalidParameters"), context.getTraceId());
			logger.error(outputResponseDto.toString());
			MDC.clear();
			return;
		}
		chain.doFilter(httpRequest, httpResponse);

	}

}
