package com.barchart.proto.util.diff;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.barchart.proto.util.diff.ProtoDiff.Difference;
import com.barchart.proto.util.diff.ProtoDiffMessage.Builder;
import com.google.protobuf.ByteString;

public class TestProtoDiff {

	
	private static final double DELTA = .000001;
	private ProtoDiffMessage.Builder expectedBuilder;
	private ProtoDiffMessage.Builder actualBuilder;
	
	@Before()
	public void setup() {
		this.expectedBuilder = createTestMessageBuilder();
		this.actualBuilder = createTestMessageBuilder();
	}
	
	
	
	private Builder createTestMessageBuilder() {
		ProtoDiffMessage.Builder builder = ProtoDiffMessage.newBuilder();
		builder.setDoubleValue(1234.00);
		builder.setInt32Value(1111);
		builder.setInt64Value(234523452435234L);
		builder.setUint32Value(65431);
		builder.setUint64Value(9191919191991L);
		builder.setSint32Value(-1234);
		builder.setSint64Value(-99999999);
		builder.setFixed32Value(12341234);
		builder.setFixed64Value(1234123499999L);
		builder.setBoolValue(true);
		builder.setStringValue("abcdefghijklmnopqrstuvwxyz");
		builder.setBytesValue(ByteString.copyFromUtf8("1234567890")); 
		return builder;
	}


	@Test
	public void noDifferences() {
		ProtoDiffMessage expected = expectedBuilder.build();
		ProtoDiffMessage actual = actualBuilder.build();
		ProtoDiff diff = new ProtoDiff(expected, actual);
		assertFalse(diff.hasDifferences());
		assertEquals(0, diff.getDifferenceCount());
	}
	

	@Test
	public void doubleDiff() {
		actualBuilder.setDoubleValue(123.00);
		performSingleDiff(expectedBuilder.getDoubleValue(), actualBuilder.getDoubleValue());
	}

	@Test
	public void floatDiff() {
		actualBuilder.setFloatValue(987.0f);
		performSingleDiff(expectedBuilder.getFloatValue(), actualBuilder.getFloatValue());
	}
	
	@Test
	public void int32Diff() {
		actualBuilder.setUint32Value(1234123);
		performSingleDiff(expectedBuilder.getUint32Value(), actualBuilder.getUint32Value());
	}
	
	@Test
	public void int64Diff() {
		actualBuilder.setInt64Value(99999999999L);
		performSingleDiff(expectedBuilder.getInt64Value(), actualBuilder.getInt64Value());
	}

	@Test
	public void uint32Diff() {
		actualBuilder.setUint32Value(12341634);
		performSingleDiff(expectedBuilder.getUint32Value(), actualBuilder.getUint32Value());
	}
	
	
	@Test
	public void uint64Diff() {
		actualBuilder.setUint64Value(1234123412341224L);
		performSingleDiff(expectedBuilder.getUint64Value(), actualBuilder.getUint64Value());
	}
	
	
	
	
	private void performSingleDiff(Object expectedValue, Object actualValue) {
		ProtoDiffMessage expected = expectedBuilder.build();
		ProtoDiffMessage actual = actualBuilder.build();
		ProtoDiff diff = new ProtoDiff(expected, actual);
		expectSingleDifference(expectedValue, actualValue, diff);
	}

	private void expectSingleDifference(Object expectExpected, Object expectActual, ProtoDiff diff) {
		assertTrue(diff.hasDifferences());
		assertEquals(1, diff.getDifferenceCount());
		List<Difference> differences = diff.getDifferences();
		Difference difference = differences.get(0);
		expectDifference(expectExpected, expectActual, difference);
	}

	
	private void expectDifference(Object expectExpected, Object expectActual, Difference difference ) {
		assertEquals(expectExpected, difference.getExpected());
		assertEquals(expectActual, difference.getActual());
	}
	
}

