package com.barchart.translator.nyl.jform.visit;

import com.barchart.translator.nyl.jform.visit.NYLField.DeliveryFlag;
import com.barchart.translator.nyl.jform.visit.NYLField.Filler;
import com.barchart.translator.nyl.jform.visit.NYLField.MsgSize;
import com.barchart.translator.nyl.jform.visit.NYLField.MsgType;
import com.barchart.translator.nyl.jform.visit.NYLField.NumberMsgEntries;
import com.barchart.translator.nyl.jform.visit.NYLField.PacketLength;
import com.barchart.translator.nyl.jform.visit.NYLField.PacketSeqNum;
import com.barchart.translator.nyl.jform.visit.NYLField.PacketType;
import com.barchart.translator.nyl.jform.visit.NYLField.SecurityID;
import com.barchart.translator.nyl.jform.visit.NYLField.SecurityIDSource;
import com.barchart.translator.nyl.jform.visit.NYLField.SendTime;
import com.barchart.translator.nyl.jform.visit.NYLField.SeriesSequenceNumber;
import com.barchart.translator.nyl.jform.visit.NYLField.ServiceID;
import com.barchart.translator.nyl.jform.visit.NYLField.SnapshotFlag;
import com.barchart.translator.nyl.jform.visit.NYLField.SourceTime;
import com.barchart.translator.nyl.jform.visit.NYLField.UpdateCount;

public interface NYLFieldVisitor {
	
	public NYLFieldVisitor visit(PacketLength field);
	
	public NYLFieldVisitor visit(PacketType field);
	
	public NYLFieldVisitor visit(PacketSeqNum field);
	
	public NYLFieldVisitor visit(SendTime field); 
	
	public NYLFieldVisitor visit(ServiceID field);
	
	public NYLFieldVisitor visit(DeliveryFlag field);
	
	public NYLFieldVisitor visit(NumberMsgEntries field);

	public NYLFieldVisitor visit(MsgSize field);

	public NYLFieldVisitor visit(MsgType field);

	public NYLFieldVisitor visit(SourceTime field);

	public NYLFieldVisitor visit(SeriesSequenceNumber field);

	public NYLFieldVisitor visit(SecurityIDSource field);

	public NYLFieldVisitor visit(SecurityID field);

	public NYLFieldVisitor visit(SnapshotFlag field);

	public NYLFieldVisitor visit(Filler field);

	public NYLFieldVisitor visit(UpdateCount field);
	
	
}
