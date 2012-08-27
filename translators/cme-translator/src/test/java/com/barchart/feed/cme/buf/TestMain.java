package com.barchart.feed.cme.buf;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.barchart.conf.list.ConfigListBuilder;
import com.barchart.conf.repo.api.ConfigService;
import com.barchart.conf.repo.impl.ConfigServiceProvider;
import com.barchart.conf.sync.api.ConfigManager;
import com.barchart.conf.sync.impl.ConfigManagerProvider;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class TestMain {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
//		ConfigService configService = new ConfigServiceProvider();
//		ConfigManagerProvider configManager = new ConfigManagerProvider();
//		Method declaredMethod = ConfigManagerProvider.class.getDeclaredMethod("bind", ConfigService.class);
//		declaredMethod.setAccessible(true);
//		declaredMethod.invoke(configManager, configService);
//		Config config = configManager.getConfig();
//		System.out.println(config);
//		
//		
		
		File file = new File("/home/joel/git/barchart-configuration-development/instance/internal/bcinc/PC-DUDLEY/application.conf");
		/** parse configuration */
		final Config step1 = ConfigFactory.parseFile(file);

		/** perform substitutions */
		final Config step2 = step1.resolve();

		/** apply list builder */
		final Config config = ConfigListBuilder.process(step2);
		System.out.println(config);
	}
	
	
	
}
