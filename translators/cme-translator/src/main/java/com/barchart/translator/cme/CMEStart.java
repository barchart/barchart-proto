package com.barchart.translator.cme;

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
import com.typesafe.config.Config;

@Component(immediate = true)
public class CMEStart implements EventHandler {

	private static final Logger logger = LoggerFactory.getLogger(CMEStart.class);

	@Property(name = EventConstants.EVENT_TOPIC)
	static final String TOPIC = ConfigEvent.ALL;

	@Activate
	protected void activate() {
		logger.info("Activate CME...");
	}

	@Deactivate
	protected void deactivate() {
		logger.info("Deactivate CME");
	}

	@Reference
	public void bind(ConfigManager configManager) {
		Config config = configManager.getConfig();
		logger.info("Got config:... " + config);
	}
	
	public void unbind(ConfigManager configManager) {
		
	}
	
	
	
	@Override
	public void handleEvent(final Event event) {
		logger.info("event : {}", event.getTopic());
	}

}
