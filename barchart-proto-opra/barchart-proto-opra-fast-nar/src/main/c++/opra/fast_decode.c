/*****************************************************************************
*  System        : OPRA
*
*  Module Name	 : fast_decode.c
*  Copyright (c) : SIAC 2007
*  Date          : 3/2007
*
*  Description	 : Function definitions to decode various fields of the 
*                  encoded opra message.
******************************************************************************/

#include "fast_api.h"
#include "opra_fast.h"
#include "fast_wrapper.h"

/////////////////////////////////////////////////////////////////////////////
//Note:1.Following field are introduced in the common header and 
//       some of the category messages in ver 2. 
//
//       explicit price  in version 2 are changed.
//       sequence Number  - 10 bytes (earler 8 bytes ) - Common Header
//       time             -  9 bytes (earler 6 bytes ) - Common Header
//       expiration month -  1 byte  ( new field added)- a,k,d,f 
//       expiration date  -  2 bytes ( earlier 1 byte )- a,k,d,f,O,U,F  
//       expiration year  -  2 bytes ( earlier 1 byte )- a,k,d,f 
//       expl strike price-  6 bytes ( earlier 7 bytes)- a,k,d,f 
//
//     2.Following messages are affected by the change in the template in ver 2.
//       a,k,d,f,O,U,F,Y,def, 
//
//     3.Following fields are removed from version 2
//       reserved field1  
//       reserved field2  
//       reserved field3  
//
//     4.Following functions are defined to support decoding version 2 msgs
//		 01.int decode_OpraFastDefMsg_v2(..);
//		 02.int decode_OpraFastQuoteSizeMsg_v2(..)
//       03.int decode_OpraFastOpenIntMsg_v2(..)
//       04.int decode_OpraFastLastSaleMsg_v2(..)
//	     05.int decode_OpraFastEodMsg_v2(..)
//       06.int decode_OpraFastFcoLastSaleMsg_v2(..)
//       07.int decode_OpraFastFcoQuoteMsg_v2(..)
//	     08.int decode_OpraFastFcoEodMsg_v2(..);
//       09.int decode_OpraFastUlValueMsg_v2(..);
//       10.int decode_OpraFastAdminMsg_v2(..);
//       11.int decode_OpraFastControlMsg_v2(..);
//
////////////////////////////////////////////////////////////////////////////////


////////////////////////////////////////////////////////////////////////////////
//
//  void msg_decode_hdr(Fast* fast, OpraMsgHdr* hdr)
//
//  Decodes opra header  
//
//  hdr->sequenceNumber - 08 bytes
//  
//  hdr->time           - 06 bytes [ milli seconds exluded]
//
////////////////////////////////////////////////////////////////////////////////


////////////////////////////////////////////////////////////////////////////////
//
//  Decodes opra header  for v2 messages
//
//  void msg_decode_hdr(Fast* fast, OpraMsgHdr_v2* hdr)
//
//  
//  hdr->sequenceNumber - 10 bytes
//  
//  hdr->time           - 09 bytes [ milli secconds included ]
//
////////////////////////////////////////////////////////////////////////////////

void msg_decode_hdr(Fast* fast, OpraMsgHdr_v2* hdr)
{
    // msg category already decoded
    hdr->type          = fast->decode_u32(fast, MESSAGE_TYPE_V2);

    // decode participant Id.
    hdr->participantId = fast->decode_u32(fast, PARTICIPANT_ID_V2);
 
    // decode retransmission requester.
    hdr->retran        = 
        fast->decode_u32(fast, RETRANSMISSION_REQUESTER_V2);

    // decode sequence Number
    u64_to_ascii(hdr->seqNumber, sizeof(hdr->seqNumber),
                 fast->decode_u64(fast, MESSAGE_SEQUENCE_NUMBER_V2));

    // decode time
    u32_to_ascii(hdr->time, sizeof(hdr->time), 
                 fast->decode_u32(fast, TIME_V2));
}

////////////////////////////////////////////////////////////////////////////////
//
//  unsigned char* u32_to_ascii (unsigned char* data, size_t size, u32 value)
//
//  converts unsigned int to string.
//
////////////////////////////////////////////////////////////////////////////////

unsigned char* u32_to_ascii (unsigned char* data, size_t size, u32 value)
{
	int p1   = 0;
    u32 temp = 0;

    // for each digit in given number find its ascii value
	for (p1 = size - 1; p1 >= 0 ; p1 --)
	{
		temp = value / 10;
		data [p1] = '0' + value - temp * 10;
		value = temp;
	}
	return data;
}

////////////////////////////////////////////////////////////////////////////////
//
//  unsigned char* u64_to_ascii (unsigned char* data, size_t size, u32 value)
//
//  converts unsigned int to string.
//
////////////////////////////////////////////////////////////////////////////////

unsigned char* u64_to_ascii (unsigned char* data, size_t size, u64 value)
{
    int p1   = 0;
    u32 temp = 0;

    // for each digit in given number find its ascii value
    for (p1 = size - 1; p1 >= 0 ; p1 --)
    {
        temp = value / 10;
        data [p1] = '0' + value - temp * 10;
        value = temp; 
    }
    return data;
}


////////////////////////////////////////////////////////////////////////////////
//
//	decode for V2
//
//  int decode_OpraFastDefMsg(Fast* fast, char *buf, CatDefMsg* msg)
//
//  decodes fast opra default message. 
//
////////////////////////////////////////////////////////////////////////////////

int 
decode_OpraFastDefMsg_v2(Fast* fast, char *buf, CatDefMsg* msg)
{
    char us = 0, endByte = 0;
    int len = 0;

    /////  Category 'def' Version 2 - Decode (start) /////

    //decode msg body
    memset(msg->body, 0, sizeof(msg->body));


    //decode default message of version 2
    len = 
      fast->decode_str(fast, DEF_MSG_V2, msg->body, sizeof(msg->body));

    /////  Category 'def' Version 2 - Decode ( end ) /////

    //copy the msg to buf
    memcpy(buf, (char *)msg->body, len);
    return len;
}



////////////////////////////////////////////////////////////////////////////////
//
//	VER 2
//
//  int decode_OpraFastQuoteSizeMsg(Fast* fast, char *buf, CatkMsg_v2* msg)
//
//  decode category k message 
//
////////////////////////////////////////////////////////////////////////////////

int 
decode_OpraFastQuoteSizeMsg_v2(Fast* fast, char *buf, CatkMsg_v2* msg)
{
    char us = 0, endByte = 0;
	unsigned int length = 0;

	memset(msg, 0, sizeof(CatkMsg_v2));

	// calculate length until bbo appendage
	length = offsetof(CatkMsg_v2, bbo);
    
	//decode header
	msg->hdr.category = 'k'; // 'k' for equity and index quote with size

    /////  Category 'k' Version 2 - Decode (start) /////

    //decode message header.
	msg_decode_hdr(fast, &msg->hdr);


	//decode symbol option
	memset(msg->symbol,' ',sizeof(msg->symbol)); 
	fast->decode_str(fast, SECURITY_SYMBOL_V2, 
                     	msg->symbol, sizeof(msg->symbol));
       
    //decode expiration month - 1 byte
    msg->expirationMonth = fast->decode_u32(fast, EXPIRATION_MONTH_V2);

    //decode expiration Date - 2 bytes 
    u32_to_ascii(msg->expirationDate,sizeof(msg->expirationDate),
					fast->decode_u32(fast, EXPIRATION_DATE_V2)); 

    //decode expiration Year - 2 bytes 
	u32_to_ascii(msg->year,sizeof(msg->year), 
					fast->decode_u32(fast, YEAR_V2));

    //decode strike price denom code.
	msg->strikePriceDenomCode = fast->decode_u32(fast, 
									STRIKE_PRICE_DENOMINATOR_CODE_V2);

    //decode explicit strike .
	u32_to_ascii(msg->explicitStrike, sizeof(msg->explicitStrike), 
					fast->decode_u32(fast, EXPLICIT_STRIKE_PRICE_V2)); 

    //decode strike price code.
	msg->strikePriceCode = fast->decode_u32(fast, STRIKE_PRICE_CODE_V2);

    //decode premium price denom code .
	msg->premiumPriceDenomCode = 
		fast->decode_u32(fast, PREMIUM_PRICE_DENOMINATOR_CODE_V2);

    //decode bid price 
	u32_to_ascii(msg->bidQuote, sizeof(msg->bidQuote), 
					fast->decode_u32(fast, BID_PRICE_V2));

    //decode bid size 
	u32_to_ascii(msg->bidSize, sizeof(msg->bidSize), 
					fast->decode_u32(fast, BID_SIZE_V2)); 

    //decode offer price 
	u32_to_ascii(msg->askQuote, sizeof(msg->askQuote), 
					fast->decode_u32(fast, OFFER_PRICE_V2)); 

    //decode offer size 
	u32_to_ascii(msg->askSize, sizeof(msg->askSize), 
					fast->decode_u32(fast, OFFER_SIZE_V2)); 

    //decode session indicator 
	msg->sessionIndicator =
        fast->decode_u32(fast, SESSION_INDICATOR_V2);

    //decode bbo  indicator 
	msg->bboIndicator = fast->decode_u32(fast, BBO_INDICATOR_V2);

	switch(msg->bboIndicator) 
    {
		case 'A': // No Best Bid Change, No Best Offer Change
		case 'B': // No Best Bid Change, Quote Contains Best Offer
		case 'D': // No Best Bid Change, No Best Offer
		case 'E': // Quote Contains Best Bid, No Best Offer Change
		case 'F': // Quote Contains Best Bid, Quote Contains Best Offer
		case 'H': // Quote Contains Best Bid, No Best Offer 
		case 'I': // No Best Bid, No Best Offer Change
		case 'J': // No Best Bid, Quote Contains Best Offer
		case 'L': // No Best Bid, No Best Offer
		case ' ': // Ineligible
		break;

		case 'C': // No Best Bid Change, Best Offer 
		case 'G': // Quote Contains Best Bid, Best Offer 
		case 'K': // No Best Bid, Best Offer 
            //decode best offer participant Id
			msg->bbo.bestOffer.partId = 
				fast->decode_u32(fast, BEST_OFFER_PARTICIPANT_ID_V2);

            //decode best offer denominator code 
			msg->bbo.bestOffer.denominator = 
				fast->decode_u32(fast, BEST_OFFER_PRICE_DENOMINATOR_CODE_V2);

            //decode best offer price  
			u32_to_ascii(msg->bbo.bestOffer.price,
							sizeof(msg->bbo.bestOffer.price),
							fast->decode_u32(fast, BEST_OFFER_PRICE_V2)); 

            //decode best size 
			u32_to_ascii(msg->bbo.bestOffer.size,
							sizeof(msg->bbo.bestOffer.size),
							fast->decode_u32(fast, BEST_OFFER_SIZE_V2)); 

			length += sizeof(msg->bbo.bestOffer);
		break;

		case 'M': // Best Bid , No Best Offer Change
		case 'P': // Best Bid , No Best Offer 
		case 'N': // Best Bid , Quote Contains Best Offer
            //decode best bid participant id
			msg->bbo.bestBid.partId = fast->decode_u32(fast, 
										BEST_BID_PARTICIPANT_ID_V2);

            //decode best bid price denominator code 
			msg->bbo.bestBid.denominator = 
				fast->decode_u32(fast, BEST_BID_PRICE_DENOMINATOR_CODE_V2);

            //decode best bid price 
			u32_to_ascii(msg->bbo.bestBid.price, 
                         	sizeof(msg->bbo.bestBid.price),
			             	fast->decode_u32(fast, BEST_BID_PRICE_V2)); 

            //decode best bid size 
			u32_to_ascii(msg->bbo.bestBid.size, 
                         	sizeof(msg->bbo.bestBid.size), 
			         	 	fast->decode_u32(fast, BEST_BID_SIZE_V2)); 

			length += sizeof(msg->bbo.bestBid);
		break;

		case 'O': // Best Bid , Best Offer 
            //decode best bid participant id 
			msg->bbo.bestBidOffer.bestBid.partId = 
				fast->decode_u32(fast, BEST_BID_PARTICIPANT_ID_V2);

            //decode best bid price denom code 
			msg->bbo.bestBidOffer.bestBid.denominator = 
				fast->decode_u32(fast, 
                                 BEST_BID_PRICE_DENOMINATOR_CODE_V2);

            //decode best bid price 
			u32_to_ascii(msg->bbo.bestBidOffer.bestBid.price, 
							sizeof(msg->bbo.bestBidOffer.bestBid.price), 
							fast->decode_u32(fast, BEST_BID_PRICE_V2));

            //decode best bid size 
			u32_to_ascii(msg->bbo.bestBidOffer.bestBid.size, 
							sizeof(msg->bbo.bestBidOffer.bestBid.size), 
							fast->decode_u32(fast, BEST_BID_SIZE_V2)); 

            //decode best offer participant Id 
			msg->bbo.bestBidOffer.bestOffer.partId = 
				fast->decode_u32(fast,BEST_OFFER_PARTICIPANT_ID_V2);

            //decode best offer price denominator code 
			msg->bbo.bestBidOffer.bestOffer.denominator = 
				fast->decode_u32(fast, BEST_OFFER_PRICE_DENOMINATOR_CODE_V2);

            //decode best offer price 
			u32_to_ascii(msg->bbo.bestBidOffer.bestOffer.price, 
							sizeof(msg->bbo.bestBidOffer.bestOffer.price), 
							fast->decode_u32(fast, BEST_OFFER_PRICE_V2)); 

            //decode best offer size 
			u32_to_ascii(msg->bbo.bestBidOffer.bestOffer.size, 
							sizeof(msg->bbo.bestBidOffer.bestOffer.size), 
							fast->decode_u32(fast, BEST_OFFER_SIZE_V2)); 

			length += sizeof(msg->bbo.bestBidOffer);
		break;

		default:
			printf("unknown bbo indicator!! - %c -\n", msg->bboIndicator);
	}

    /////  Category 'k' Version 2 - Decode ( end ) /////

	//copy the msg to buf
	memcpy(buf, (char *)msg, length);

	return length;
}

////////////////////////////////////////////////////////////////////////////////
//
//	decode for V2
//
// int decode_OpraFastOpenIntMsg_v2(Fast* fast, char *buf,
//   CatdMsg_v2 *msg)
//
//  decode category d message
//
////////////////////////////////////////////////////////////////////////////////

int 
decode_OpraFastOpenIntMsg_v2(Fast* fast, char *buf, CatdMsg_v2 *msg)
{
    char us, endByte;
	unsigned int length = sizeof(CatdMsg_v2);

	memset(msg, 0, length);

	//decode header
	msg->hdr.category = 'd'; // 'd' for open interest

    /////  Category 'd' Version 2 - Decode (start) /////

	msg_decode_hdr(fast, &msg->hdr);

	//decode msg body
	memset(msg->symbol,' ',sizeof(msg->symbol));
	fast->decode_str(fast, SECURITY_SYMBOL_V2, 
                     	msg->symbol, sizeof(msg->symbol));
    //decode expiration month - 1 byte
    msg->expirationMonth = fast->decode_u32(fast, EXPIRATION_MONTH_V2); 

    //decode expiration Date - 2 bytes
    u32_to_ascii(msg->expirationDate,sizeof(msg->expirationDate),
                 fast->decode_u32(fast, EXPIRATION_DATE_V2));

    //decode expiration Year - 2 bytes
    u32_to_ascii(msg->year,sizeof(msg->year), 
                 fast->decode_u32(fast, YEAR_V2));


    //decode strike price denom code
	msg->strikePriceDenomCode = 
		fast->decode_u32(fast, STRIKE_PRICE_DENOMINATOR_CODE_V2);

    //decode explict strike price 
	u32_to_ascii(msg->explicitStrike, 
					sizeof(msg->explicitStrike), 
					fast->decode_u32(fast,EXPLICIT_STRIKE_PRICE_V2)); 

    //decode strike price
	msg->strikePriceCode = fast->decode_u32(fast, STRIKE_PRICE_CODE_V2);

    //decode open interest volume
	u32_to_ascii(msg->openIntVolume, 
					sizeof(msg->openIntVolume), 
        			fast->decode_u32(fast,OPEN_INT_VOLUME_V2)); 

    /////  Category 'd' Version 2 - Decode ( end ) /////

	//copy the msg to buf
	memcpy(buf, (char *)msg, length);

    return length;
}


////////////////////////////////////////////////////////////////////////////////
//
//	decode for V2
//
//  int decode_OpraFastLastSaleMsg(Fast* fast, char *buf, CataMsg_v2 *msg)
//
//  decode category a message
//
////////////////////////////////////////////////////////////////////////////////

int 
decode_OpraFastLastSaleMsg_v2(Fast* fast, char *buf, CataMsg_v2 *msg)
{
    char us, endByte;
	unsigned int length = sizeof(CataMsg_v2);

	memset(msg, 0, length);

	//decode header
	msg->hdr.category = 'a'; // 'a' for Equity and Index Last Sale


    /////  Category 'a' Version 2 - Decode (start) /////

    //decode header
	msg_decode_hdr(fast, &msg->hdr);

	//decode symbol option
	memset(msg->symbol,' ',sizeof(msg->symbol));
	fast->decode_str(fast,SECURITY_SYMBOL_V2, msg->symbol, 
                     	sizeof(msg->symbol));

    //decode expiration month - 1 byte
    msg->expirationMonth = fast->decode_u32(fast, EXPIRATION_MONTH_V2); 

    //decode expiration Date - 2 bytes
    u32_to_ascii(msg->expirationDate,sizeof(msg->expirationDate),
                     fast->decode_u32(fast, EXPIRATION_DATE_V2));

    //decode expiration Year - 2 bytes
    u32_to_ascii(msg->year,sizeof(msg->year), 
                 	fast->decode_u32(fast, YEAR_V2));

    //decode strike price deno code
	msg->strikePriceDenomCode = fast->decode_u32(fast, 
		STRIKE_PRICE_DENOMINATOR_CODE_V2);

    //decode explicit strike price 
	u32_to_ascii(msg->explicitStrike, sizeof(msg->explicitStrike), 
					fast->decode_u32(fast, EXPLICIT_STRIKE_PRICE_V2)); 

    //decode strike price
	msg->strikePriceCode = fast->decode_u32(fast, STRIKE_PRICE_CODE_V2);

	//decode msg body
	u32_to_ascii(msg->volume,sizeof(msg->volume), 
					fast->decode_u32(fast, VOLUME_V2)); 

	//decode premium price denom code 
	msg->premiumPriceDenomCode = fast->decode_u32 (fast, 
		PREMIUM_PRICE_DENOMINATOR_CODE_V2);

    //decode premium price
	u32_to_ascii(msg->premium, sizeof(msg->premium), 
					fast->decode_u32(fast, PREMIUM_PRICE_V2)); 

    //decode session indicator
	msg->sessionIndicator = fast->decode_u32(fast, SESSION_INDICATOR_V2);

    /////  Category 'a' Version 2 - Decode ( end ) /////

	//copy the msg to buf
	memcpy(buf, (char *)msg, length);

	return length;
}


////////////////////////////////////////////////////////////////////////////////
//
//	decode for V2
//
//  int decode_OpraFastEodMsg_v2(Fast* fast, char *buf, CatfMsg_v2 *msg)
// 
//  decode category f message
// 
////////////////////////////////////////////////////////////////////////////////

int 
decode_OpraFastEodMsg_v2(Fast* fast, char *buf, CatfMsg_v2 *msg)
{
    char us, endByte;
	unsigned int length = sizeof(CatfMsg_v2);

	memset(msg, 0, length);

	//decode header
	msg->hdr.category = 'f'; // 'f' for Equity and Index EOD summary

    /////  Category 'f' Version 2 - Decode (start) /////
 
    //decode message header
	msg_decode_hdr(fast, &msg->hdr);

	//decode symbol option
	//left justified FCO (3 bytes only)
	memset(msg->symbol,' ',sizeof(msg->symbol));
	fast->decode_str(fast, SECURITY_SYMBOL_V2, 
	                     msg->symbol, sizeof(msg->symbol));

    //decode expiration month - 1 byte
    msg->expirationMonth = fast->decode_u32(fast, EXPIRATION_MONTH_V2); 

    //decode expiration Date - 2 bytes
    u32_to_ascii(msg->expirationDate,sizeof(msg->expirationDate),
   		              fast->decode_u32(fast, EXPIRATION_DATE_V2));

    //decode expiration Year - 2 bytes
    u32_to_ascii(msg->year,sizeof(msg->year), 
    	             fast->decode_u32(fast, YEAR_V2));


    //decode strike price denom code
	msg->strikePriceDenomCode = fast->decode_u32(fast, 
		STRIKE_PRICE_DENOMINATOR_CODE_V2);

    //decode explicit strike 
	u32_to_ascii(msg->explicitStrike, sizeof(msg->explicitStrike), 
					fast->decode_u32(fast, EXPLICIT_STRIKE_PRICE_V2)); 

    //decode strike price code
	msg->strikePriceCode = fast->decode_u32(fast, STRIKE_PRICE_CODE_V2);

    //decode volume 
	u32_to_ascii(msg->volume, sizeof(msg->volume), 
					fast->decode_u32(fast, VOLUME_V2)); 

    //decode open int volume 
	u32_to_ascii(msg->openIntVolume, sizeof(msg->openIntVolume), 
					fast->decode_u32(fast, OPEN_INT_VOLUME_V2));

    //decode premium price denom code 
	msg->premiumPriceDenomCode = fast->decode_u32 (fast, 
		PREMIUM_PRICE_DENOMINATOR_CODE_V2);

    //decode open  price 
	u32_to_ascii(msg->open, sizeof(msg->open), fast->decode_u32(fast, 
					OPEN_PRICE_V2));

    //decode high price 
	u32_to_ascii(msg->high, sizeof(msg->high), fast->decode_u32(fast, 
					HIGH_PRICE_V2)); 

    //decode low price 
	u32_to_ascii(msg->low, 
        sizeof(msg->low), fast->decode_u32(fast, LOW_PRICE_V2));

    //decode last price 
	u32_to_ascii(msg->last, sizeof(msg->last), fast->decode_u32(fast, 
					LAST_PRICE_V2)); 

    //decode net change indicator 
	msg->netChangeIndicator = fast->decode_u32(fast, NET_CHANGE_INDICATOR_V2);

    //decode net change 
	u32_to_ascii(msg->netChange, sizeof(msg->netChange), 
        			fast->decode_u32(fast, NET_CHANGE_V2)); 

    //decode underlying  price denom code 
	msg->underlyingPriceDenomCode = fast->decode_u32(fast, 
		UNDERLYING_PRICE_DENOM_V2);

    //decode underlying  stock price 
	u32_to_ascii(msg->underlyingStockPrice, 
					sizeof(msg->underlyingStockPrice),
					fast->decode_u32(fast, UNDERLYING_STOCK_PRICE_V2)); 

    //decode bid  price 
	u32_to_ascii(msg->bidQuote, 
                 	sizeof(msg->bidQuote), 
                 	fast->decode_u32(fast, BID_PRICE_V2)); 

    //decode offer price 
	u32_to_ascii(msg->askQuote, 
                 	sizeof(msg->askQuote),
                 	fast->decode_u32(fast, OFFER_PRICE_V2)); 

    /////  Category 'f' Version 2 - Decode ( end ) /////

	//copy the msg to buf
	memcpy(buf, (char *)msg, length);

	return length;
}


////////////////////////////////////////////////////////////////////////////////
//
//	decode for V2
//
// 	int decode_OpraFastulValueMsg_v2(Fast* fast, char *buf, CatYMsg_v2 *msg)
//
// 	decode category Y message
//
////////////////////////////////////////////////////////////////////////////////

int 
decode_OpraFastUlValueMsg_v2(Fast* fast, char *buf, CatYMsg_v2 *msg)
{
    char         us, endByte;
    unsigned int length;
	memset(msg, 0, sizeof(CatYMsg_v2));

	//decode header
	msg->hdr.category = 'Y'; // 'Y' for Underlying Value

    /////  Category 'Y' Version 2 - Decode (start) /////

	msg_decode_hdr(fast, &msg->hdr);

	length =  sizeof(msg->hdr);
	length += 2;    // add NUMBER_OF_INDICES_IN_GROUP - always present!

	//decode msg body
	int indices;
	int i = 0;
	switch(msg->hdr.type)
	{
		case ' ': 
			indices =
				fast->decode_u32(fast, NUMBER_OF_INDICES_IN_GROUP_V2);
			u32_to_ascii(msg->body.numOfIndices,
							sizeof(msg->body.numOfIndices), indices);
			while( i < indices )
			{
				memset(msg->body.indexGroup[i].symbol, ' ', 
						sizeof(msg->body.indexGroup[i].symbol)); 
				fast->decode_str(fast, INDEX_SYMBOL_V2, 
					msg->body.indexGroup[i].symbol, 
					sizeof(msg->body.indexGroup[i].symbol));

				fast->decode_str(fast, INDEX_VALUE_V2,
					msg->body.indexGroup[i].group.indexValue,
					sizeof(msg->body.indexGroup[i].group.indexValue));

				++i;
			};
			length += (indices * 11);
		break;
		case 'I':
			indices =
				fast->decode_u32(fast, NUMBER_OF_INDICES_IN_GROUP_V2); 
			u32_to_ascii(msg->body.numOfIndices,
				sizeof(msg->body.numOfIndices), indices);
			while( i < indices )
			{
				memset(msg->body.indexGroup[i].symbol, ' ', 
					sizeof(msg->body.indexGroup[i].symbol)); 

				fast->decode_str(fast, INDEX_SYMBOL_V2, 
					msg->body.indexGroup[i].symbol, 
					sizeof(msg->body.indexGroup[i].symbol));

				fast->decode_str(fast, BID_INDEX_VALUE_V2, 
					msg->body.indexGroup[i].group.bidOffer.bidValueIndex, 
					sizeof(msg->body.indexGroup[i].group.bidOffer.bidValueIndex));

				fast->decode_str(fast, OFFER_INDEX_VALUE_V2, 
					msg->body.indexGroup[i].group.bidOffer.offerValueIndex, 
					sizeof(msg->body.indexGroup[i].group.bidOffer.offerValueIndex));

				++i;
			};
			length += (indices * 19);
		break;
		case 'F':
		case 'C':
			indices = fast->decode_u32(fast, 
				NUMBER_OF_FOREIGN_CURRENCY_SPOT_VALUES_IN_GROUP_V2);
			u32_to_ascii(msg->body.numOfIndices,
				sizeof(msg->body.numOfIndices), indices);
			while( i < indices )
			{
				memset(msg->body.indexGroup[i].symbol, ' ', 
					sizeof(msg->body.indexGroup[i].symbol)); 
				fast->decode_str(fast, FCO_SYMBOL_V2, 
					msg->body.indexGroup[i].symbol, 
					sizeof(msg->body.indexGroup[i].symbol));

				u32_to_ascii(msg->body.indexGroup[i].group.fcSpotVal.decimalPlacementIndicator, 
					sizeof(msg->body.indexGroup[i].group.fcSpotVal.decimalPlacementIndicator),
					fast->decode_u32(fast, DECIMAL_PLACEMENT_INDICATOR_V2));

				u32_to_ascii(msg->body.indexGroup[i].group.fcSpotVal.foreignCurSpotValue, 
					sizeof(msg->body.indexGroup[i].group.fcSpotVal.foreignCurSpotValue), 
					fast->decode_u32(fast, FOREIGN_CURRENCY_SPOT_VALUE_V2)); 

				++i;
			};
			length += (indices * 13);
		break;
		default:
				;
			// unknown message type!!
	}

    /////  Category 'Y' Version 2 - Decode ( end ) /////

	//copy the msg to buf
	memcpy(buf, (char *)msg, length);
	return length;
}


////////////////////////////////////////////////////////////////////////////////
//
//	decode for V2
//
// int decode_OpraFastAdminMsg_v2(Fast* fast, char *buf, CatCMsg_v2 *msg)
//
// decode category C message
//
////////////////////////////////////////////////////////////////////////////////

int 
decode_OpraFastAdminMsg_v2(Fast* fast, char *buf, CatCMsg_v2 *msg)
{
    int bodyLen = 0;
    char us, endByte;
    unsigned int length = sizeof(msg->hdr);

    memset(msg, 0, length);

    //decode msg header
    msg->hdr.category = 'C'; // 'C' for Admin Msg
	
    /////  Category 'C' Version 2 - Decode (start) /////

    //decode header
	msg_decode_hdr(fast, &msg->hdr);

	//decode msg body

    //decode text
	bodyLen = fast->decode_str(fast, TEXT_V2, msg->text, sizeof(msg->text));

	if(bodyLen > 0)
	   length += bodyLen;

    /////  Category 'C' Version 2 - Decode ( end ) /////

    //copy the msg to buf
    memcpy(buf, (char *)msg, length);
    return length;
}


////////////////////////////////////////////////////////////////////////////////
//
//	decode for V2
//
//	int decode_OpraFastControlMsg_v2(Fast* fast,char *buf,CatHMsg_v2 *msg)
//
// 	decode category H message
//
////////////////////////////////////////////////////////////////////////////////
int 
decode_OpraFastControlMsg_v2(Fast* fast, char *buf, CatHMsg_v2 *msg)
{
    int bodyLen = 0;
    unsigned int length = sizeof(msg->hdr);
    char us, endByte;

    memset(msg, 0, length);

    /////  Category 'H' Version 2 - Decode (start) /////

    //decode header
    msg->hdr.category = 'H'; // 'H' for Control msg

    //decode message header
	msg_decode_hdr(fast, &msg->hdr);

    //decode text
	bodyLen = fast->decode_str(fast, TEXT_V2, msg->text, sizeof(msg->text));
	if(bodyLen > 0)
		length += bodyLen;

    /////  Category 'H' Version 2 - Decode ( end ) /////

    //copy the msg to buf
    memcpy(buf, (char *)msg, length);
    return length;
}
