package com.barchart.translator.common.jform;

import com.barchart.translator.common.lookup.ValueLookup;
import com.google.common.collect.ImmutableMap.Builder;

public class MessageBlock extends CharField {

	private static final ValueLookup<Character, FieldBlock> lookup = new ValueLookup<Character, FieldBlock>() {
		@Override
		public void init(Builder<Character, FieldBlock> builder) {
			
		}
	};
	

	
	
}
