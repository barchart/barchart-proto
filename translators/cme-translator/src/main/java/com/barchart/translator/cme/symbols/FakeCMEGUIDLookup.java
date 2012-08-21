package com.barchart.translator.cme.symbols;

public class FakeCMEGUIDLookup implements CMEGUIDLookup {

	@Override
	public Long marketLookup(String securityID) {
		long parseLong = Long.parseLong(securityID);
		return parseLong;
	}

}

