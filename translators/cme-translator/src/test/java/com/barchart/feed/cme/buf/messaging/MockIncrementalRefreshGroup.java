//package com.barchart.feed.cme.buf.messaging;
//
//import java.util.Set;
//
//import com.barchart.feed.fix.enums.FixAggressorSide;
//import com.barchart.feed.fix.enums.FixEntryType;
//import com.barchart.feed.fix.enums.FixMatchEventIndicator;
//import com.barchart.feed.fix.enums.FixOpenCloseSettleFlag;
//import com.barchart.feed.fix.enums.FixQuoteCondition;
//import com.barchart.feed.fix.enums.FixTickDirection;
//import com.barchart.feed.fix.enums.FixTradeCondition;
//import com.barchart.feed.fix.enums.FixUpdateAction;
//import com.cme.fest.framework.util.ScaledDecimal;
//
//public class MockIncrementalRefreshGroup implements IncrementalRefreshGroup {
//
//	private FixUpdateAction mdUpdateAction;
//	private Integer mdPriceLevel;
//	private FixEntryType mdEntryType;
//	private Character securityIDSource;
//	private String securityID;
//	private Integer rptSeq;
//	private Set<FixQuoteCondition> quoteCondition;
//	private ScaledDecimal mdEntryPx;
//	private Integer numberOfOrders;
//	private Long mdEntryTime;
//	private Integer mdEntrySize;
//	private String tradingSessionID;
//	private ScaledDecimal netChgPrevDay;
//	private FixTickDirection tickDirection;
//	private FixOpenCloseSettleFlag openCloseSettleFlag;
//	private String settlDate;
//	private FixTradeCondition  tradeCondition;
//	private Integer tradeVolume;
//	private Integer mdQuoteType;
//	private String fixingBracket;
//	private FixAggressorSide aggressorSide;
//	private FixMatchEventIndicator matchEventIdicator;
//
//	public FixUpdateAction getMdUpdateAction() {
//		return mdUpdateAction;
//	}
//
//	public void setMdUpdateAction(FixUpdateAction mdUpdateAction) {
//		this.mdUpdateAction = mdUpdateAction;
//	}
//
//	public Integer getMdPriceLevel() {
//		return mdPriceLevel;
//	}
//
//	public void setMdPriceLevel(Integer mdPriceLevel) {
//		this.mdPriceLevel = mdPriceLevel;
//	}
//
//	public FixEntryType getMdEntryType() {
//		return mdEntryType;
//	}
//
//	public void setMdEntryType(FixEntryType mdEntryType) {
//		this.mdEntryType = mdEntryType;
//	}
//
//	public Character getSecurityIDSource() {
//		return securityIDSource;
//	}
//
//	public void setSecurityIDSource(Character securityIDSource) {
//		this.securityIDSource = securityIDSource;
//	}
//
//	public String getSecurityID() {
//		return securityID;
//	}
//
//	public void setSecurityID(String securityID) {
//		this.securityID = securityID;
//	}
//
//	public Integer getRptSeq() {
//		return rptSeq;
//	}
//
//	public void setRptSeq(Integer rptSeq) {
//		this.rptSeq = rptSeq;
//	}
//
//	public Set<FixQuoteCondition> getQuoteCondition() {
//		return quoteCondition;
//	}
//
//	public void setQuoteCondition(Set<FixQuoteCondition> quoteCondition) {
//		this.quoteCondition = quoteCondition;
//	}
//
//	public ScaledDecimal getMdEntryPx() {
//		return mdEntryPx;
//	}
//
//	public void setMdEntryPx(ScaledDecimal mdEntryPx) {
//		this.mdEntryPx = mdEntryPx;
//	}
//
//	public Integer getNumberOfOrders() {
//		return numberOfOrders;
//	}
//
//	public void setNumberOfOrders(Integer numberOfOrders) {
//		this.numberOfOrders = numberOfOrders;
//	}
//
//	public Long getMdEntryTime() {
//		return mdEntryTime;
//	}
//
//	public void setMdEntryTime(Long mdEntryTime) {
//		this.mdEntryTime = mdEntryTime;
//	}
//
//	public Integer getMdEntrySize() {
//		return mdEntrySize;
//	}
//
//	public void setMdEntrySize(Integer mdEntrySize) {
//		this.mdEntrySize = mdEntrySize;
//	}
//
//	public String getTradingSessionID() {
//		return tradingSessionID;
//	}
//
//	public void setTradingSessionID(String tradingSessionID) {
//		this.tradingSessionID = tradingSessionID;
//	}
//
//	public ScaledDecimal getNetChgPrevDay() {
//		return netChgPrevDay;
//	}
//
//	public void setNetChgPrevDay(ScaledDecimal netChgPrevDay) {
//		this.netChgPrevDay = netChgPrevDay;
//	}
//
//	public FixTickDirection getTickDirection() {
//		return tickDirection;
//	}
//
//	public void setTickDirection(FixTickDirection tickDirection) {
//		this.tickDirection = tickDirection;
//	}
//
//	public FixOpenCloseSettleFlag getOpenCloseSettleFlag() {
//		return openCloseSettleFlag;
//	}
//
//	public void setOpenCloseSettleFlag(FixOpenCloseSettleFlag openCloseSettleFlag) {
//		this.openCloseSettleFlag = openCloseSettleFlag;
//	}
//
//	public String getSettlDate() {
//		return settlDate;
//	}
//
//	public void setSettlDate(String settlDate) {
//		this.settlDate = settlDate;
//	}
//
//	public FixTradeCondition getTradeCondition() {
//		return tradeCondition;
//	}
//
//	public void setTradeCondition(String FixTradeCondition ) {
//		this.tradeCondition = tradeCondition;
//	}
//
//	public Integer getTradeVolume() {
//		return tradeVolume;
//	}
//
//	public void setTradeVolume(Integer tradeVolume) {
//		this.tradeVolume = tradeVolume;
//	}
//
//	public Integer getMdQuoteType() {
//		return mdQuoteType;
//	}
//
//	public void setMdQuoteType(Integer mdQuoteType) {
//		this.mdQuoteType = mdQuoteType;
//	}
//
//	public String getFixingBracket() {
//		return fixingBracket;
//	}
//
//	public void setFixingBracket(String fixingBracket) {
//		this.fixingBracket = fixingBracket;
//	}
//
//	public FixAggressorSide getAggressorSide() {
//		return aggressorSide;
//	}
//
//	public void setAggressorSide(FixAggressorSide aggressorSide) {
//		this.aggressorSide = aggressorSide;
//	}
//
//	public FixMatchEventIndicator getMatchEventIdicator() {
//		return matchEventIdicator;
//	}
//
//	public void setMatchEventIdicator(FixMatchEventIndicator matchEventIdicator) {
//		this.matchEventIdicator = matchEventIdicator;
//	}
//
//	@Override
//	public FixUpdateAction getMDUpdateAction() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Integer getMDPriceLevel() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public FixEntryType getMDEntryType() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public ScaledDecimal getMDEntryPx() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Long getMDEntryTime() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Integer getMDEntrySize() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Integer getMDQuoteType() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public FixMatchEventIndicator getMatchEventIndicator() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//}
