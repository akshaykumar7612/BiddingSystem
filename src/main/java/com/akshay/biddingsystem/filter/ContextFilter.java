package com.akshay.biddingsystem.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.Map;
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

import org.hibernate.cfg.Ejb3DiscriminatorColumn;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerMapping;

import com.akshay.biddingsystem.dto.OutputResponseDto;
import com.akshay.biddingsystem.util.Context;
import com.akshay.biddingsystem.util.Messages;
import com.google.gson.Gson;

@Component
@WebFilter(urlPatterns = "/*")
public class ContextFilter implements Filter {
	
	@Autowired
	Gson gson;
	
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
			if (((HttpServletRequest) request).getRequestURL().toString().endsWith("/bid")) {
				if(httpRequest.getHeader("context") != null) {
					byte[] decodedBytes = Base64.getDecoder().decode(httpRequest.getHeader("context"));
					String decodedString = new String(decodedBytes);
					logger.info("Decoded String: " + decodedString);
					JSONParser jobj = new JSONParser();
					JSONObject jsonObj = (JSONObject) jobj.parse(decodedString);
					logger.info("Json: " + jsonObj.toJSONString());
					Long nUserId = (Long) jsonObj.get("userId");
					if(nUserId!=null) {
						context.setUserId((Long) jsonObj.get("userId"));
						context.setTraceId((String) jsonObj.get("traceId"));
						context.setUserAgent((String) jsonObj.get("userAgent"));
						context.setLanguageCode((String) jsonObj.get("languageCode"));
						messages.setLocale(context.getLanguageCode());
						MDC.put("Context", context.toString());
						logger.info("Context: {}" + context);
					} else {
						OutputResponseDto outputResponseDto = new OutputResponseDto(false, null,
								messages.getStastusCode("bs.failure.auction.placeBidAuthFailed.message.id"),
								messages.get("bs.failure.auction.placeBidAuthFailed.message"),"9999");
						logger.error("User Id Not Found Authentication Failed " + messages.get("bs.failure.auction.placeBidAuthFailed.message"));
						String sAppCode = outputResponseDto.getStatusCode().toString();
						String sCode = sAppCode.split("-")[1].toString();
						Integer st = Integer.parseInt(sCode);
						httpResponse.setStatus(st);
						httpResponse.getWriter().write(gson.toJson(outputResponseDto));
						return;
					}
				} else {
					OutputResponseDto outputResponseDto = new OutputResponseDto(false, null,
							messages.getStastusCode("bs.failure.auction.placeBidAuthFailed.message.id"),
							messages.get("bs.failure.auction.placeBidAuthFailed.message"),"9999");
					logger.error("Context Not Found Authentication Failed " + messages.get("bs.failure.auction.placeBidAuthFailed.message"));
					String sAppCode = outputResponseDto.getStatusCode().toString();
					String sCode = sAppCode.split("-")[1].toString();
					Integer st = Integer.parseInt(sCode);
					httpResponse.setStatus(st);
					httpResponse.getWriter().write(gson.toJson(outputResponseDto));
					return;
				}
			} else {
				context.setTraceId(UUID.randomUUID().toString());
				context.setUserAgent("Chrome");
				context.setUserId(-1L);
				context.setLanguageCode("en");
				logger.info("Context: {}" + context);
				MDC.put("Context", context.toString());
			}
		} catch (Exception e) {
			logger.error("Exception  "+ e);
			httpResponse.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
			OutputResponseDto outputResponseDto = new OutputResponseDto(false, null,
					messages.getStastusCode("common.filter.exception.invalidParameters.id"),
					messages.get("common.filter.exception.invalidParameters"), "9999");
			logger.error(outputResponseDto.toString());
			httpResponse.getWriter().write(gson.toJson(outputResponseDto));
			MDC.clear();
			return;
		}
		chain.doFilter(httpRequest, httpResponse);

	}

}
