package com.barchart.translator.common.exception;

public class TranslatorException extends RuntimeException {

	private static final long serialVersionUID = 589618102571737272L;

	public TranslatorException() {
		super();
	}

	public TranslatorException(String message, Throwable cause) {
		super(message, cause);
	}

	public TranslatorException(String message) {
		super(message);
	}

	public TranslatorException(Throwable cause) {
		super(cause);
	}
	
	

}
