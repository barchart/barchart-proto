package com.barchart.proto.buf;

/**
 * 
 * semantics : apply( source message to message consumer )
 * 
 * target could be a market state or a news consumer, etc;
 * 
 */
public interface MessageVisitor<TARGET> {

	void apply(MarketData message, TARGET target);

	void apply(MarketNews message, TARGET target);

	void apply(DataSubscribeRequest message, TARGET target);

	void apply(DataSubscribeResponse message, TARGET target);

	void apply(NewsSubscribeRequest message, TARGET target);

	void apply(NewsSubscribeResponse message, TARGET target);

	void apply(InstrumentRequest message, TARGET target);

	void apply(InstrumentResponse message, TARGET target);

	void apply(LoginRequest message, TARGET target);

	void apply(LoginResponse message, TARGET target);

	void apply(HeartBeat message, TARGET target);

	/** base class for message visitor implementations */
	class Adaptor<TARGET> implements MessageVisitor<TARGET> {

		@Override
		public void apply(final MarketData message, final TARGET target) {
		}

		@Override
		public void apply(final MarketNews message, final TARGET target) {
		}

		@Override
		public void apply(final DataSubscribeRequest message,
				final TARGET target) {
		}

		@Override
		public void apply(final DataSubscribeResponse message,
				final TARGET target) {
		}

		@Override
		public void apply(final NewsSubscribeRequest message,
				final TARGET target) {
		}

		@Override
		public void apply(final NewsSubscribeResponse message,
				final TARGET target) {
		}

		@Override
		public void apply(final InstrumentRequest message, final TARGET target) {
		}

		@Override
		public void apply(final InstrumentResponse message, final TARGET target) {
		}

		@Override
		public void apply(final LoginRequest message, final TARGET target) {
		}

		@Override
		public void apply(final LoginResponse message, final TARGET target) {
		}

		@Override
		public void apply(final HeartBeat message, final TARGET target) {
		}

	}

}
