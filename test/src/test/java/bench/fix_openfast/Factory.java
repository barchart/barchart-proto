/**
 * Copyright (C) 2011-2013 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package bench.fix_openfast;

import java.util.concurrent.atomic.AtomicLong;

import org.openfast.GroupValue;
import org.openfast.Message;
import org.openfast.SequenceValue;
import org.openfast.template.Field;
import org.openfast.template.MessageTemplate;
import org.openfast.template.Sequence;
import org.openfast.template.TemplateRegistry;

import com.barchart.proto.buf.MarketDataEntry;

class Factory {

	static final int ENTRY_COUNT = 20;

	static final long A_LONG = Long.MAX_VALUE - 3;
	static final int AN_INT = Integer.MAX_VALUE - 3;

	static final long RANGE = 1234567890;
	static final AtomicLong RANDOM = new AtomicLong(RANGE);

	static int getIntMod(final int size) {
		return (int) RANDOM.getAndIncrement() % size;
	}

	static long getLongMod(final long size) {
		return RANDOM.getAndIncrement() % size;
	}

	static final MarketDataEntry.Descriptor[] DESC_VALS = MarketDataEntry.Descriptor
			.values();

	static MarketDataEntry.Descriptor getDescriptorMod(final int size) {
		final int index = getIntMod(size) % DESC_VALS.length;
		return DESC_VALS[index];
	}

	final TemplateRegistry registry;

	Factory(final TemplateRegistry registry) {
		this.registry = registry;
	}

	public enum Mode {

		UPDATE_SIMPLE, //
		UPDATE_COMPLEX, //

		SNAPSHOT_SIMPLE, //
		SNAPSHOT_COMPLEX, //

		//

		SNAPSHOT_SIMPLE_DESC, //
		UPDATE_SIMPLE_DESC, //

	}

	public Field newField() {

		return null;

	}

	public SequenceValue newSequence(final Mode mode,
			final MessageTemplate template) {

		switch (mode) {

		case SNAPSHOT_SIMPLE: {

			final Sequence sequence = template.getSequence("MDEntries");

			final SequenceValue sequenceValue = new SequenceValue(sequence);

			for (int k = 0; k < ENTRY_COUNT; k++) {

				final GroupValue entry = new GroupValue(sequence.getGroup());

				// entry.setInteger("SecurityID", getIntMod(100 * 1000));

				entry.setString("MDEntryType", "0");
				entry.setDecimal("MDEntryPx", getIntMod(100 * 1000));
				entry.setInteger("MDEntrySize", getIntMod(1 * 1000));

				sequenceValue.add(entry);

			}

			return sequenceValue;

		}

		case SNAPSHOT_SIMPLE_DESC: {

			return null;

		}

		case SNAPSHOT_COMPLEX: {

			return null;

		}

		case UPDATE_SIMPLE: {

			return null;

		}

		case UPDATE_SIMPLE_DESC: {

			return null;

		}

		case UPDATE_COMPLEX: {

			return null;

		}

		}

		return null;

	}

	public Message newMessage(final Mode mode) {

		final String templateId;

		switch (mode) {
		case SNAPSHOT_SIMPLE:
			templateId = "MDSnapshotFullRefresh"; //
			break;
		case UPDATE_SIMPLE:
			templateId = "MarketDataIncrementalRefresh"; //
			break;
		default:
			templateId = "";
			break;
		}

		final MessageTemplate template = registry.get(templateId);

		switch (mode) {

		case SNAPSHOT_SIMPLE_DESC:
		case SNAPSHOT_SIMPLE: {

			final Message message = new Message(template);

			message.setInteger("SecurityID", getIntMod(100 * 1000));

			message.setInteger("MsgSeqNum", getIntMod(100 * 1000));

			message.setLong("SendingTime", getLongMod(1000 * 1000));

			message.setFieldValue("MDEntries", newSequence(mode, template));

			return message;

		}

		case SNAPSHOT_COMPLEX: {

			return null;

		}

		case UPDATE_SIMPLE_DESC:
		case UPDATE_SIMPLE: {

			return null;

		}

		case UPDATE_COMPLEX: {

			return null;

		}

		}

		return null;

	}

}
