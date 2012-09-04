package com.barchart.proto.util.diff;

import java.util.Map;

import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Message;

public class ProtoInspect {

	private Message message;

	public ProtoInspect(Message message) {
		this.message = message;
	}
	
	public void accept(ProtoVisitor visitor) {
		Map<FieldDescriptor, Object> allFields = message.getAllFields();
		for (Map.Entry<FieldDescriptor, Object> entry : allFields.entrySet()) {
			
		}
	}
	
}
