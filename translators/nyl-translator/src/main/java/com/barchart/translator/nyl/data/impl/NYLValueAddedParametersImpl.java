package com.barchart.translator.nyl.data.impl;

import com.barchart.translator.common.data.ByteFacade;
import com.barchart.translator.common.data.OffsetByteReader;
import com.barchart.translator.nyl.data.NYLValueAddedParameters;
import com.barchart.translator.nyl.data.enums.NYLMessageType;

public class NYLValueAddedParametersImpl extends OffsetByteReader implements NYLValueAddedParameters {

	public NYLValueAddedParametersImpl(int baseOffset, ByteFacade bytes) {
		super(baseOffset, bytes);
	}

	@Override
	public int getMsgSize() {
		return bytes.unsignedShort(offset(0));
	}

	@Override
	public NYLMessageType getMsgType() {
		int code = bytes.unsignedShort(offset(2));
		return NYLMessageType.fromCode(code);
	}

	@Override
	public long getSourceTime() {
		return bytes.unsignedInt(offset(4));
	}

	@Override
	public int getSecurityIDSource() {
		return bytes.unsignedByte(offset(8));
	}

	@Override
	public String getSecurityID() {
		return bytes.string(offset(9), 15);
	}

	@Override
	public int getUpdateCount() {
		return bytes.unsignedShort(offset(24));
	}
	
	public static class EntryImpl extends OffsetByteReader implements NYLValueAddedParameters.Entry {

		public EntryImpl(int baseOffset, ByteFacade bytes) {
			super(baseOffset, bytes);
		}

		@Override
		public UpdateType getUpdateType() {
			int code = bytes.unsignedShort(0);
			return UpdateType.fromCode(code);
		}

		@Override
		public long getPrice() {
			return bytes.unsignedInt(32);
		}

		@Override
		public long getVolume() {
			return bytes.unsignedInt(36);
		}
		
	}

}
