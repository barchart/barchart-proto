package com.barchart.translator.common.config.typesafe;

import org.junit.Test;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class TypesafeTesting {

	
	@Test
	public void experiment() {
		Config conf = ConfigFactory.load("network_cme.conf");
		System.out.println(conf);
		conf.getString("address");
	}
	
}
