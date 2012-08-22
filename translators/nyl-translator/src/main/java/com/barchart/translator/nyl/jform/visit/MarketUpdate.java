package com.barchart.translator.nyl.jform.visit;

import com.barchart.translator.nyl.jform.visit.NYLField.Filler;
import com.barchart.translator.nyl.jform.visit.NYLField.MsgSize;
import com.barchart.translator.nyl.jform.visit.NYLField.MsgType;
import com.barchart.translator.nyl.jform.visit.NYLField.SecurityID;
import com.barchart.translator.nyl.jform.visit.NYLField.SecurityIDSource;
import com.barchart.translator.nyl.jform.visit.NYLField.SeriesSequenceNumber;
import com.barchart.translator.nyl.jform.visit.NYLField.SnapshotFlag;
import com.barchart.translator.nyl.jform.visit.NYLField.SourceTime;
import com.barchart.translator.nyl.jform.visit.NYLField.UpdateCount;
import com.google.common.collect.ImmutableCollection.Builder;

public class MarketUpdate extends NYLFieldBlock {

	@Override
	public void populateFields(Builder<NYLField> fields) {
		fields.add(new MsgSize());
		fields.add(new MsgType());
		fields.add(new SourceTime());
		fields.add(new SeriesSequenceNumber());
		fields.add(new SecurityIDSource());
		fields.add(new SecurityID());
		fields.add(new SnapshotFlag());
		fields.add(new Filler(1));
		fields.add(new UpdateCount());
		
	}

}
