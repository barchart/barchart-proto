package com.barchart.proto.util;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestProtoUtil {

	private final static Logger log = LoggerFactory
			.getLogger(TestProtoUtil.class);

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDecimalDateOnly() {

		final DateOnlyValue source = new DateOnlyValue(2012, 12, 31);

		log.debug("source : {}", source);

		final int encoded = ProtoUtil.intoDecimalDateOnly(source);

		final DateOnlyValue target = ProtoUtil.fromDecimalDateOnly(encoded);

		log.debug("target : {}", target);

		assertEquals(source, target);

	}

	@Test
	public void testDecimalDateTime() {

		final DateTimeValue source = new DateTimeValue(2012, 12, 31, 23, 59,
				59, 999);

		log.debug("source : {}", source);

		final long encoded = ProtoUtil.intoDecimalDateTime(source);

		final DateTimeValue target = ProtoUtil.fromDecimalDateTime(encoded);

		log.debug("target : {}", target);

		assertEquals(source, target);

	}

	@Test
	public void testBinaryDateTime() {

		final DateTimeValue source = new DateTimeValue(2012, 12, 31, 23, 59,
				59, 999);

		log.debug("source : {}", source);

		final long encoded = ProtoUtil.intoBinaryDateTime(source);

		final DateTimeValue target = ProtoUtil.fromBinaryDateTime(encoded);

		log.debug("target : {}", target);

		assertEquals(source, target);

	}

	@Test
	public void testBinaryDateOnly() {

		final DateOnlyValue source = new DateOnlyValue(2012, 12, 31);

		log.debug("source : {}", source);

		final int encoded = ProtoUtil.intoBinaryDateOnly(source);

		final DateOnlyValue target = ProtoUtil.fromBinaryDateOnly(encoded);

		log.debug("target : {}", target);

		assertEquals(source, target);

	}

	@Test
	public void testDateOnlySize() {

		final int fixDate = 20121231;

		final int fixBytes = ProtoUtil.countBytes(fixDate);

		log.debug("fixDate : {}", fixDate);
		log.debug("fixDate bytes : {}", fixBytes);

		assertEquals(fixBytes, 4);

		final int bufDate = ProtoUtil.intoBinaryDateOnly(new DateOnlyValue(
				2012, 12, 31));

		final int bufBytes = ProtoUtil.countBytes(bufDate);

		log.debug("bufDate : {}", bufDate);
		log.debug("bufDate bytes : {}", bufBytes);

		assertEquals(bufBytes, 3);

	}

	@Test
	public void testDateTimeSize() {

		final long fixDate = 20121231235959123L;

		final int fixBytes = ProtoUtil.countBytes(fixDate);

		log.debug("fixDate : {}", fixDate);
		log.debug("fixDate bytes : {}", fixBytes);

		assertEquals(fixBytes, 7);

		//

		final long bufDate = ProtoUtil.intoBinaryDateTime(new DateTimeValue(
				2012, 12, 31, 23, 59, 59, 123));

		final int bufBytes = ProtoUtil.countBytes(bufDate);

		log.debug("bufDate : {}", bufDate);
		log.debug("bufDate bytes : {}", bufBytes);

		assertEquals(bufBytes, 6);

		//

		final long utcDate = System.currentTimeMillis();
		final int utcBytes = ProtoUtil.countBytes(utcDate);

		log.debug("utcDate : {}", utcDate);
		log.debug("utcBytes bytes : {}", utcBytes);

		assertEquals(utcBytes, 6);

	}

}
