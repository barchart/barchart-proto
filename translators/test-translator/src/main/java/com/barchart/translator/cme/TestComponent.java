package com.barchart.translator.cme;

import java.lang.reflect.Method;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barchart.conf.sync.api.ConfigManager;

@Component(immediate = true)
public class TestComponent {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	private ConfigManager config;
	
	
	@Activate
	protected void activate() {
		logger.info("www");
		
		
		logger.info("Class loader: " + config.getClass().getClassLoader());
		logger.info("config: " + config.getClass());
		
		
		logger.info("Ident: " + config.getIdentity());
		
		for (Method m : config.getClass().getMethods()) {
			logger.info("Method: " + m);
		}
		
		logger.info("Received config.....d......");
//		logger.info("Is config valid: " + config.isConfigValid());
		logger.info("Config: " + config.getConfig());

	}

	@Deactivate
	protected void deactivate() {
		logger.info("Deactivate.......");
	}
	
	@Reference
	public void bind(ConfigManager config) {
		this.config = config;
		
		
	}
	
	public void unbind(ConfigManager config) {////
		
	}

}
