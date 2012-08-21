package com.barchart.translator.cme.messaging;

import java.util.List;

import com.barchart.translator.common.fix.FIXMessage;

public final class CMEMessage {

	private final FIXMessage header;

	private final FIXMessage body;

	private final List<FIXMessage> repeatingGroups;

	public CMEMessage(FIXMessage header, FIXMessage body, List<FIXMessage> repeatingGroups) {
		this.header = header;
		this.body = body;
		this.repeatingGroups = repeatingGroups;
	}

	public FIXMessage getHeader() {
		return header;
	}

	public FIXMessage getBody() {
		return body;
	}

	public List<FIXMessage> getRepeatingGroups() {
		return repeatingGroups;
	}
	
	

}
