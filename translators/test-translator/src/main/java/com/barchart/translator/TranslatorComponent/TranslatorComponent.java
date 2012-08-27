package com.barchart.translator.TranslatorComponent;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barchart.conf.sync.api.ConfigManager;
import com.barchart.translator.common.TranslatorFactory;

//@Component(immediate = true)
//public class TranslatorComponent implements EventHandler {
//
//	private final Logger logger = LoggerFactory.getLogger(getClass());
//
//	private ConfigManager configManager;
//	
//	@Activate
//	protected void activate() {
//		logger.info("Activate Translator Component");
//	}
//
//	@Deactivate
//	protected void deactivate() {
//		logger.info("Deactivate Translator Component");
//	}
//	
//	@Reference
//	public void bind(ConfigManager config) {
//		this.configManager = config;
//	}
//	
//	public void unbind(ConfigManager config) {
//		
//	}
//
//	
//	@Reference
//	public void bind(TranslatorFactory factory) {
//		
//	}
//	
//	@Override
//	public void handleEvent(Event event) {
//
//	}
//
//}
