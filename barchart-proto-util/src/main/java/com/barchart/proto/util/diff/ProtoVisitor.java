package com.barchart.proto.util.diff;

import com.google.protobuf.Descriptors.FieldDescriptor;

public interface ProtoVisitor {

	public void visitField(FieldDescriptor descriptor, Object value);
	
}
