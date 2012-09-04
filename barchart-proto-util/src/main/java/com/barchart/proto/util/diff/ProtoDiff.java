package com.barchart.proto.util.diff;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Message;

public class ProtoDiff {

	private static final Logger logger = LoggerFactory.getLogger(ProtoDiff.class);

	private final Message expected;
	private final Message actual;
	private final List<Difference> differences;
	private final Descriptor descriptor;

	public ProtoDiff(Message expected, Message actual) {
		this.expected = expected;
		this.actual = actual;
		this.descriptor = expected.getDescriptorForType();
		this.differences = new ArrayList<Difference>();
		
		if (checkSameClass(expected, actual)) {
			diff();	
		} 
		
	}

	public void diff() {

		List<FieldDescriptor> fields = descriptor.getFields();
		for (FieldDescriptor fieldDescriptor : fields) {
			diffField(fieldDescriptor);
		}
	}

	private void diffRepeated(FieldDescriptor fieldDescriptor) {
	}

	private void diffField(FieldDescriptor fieldDescriptor) {
		switch (fieldDescriptor.getJavaType()) {
		case BOOLEAN:
		case BYTE_STRING:
		case DOUBLE:
		case ENUM:
		case FLOAT:
		case INT:
		case LONG:
		case STRING:
			diffObject(fieldDescriptor);
			break;
		case MESSAGE:
			diffMessage(fieldDescriptor);
			break;
		}
	}

	@SuppressWarnings("unchecked")
	private void diffMessage(FieldDescriptor fieldDescriptor) {
		if (fieldDescriptor.isRepeated()) {
			Collection<Message> expectedMessages = (Collection<Message>) expected.getField(fieldDescriptor);
			Collection<Message> actualMessages = (Collection<Message>) actual.getField(fieldDescriptor);
			
			
			
			
		} else {
			throw new UnsupportedOperationException();
		}
	}

	private void diffObject(FieldDescriptor fieldDescriptor) {
		Object value1 = expected.getField(fieldDescriptor);
		Object value2 = actual.getField(fieldDescriptor);
		if (!nullSafeEquals(value1, value2)) {
			differences.add(new FieldDifference(fieldDescriptor, value1, value2));
		}
	}

	private boolean checkSameClass(Message expected, Message actual) {
		if (!expected.getClass().equals(actual.getClass())) {
			differences.add(new ClassDifference(expected.getClass(), actual.getClass()));
			return false;
		} else {
			return true;
		}
		
	}



	private boolean nullSafeEquals(Object o1, Object o2) {
		if (o1 == null) {
			return o2 == null;
		} else {
			return o1.equals(o2);
		}
	}

	public List<Difference> getDifferences() {
		return differences;
	}
	
	
	public static class Difference {
	
		private final Object expected;
		
		private final Object actual;

		public Difference(Object expected, Object actual) {
			this.expected = expected;
			this.actual = actual;
		}
		
		public String toString() {
			return "Expected: " + expected + ", actual: " + actual;
		}

		public Object getExpected() {
			return expected;
		}

		public Object getActual() {
			return actual;
		}
		
		
		
		
	}
	
	public static class ClassDifference extends Difference {

		public ClassDifference(Object expected, Object actual) {
			super(expected, actual);
		}
		
		public String toString() {
			return "Class difference. - " + super.toString();
		}
	}
	
	public static class FieldDifference extends Difference {
		private final FieldDescriptor fieldDescriptor;

		public FieldDifference(FieldDescriptor fieldDescriptor, Object expected, Object actual) {
			super(expected, actual);
			this.fieldDescriptor = fieldDescriptor;
		}

		public String toString() {
			return fieldDescriptor.getFullName() + " - " + super.toString(); 
		}
	}

	public boolean hasDifferences() {
		return !differences.isEmpty();
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (Difference difference : differences) {
			builder.append(difference.toString());
			builder.append("\n");
		}
		return builder.toString();
	}

	public int getDifferenceCount() {
		return differences.size();
	}
	
}
