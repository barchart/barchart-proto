package com.barchart.translator.cme;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barchart.conf.sync.api.ConfigManager;
import com.barchart.proto.buf.data.MarketPacket;

@Component(immediate = true)
public class TestComponent implements EventHandler {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	private ConfigManager config;
	
	
	@Activate
	protected void activate() {
		logger.info("IIIIIIIIIIIIIIIIIIIIIIII");
		logger.info("Received config");
		logger.info("Config: " + config.getConfig());
		MarketPacket.Builder builder = MarketPacket.newBuilder();
		builder.setSequence(1234L);
		MarketPacket build = builder.build();
		logger.info("Packet: " + build);
		
		
		
	}

	@Deactivate
	protected void deactivate() {
		logger.info("Deactivate.......");
	}
	
	@Reference
	public void bind(ConfigManager config) {
		this.config = config;
		
		
	}
	
	public void unbind(ConfigManager config) {
		
	}

	@Override
	public void handleEvent(Event event) {
		logger.info("event : {}", event.getTopic());		
	}

}
