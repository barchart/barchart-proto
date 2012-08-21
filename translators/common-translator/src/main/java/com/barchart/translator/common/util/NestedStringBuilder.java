package com.barchart.translator.common.util;

public class NestedStringBuilder {

	private int indent;

	private StringBuilder builder;
	
	public NestedStringBuilder() {
		this.builder = new StringBuilder();
		this.indent = 0;
	}
	
	public NestedStringBuilder appendLine(String s) {
		for (int i = 0; i < indent; i++) {
			builder.append("\t");
		}
		builder.append(s);
		builder.append("\n");
		return this;
	}
	
	public void indent() {
		indent++;
	}
	
	public void dedent() {
		indent--;
	}
	
	public String build() {
		return builder.toString();
	}
}
