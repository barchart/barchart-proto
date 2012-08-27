package com.barchart.translator.common;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Property;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barchart.conf.event.ConfigEvent;
import com.barchart.conf.sync.api.ConfigManager;

@Component(immediate = true)
public class TranslatorApp implements EventHandler {
	private static final Logger logger = LoggerFactory.getLogger(TranslatorApp.class);

	@Property(name = EventConstants.EVENT_TOPIC)
	static final String TOPIC = ConfigEvent.CONFIG_CHANGE;

	private Map<String, TranslatorFactory> translatorFactories;
	
	private ConfigManager configManager;

	public TranslatorApp() {
		this.translatorFactories = new HashMap<String, TranslatorFactory>();
	}

	@Reference
	public void bind(TranslatorFactory translatorFactory) {
		Class<? extends TranslatorFactory> clazz = translatorFactory.getClass();
		String className = clazz.getName();
		translatorFactories.put(className, translatorFactory);
		logger.info("Bound to TranslatorFactory: " + className);
	}
	
	public void unbind(TranslatorFactory translatorFactory) {
		Class<? extends TranslatorFactory> clazz = translatorFactory.getClass();
		String className = clazz.getName();
		TranslatorFactory removed = translatorFactories.remove(className);
		if (removed == null) {
			throw new IllegalStateException("Unexpected unbind from TranslatorFactory: " + translatorFactory);
		}
		logger.info("Unbind translator factory: " + translatorFactory);
	}
	
	
	@Activate
	protected void activate() {
		logger.info("Activate Translator");
	}

	@Deactivate
	protected void deactivate() {
		logger.info("Deactivate Translator");
	}

	@Reference
	public void bind(ConfigManager configManager) {
		this.configManager = configManager;
	}

	public void unbind(ConfigManager configManager) {
		this.configManager = null;
	}
	
	@Override
	public void handleEvent(final Event event) {
		logger.info("Configuration changed.");
	}

}
