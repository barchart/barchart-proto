package com.barchart.translator.nyl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;

import com.barchart.translator.common.exception.TranslatorException;

public class Replayer {

	private ObjectInputStream ois;

	public Replayer(InputStream is) throws IOException {
		this.ois = new ObjectInputStream(is);
	}
	
	public void close() throws IOException {
		ois.close();
	}
	
	public byte[] nextPayload() throws IOException, ClassNotFoundException {
		if (ois.available() == 0) {
			return null;
		}
		ois.readLong();
		int length = ois.readInt();
		byte[] array = new byte[length];
		ois.readFully(array);
		ois.readObject();
		return array;
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		File file = new File("/home/joel/buf/nyl-metals-l2-a.packets");
		Replayer replayer = new Replayer(new FileInputStream(file));
		
		byte[] array;
		
		NYLTranslatorXForm nylTranslator = new NYLTranslatorXForm(13);
		int good = 0;
		int bad = 0;
		while ( (array= replayer.nextPayload()) != null) {
			try {
				nylTranslator.translate(ByteBuffer.wrap(array));
				good++;
			} catch (TranslatorException e) {
				bad++;
				System.out.println(bad + "\t" + e.getCause().getMessage());
//				e.printStackTrace();
//				doItForever(nylTranslator, array);
				
			}
			
		}
		System.out.println("Good: " + good + ", bad: " + bad);
	}
	
	private static void doItForever(NYLTranslatorXForm nylTranslator, byte[] array) {
		while (true) {
			try {
				nylTranslator.translate(ByteBuffer.wrap(array));
			} catch (Exception ex) {
				ex.printStackTrace();		
			}
		}		
	}

	
}
