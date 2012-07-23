package com.barchart.proto.xform.cfn;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barchart.proto.xform.cfn.CFN.Body;
import com.barchart.proto.xform.cfn.CFN.Body.Type;
import com.barchart.proto.xform.cfn.CFN.BodyFutureBookTop;
import com.barchart.proto.xform.cfn.CFN.ExpirationMonth;
import com.barchart.proto.xform.cfn.CFN.ExpirationYear;
import com.barchart.proto.xform.cfn.CFN.Head;
import com.barchart.proto.xform.cfn.CFN.InstFuture;
import com.barchart.proto.xform.cfn.CFN.Message;
import com.barchart.proto.xform.cfn.CFN.MessageCategory;
import com.barchart.proto.xform.cfn.CFN.MessageType;
import com.barchart.proto.xform.cfn.CFN.Packet;
import com.barchart.proto.xform.cfn.CFN.PriceExponent;
import com.barchart.proto.xform.cfn.CFN.SessionIndicator;

public class MainNEW {

	final static Logger log = LoggerFactory.getLogger(MainNEW.class);

	public static void main(final String[] args) throws Exception {

		final Packet packet = new Packet();

		final List<Message> messageList = new ArrayList<Message>();

		{

			final Head head = new Head();
			head.categoryEnum(MessageCategory.TRADE_F);
			head.typeEnum(MessageType.REGULAR);

			final BodyFutureBookTop value = new BodyFutureBookTop();
			final InstFuture inst = new InstFuture();
			inst.symbol("MSFT");
			inst.monthEnum(ExpirationMonth.AUG);
			inst.yearEnum(ExpirationYear.YEAR_2);
			value.instrument(inst);
			value.priceExponentEnum(PriceExponent.NEG_02);
			value.sessionIdicatorEnum(SessionIndicator.NORMAL);

			final Body body = new Body();
			body.type(Type.F_BT_3);
			body.value(value);

			final Message message = new Message();
			message.body(body);
			message.head(head);
			messageList.add(message);
		}

		{
			final Head head = new Head();
			head.categoryEnum(MessageCategory.TRADE_O);
			head.typeEnum(MessageType.REGULAR);

			final Body body = new Body();
			body.type(Type.F_BT_4);
			final BodyFutureBookTop value = (BodyFutureBookTop) Type.F_BT_4
					.newInstance();
			body.value(value);
			value.priceExponentEnum(PriceExponent.NEG_03);
			value.sessionIdicatorEnum(SessionIndicator.MORINING);

			final Message message = new Message();
			message.body(body);
			message.head(head);
			messageList.add(message);
		}

		packet.messageList(messageList);

		log.debug("packet : {}", packet);

	}

}
