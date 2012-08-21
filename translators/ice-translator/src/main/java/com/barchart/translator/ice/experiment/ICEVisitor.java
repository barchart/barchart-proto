package com.barchart.translator.ice.experiment;

import com.barchart.translator.ice.experiment.common.FieldVisitor;

public interface ICEVisitor extends FieldVisitor {

	public void visitSessionNumber(short value);

	public void visitSequenceNumber(int value);

	public void visitMessageType(char value);

}
