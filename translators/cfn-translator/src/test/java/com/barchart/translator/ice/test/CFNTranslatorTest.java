package com.barchart.translator.ice.test;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import org.junit.Before;
import org.junit.Test;

import com.barchart.proto.buf.data.MarketPacket;
import com.barchart.proto.xform.cfn.CFN;
import com.barchart.proto.xform.cfn.CodecCFN;
import com.barchart.proto.xform.cfn.ConverterCFN;
import com.barchart.translator.common.Translator;
import com.barchart.translator.ice.CFNTranslator;

public abstract class CFNTranslatorTest {

	static {
		CFN.bind(new CodecCFN());
		CFN.bind(new ConverterCFN());
	}
	
	private final ByteArrayOutputStream bytes;
	private final DataOutputStream out;
	private Translator translator;
	private MarketPacket expectedPacket;
	private MarketPacket actual;

	public CFNTranslatorTest() {
		this.bytes = new ByteArrayOutputStream();
		this.out = new DataOutputStream(bytes);
		this.translator = new CFNTranslator();
		this.expectedPacket = expect();
	}
	
	

	
	protected abstract void given() throws IOException;
	protected abstract MarketPacket expect();
	
	@Before
	public void setupTestCase() throws IOException {
		given();
		ByteBuffer byteBuffer = ByteBuffer.wrap(bytes.toByteArray());
		this.actual = translator.translate(byteBuffer);
		
	}
	
	@Test
	public void testSequenceNumber() {
		assertEquals(expectedPacket.getSequence(), actual.getSequence());
	}

	
	
	
	
}
