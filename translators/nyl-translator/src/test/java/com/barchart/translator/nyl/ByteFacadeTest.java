package com.barchart.translator.nyl;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.junit.Test;

import com.barchart.translator.nyl.jform.facade.ByteFacade;

public class ByteFacadeTest {

	@Test
	public void testByte() throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		dos.write((byte) 16);
		byte[] array = baos.toByteArray();
		ByteFacade facade = new ByteFacade(array);
		int expected = 16;
		int actual = facade.unsignedByte(0);
		assertEquals(expected, actual);
	}

	@Test
	public void testByte2() throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		dos.write((byte) 250  );
		byte[] array = baos.toByteArray();
		ByteFacade facade = new ByteFacade(array);
		int expected = 250;
		int actual = facade.unsignedByte(0);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testCasting() throws IOException {
		byte b = (byte) 250;
		assertEquals(-6, b);
	}
	
}
