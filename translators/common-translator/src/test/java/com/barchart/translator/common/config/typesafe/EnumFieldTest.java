package com.barchart.translator.common.config.typesafe;

import org.junit.Test;

import com.barchart.translator.common.jform.EnumField;

public class EnumFieldTest {

	public enum TestEnum {
		A,B,C
	}
	
	@Test
	public void testEnum() {
		EnumField<TestEnum> field = new EnumField(TestEnum.class);
		
	}
	
}
