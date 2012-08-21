package com.barchart.translator.common.fix;

import java.math.BigDecimal;
import java.util.Set;

import com.barchart.feed.fix.enums.FixAggressorSide;
import com.barchart.feed.fix.enums.FixBookType;
import com.barchart.feed.fix.enums.FixEntryType;
import com.barchart.feed.fix.enums.FixMatchEventIndicator;
import com.barchart.feed.fix.enums.FixMessageType;
import com.barchart.feed.fix.enums.FixOpenCloseSettleFlag;
import com.barchart.feed.fix.enums.FixQuoteCondition;
import com.barchart.feed.fix.enums.FixTickDirection;
import com.barchart.feed.fix.enums.FixTradeCondition;
import com.barchart.feed.fix.enums.FixUpdateAction;

public interface FIXMessage {

	public Integer getTotNumRports();

	public Integer getRptSequence();

	public FixBookType getMDBookType();

	public Integer getTradeDate();

	public Integer getNoMDEntries();

	public Integer getMDEntrySize();

	public Integer getTradeVolume();

	public FixTickDirection getTickDirection();

	public BigDecimal getNetChgPrevDay();

	public FixUpdateAction getMDUpdateAction();

	public Integer getMDPriceLevel();

	public FixEntryType getMDEntryType();

	public Character getSecurityIDSource();

	public String getSecurityID();

	public Integer getRptSeq();

	public Set<FixQuoteCondition> getQuoteCondition();

	public BigDecimal getMDEntryPx();

	public Integer getNumberOfOrders();

	public Long getMDEntryTime();

	public String getTradingSessionID();

	public FixOpenCloseSettleFlag getOpenCloseSettleFlag();

	public String getSettlDate();

	public FixTradeCondition getTradeCondition();

	public Integer getMDQuoteType();

	public String getFixingBracket();

	public FixAggressorSide getAggressorSide();

	public FixMatchEventIndicator getMatchEventIndicator();

	public Long getMsgSeqNum();

	public FixMessageType getMsgType();

	public String getSenderCompID();

	public Long getSendingTime();

	public String getApplVerID();

	public Long getLastMsgSeqNumProcessed();
	
	public Integer getMDSecurityTradingStatus();
	
	public Boolean isPossibleDuplicate();
	
}
