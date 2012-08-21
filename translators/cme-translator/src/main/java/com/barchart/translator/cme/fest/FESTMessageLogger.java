package com.barchart.translator.cme.fest;

import com.barchart.feed.fix.enums.FixTag;
import com.barchart.translator.common.util.NestedStringBuilder;
import com.cme.fest.framework.messaging.Message;
import com.cme.fest.framework.messaging.MessageBlock;
import com.cme.fest.framework.messaging.MessageField;
import com.cme.fest.framework.messaging.MessageGroup;

public class FESTMessageLogger {

	public static void log(Message cmeMessage) {
		try {
			System.out.println(new NestedLogger(cmeMessage).log());
		} catch (NullPointerException ex) {
			ex.printStackTrace();
		}
		System.out.println("\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");

	}

	private static class NestedLogger {
		private NestedStringBuilder nestedStringBuilder;
		private Message message;

		NestedLogger(Message message) {
			this.nestedStringBuilder = new NestedStringBuilder();
			this.message = message;
		}

		public String log() {
			logBlock(message);
			return nestedStringBuilder.build();
		}

		private void logBlock(MessageBlock block) {
			MessageField[] messageFields = block.getMessageFields();
			MessageGroup[] messageGroups = block.getMessageGroups();
			logFields(messageFields);
			logGroups(messageGroups);
		}

		private void logFields(MessageField[] messageFields) {
			for (MessageField field : messageFields) {
				int tag = field.getTag();
				FixTag fixTag = FixTag.of(tag);

				nestedStringBuilder.appendLine(fixTag + "(" + tag + ") = " + field.getValue() + " --- " + field.getClass());
			}
		}

		private void logGroups(MessageGroup[] messageGroups) {
			nestedStringBuilder.indent();
			int groupCt = 1;
			for (MessageGroup group : messageGroups) {
				nestedStringBuilder.appendLine("Group: " + groupCt++);
				nestedStringBuilder.indent();
				int blockCt = 1;
				for (MessageBlock block : group.getMessageBlocks()) {
					if (block != null) {
						nestedStringBuilder.appendLine("Block: " + blockCt++);
						nestedStringBuilder.indent();
						logBlock(block);
						MessageField tradeDateField = block.getMessageField(75);
						Object tradeDate = (tradeDateField == null) ? "NULL" : tradeDateField.getValue();
						nestedStringBuilder.appendLine("--------- TradeDate: " + tradeDate);
						nestedStringBuilder.dedent();
					}
				}
				nestedStringBuilder.dedent();
			}
			nestedStringBuilder.dedent();
		}
	}

}
