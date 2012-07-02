package com.barchart.proto.buf;

/**
 * TODO more types
 * 
 * semantics : apply( source message to message consumer )
 * 
 * target could be a market state or a news consumer;
 * 
 */
public interface MessageVisitor<TARGET> {

	void apply(MarketData message, TARGET target);

	void apply(MarketNews message, TARGET target);

	//

	class Adaptor<TARGET> implements MessageVisitor<TARGET> {

		@Override
		public void apply(final MarketData message, final TARGET target) {
		}

		@Override
		public void apply(final MarketNews message, final TARGET target) {
		}

		//

	}

}
