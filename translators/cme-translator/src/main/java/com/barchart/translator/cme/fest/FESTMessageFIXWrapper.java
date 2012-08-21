package com.barchart.translator.cme.fest;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Set;

import com.barchart.feed.fix.enums.FixAggressorSide;
import com.barchart.feed.fix.enums.FixBookType;
import com.barchart.feed.fix.enums.FixEntryType;
import com.barchart.feed.fix.enums.FixMatchEventIndicator;
import com.barchart.feed.fix.enums.FixMessageType;
import com.barchart.feed.fix.enums.FixOpenCloseSettleFlag;
import com.barchart.feed.fix.enums.FixQuoteCondition;
import com.barchart.feed.fix.enums.FixTag;
import com.barchart.feed.fix.enums.FixTickDirection;
import com.barchart.feed.fix.enums.FixTradeCondition;
import com.barchart.feed.fix.enums.FixUpdateAction;
import com.barchart.translator.common.fix.FIXMessage;
import com.cme.fest.framework.messaging.MessageBlock;
import com.cme.fest.framework.messaging.MessageField;
import com.cme.fest.framework.util.ScaledDecimal;

public class FESTMessageFIXWrapper implements FIXMessage {

	private final MessageBlock messageBlock;

	public FESTMessageFIXWrapper(MessageBlock messageBlock) {
		this.messageBlock = messageBlock;
	}

	@Override
	public Integer getNoMDEntries() {
		return getInteger(FixTag.NoMDEntries);
	}

	@Override
	public Integer getTotNumRports() {
		return getInteger(FixTag.TotNumReports);
	}

	@Override
	public Integer getRptSequence() {
		return getInteger(FixTag.RptSeq);
	}

	@Override
	public FixBookType getMDBookType() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getSecurityID() {
		return getString(FixTag.SecurityID);
	}

	@Override
	public Character getSecurityIDSource() {
		return getCharacter(FixTag.SecurityIDSource);
	}

	@Override
	public Integer getTradeDate() {
		return getInteger(FixTag.TradeDate);
	}

	@Override
	public FixUpdateAction getMDUpdateAction() {
		Character code = getCharacter(FixTag.MDUpdateAction);
		return FixUpdateAction.lookup(code);
	}

	@Override
	public Integer getMDPriceLevel() {
		return getInteger(FixTag.MDPriceLevel);
	}

	@Override
	public FixEntryType getMDEntryType() {
		Character code = getCharacter(FixTag.MDEntryType);
		return FixEntryType.lookup(code);
	}

	@Override
	public Integer getRptSeq() {
		return getInteger(FixTag.RptSeq);
	}

	@Override
	public Set<FixQuoteCondition> getQuoteCondition() {
		throw new UnsupportedOperationException();
	}

	@Override
	public BigDecimal getMDEntryPx() {
		return getBigDecimal(FixTag.MDEntryPx);
	}

	@Override
	public Integer getNumberOfOrders() {
		return getInteger(FixTag.NumberOfOrders);
	}

	@Override
	public Long getMDEntryTime() {
		return getLong(FixTag.MDEntryTime);
	}

	@Override
	public Integer getMDEntrySize() {
		return getInteger(FixTag.MDEntrySize);
	}

	@Override
	public String getTradingSessionID() {
		return getString(FixTag.TradingSessionID);
	}

	@Override
	public BigDecimal getNetChgPrevDay() {
		return getBigDecimal(FixTag.NetChgPrevDay);
	}

	@Override
	public FixTickDirection getTickDirection() {
		throw new UnsupportedOperationException();
	}

	@Override
	public FixOpenCloseSettleFlag getOpenCloseSettleFlag() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getSettlDate() {
		return getString(FixTag.SettlDate);
	}

	@Override
	public FixTradeCondition getTradeCondition() {
		String string = getString(FixTag.TradeCondition);
		return FixTradeCondition.valueOf(string);
	}

	@Override
	public Integer getTradeVolume() {
		return getInteger(FixTag.TradeVolume);
	}

	@Override
	public Integer getMDQuoteType() {
		return getInteger(FixTag.MDQuoteType);
	}

	@Override
	public String getFixingBracket() {
		return getString(FixTag.FixingBraket);
	}

	@Override
	public Long getMsgSeqNum() {
		return getLong(FixTag.MsgSeqNum);
	}

	@Override
	public FixMessageType getMsgType() {
		String code = getString(FixTag.MsgType);
		return FixMessageType.lookup(code);
	}

	@Override
	public String getSenderCompID() {
		return getString(FixTag.SenderCompID);
	}

	@Override
	public Long getSendingTime() {
		return getLong(FixTag.SendingTime);
	}

	@Override
	public String getApplVerID() {
		return getString(FixTag.ApplVerID);
	}

	@Override
	public Long getLastMsgSeqNumProcessed() {
		return getLong(FixTag.LastMsgSeqNumProcessed);
	}

	@Override
	public Boolean isPossibleDuplicate() {
		throw new UnsupportedOperationException();
	}

	@Override
	public FixAggressorSide getAggressorSide() {
		throw new UnsupportedOperationException();
	}

	@Override
	public FixMatchEventIndicator getMatchEventIndicator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Integer getMDSecurityTradingStatus() {
		throw new UnsupportedOperationException();
	}

	private Long getLong(FixTag fixTag) {
		MessageField field = getField(fixTag);
		return field == null ? null : field.getAsLong();
	}

	private Integer getInteger(FixTag fixTag) {
		MessageField field = getField(fixTag);
		return field == null ? null : field.getAsInt();
	}

	private String getString(FixTag fixTag) {
		MessageField field = getField(fixTag);
		return field == null ? null : field.getAsString();
	}

	private Character getCharacter(FixTag fixTag) {
		String string = getString(fixTag);
		return (string == null || string.length() == 0) ? null : string.charAt(0);
	}

	private ScaledDecimal getScaledDecimal(FixTag fixTag) {
		MessageField field = getField(fixTag);
		return field == null ? null : field.getAsScaledDecimal();
	}
	
	private BigDecimal getBigDecimal(FixTag fixTag) {
		ScaledDecimal scaledDecimal = getScaledDecimal(fixTag);
		if (scaledDecimal == null) {
			return null;
		} else {
			BigInteger mantissa = BigInteger.valueOf(scaledDecimal.getMantissa());
			return new BigDecimal(mantissa, scaledDecimal.getExponent());
		}
	}

	private MessageField getField(FixTag fixTag) {
		MessageField field = messageBlock.getMessageField(fixTag.code);
		return field;
	}

}
