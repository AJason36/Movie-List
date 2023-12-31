package com.nostratech.movie.service.impl;

import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.nostratech.movie.config.ApplicationProperties;
import com.nostratech.movie.config.CloudProperties;
import com.nostratech.movie.service.GreetingService;


@Service
public class GreetingServiceImpl implements GreetingService {

	Logger log = LoggerFactory.getLogger(GreetingServiceImpl.class);

	
	private ApplicationProperties appProperties;
	
	
	private CloudProperties cloudProperties;

	public GreetingServiceImpl(ApplicationProperties appProperties, CloudProperties cloudProperties) {
		super();
		this.appProperties = appProperties;
		this.cloudProperties = cloudProperties;
	}



	@Override
	public String sayGreeting() {
		log.trace("this is log TRACE");
		log.debug("this is log DEBUG");
		log.info("this is log INFO");
		log.warn("this is log WARN");
		log.error("this is log ERROR");
		System.out.println(cloudProperties.getApiKey());
		TimeZone timezone = TimeZone.getTimeZone(appProperties.getTimezone());
		return appProperties.getWelcomeText()+", our timezone :"+timezone.getDisplayName()+
				", our currency:"+appProperties.getCurrency();
	}
	

}
