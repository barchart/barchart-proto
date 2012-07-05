/**************************************************************************
*  System        : OPRA
*
*  Module Name   : opra_fast.h
*  Copyright (c) : SIAC 2007
*  Date          : 3/2007
*
*  Description   : Definition of opra messages
***************************************************************************/

#ifndef _oprafast_h_
#define _oprafast_h_
#include "fast_wrapper.h"

////////////////////////////////////////////////////////////////////////////////
//
//  enumerators for opra message field tags
//
////////////////////////////////////////////////////////////////////////////////

enum opra_tid
{
    OPRA_BASE_TID=0
};

enum opra_msg_field_tags_v2
{
        // opra header

        MESSAGE_CATEGORY_V2 = 
            MAKE_TAG (FAST_TYPE_U32, FAST_OP_COPY, OPRA_BASE_TID, 0),
        MESSAGE_TYPE_V2 = 
            MAKE_TAG (FAST_TYPE_U32, FAST_OP_COPY, OPRA_BASE_TID, 1),
        PARTICIPANT_ID_V2 = 
            MAKE_TAG (FAST_TYPE_U32, FAST_OP_COPY, OPRA_BASE_TID, 2),
        RETRANSMISSION_REQUESTER_V2 =
            MAKE_TAG (FAST_TYPE_U32, FAST_OP_COPY, OPRA_BASE_TID, 3),
        MESSAGE_SEQUENCE_NUMBER_V2 =
            MAKE_TAG (FAST_TYPE_U64, FAST_OP_INCR, OPRA_BASE_TID, 4),
        TIME_V2 = 
            MAKE_TAG (FAST_TYPE_U32, FAST_OP_COPY, OPRA_BASE_TID, 5),

    	//sequential order 

        SECURITY_SYMBOL_V2 = 
            MAKE_TAG (FAST_TYPE_STR, FAST_OP_COPY, OPRA_BASE_TID, 6),
        EXPIRATION_MONTH_V2 = 
            MAKE_TAG (FAST_TYPE_U32, FAST_OP_COPY, OPRA_BASE_TID, 7),
        EXPIRATION_DATE_V2 = 
            MAKE_TAG (FAST_TYPE_U32, FAST_OP_COPY, OPRA_BASE_TID, 8),
        YEAR_V2 =
            MAKE_TAG (FAST_TYPE_U32, FAST_OP_COPY, OPRA_BASE_TID, 9),
        STRIKE_PRICE_DENOMINATOR_CODE_V2 = 
            MAKE_TAG (FAST_TYPE_U32, FAST_OP_COPY, OPRA_BASE_TID, 10),
        EXPLICIT_STRIKE_PRICE_V2 =
            MAKE_TAG (FAST_TYPE_U32, FAST_OP_COPY, OPRA_BASE_TID, 11),
        STRIKE_PRICE_CODE_V2 =
            MAKE_TAG (FAST_TYPE_U32, FAST_OP_COPY, OPRA_BASE_TID, 12),
        VOLUME_V2 = 
            MAKE_TAG (FAST_TYPE_U32, FAST_OP_COPY, OPRA_BASE_TID, 13),
        OPEN_INT_VOLUME_V2 = 
            MAKE_TAG (FAST_TYPE_U32, FAST_OP_COPY, OPRA_BASE_TID, 14),
        PREMIUM_PRICE_DENOMINATOR_CODE_V2 =
            MAKE_TAG (FAST_TYPE_U32, FAST_OP_COPY, OPRA_BASE_TID, 15),
        PREMIUM_PRICE_V2 = 
            MAKE_TAG (FAST_TYPE_U32, FAST_OP_COPY, OPRA_BASE_TID, 16),
        OPEN_PRICE_V2 = 
            MAKE_TAG (FAST_TYPE_U32, FAST_OP_COPY, OPRA_BASE_TID, 17),
        HIGH_PRICE_V2 = 
            MAKE_TAG (FAST_TYPE_U32, FAST_OP_COPY, OPRA_BASE_TID, 18),
        LOW_PRICE_V2 = 
            MAKE_TAG (FAST_TYPE_U32, FAST_OP_COPY, OPRA_BASE_TID, 19),
        LAST_PRICE_V2 = 
            MAKE_TAG (FAST_TYPE_U32, FAST_OP_COPY, OPRA_BASE_TID, 20),
        NET_CHANGE_INDICATOR_V2 =
            MAKE_TAG (FAST_TYPE_U32, FAST_OP_COPY, OPRA_BASE_TID, 21),
        NET_CHANGE_V2 = 
            MAKE_TAG (FAST_TYPE_U32, FAST_OP_COPY, OPRA_BASE_TID, 22),
        UNDERLYING_PRICE_DENOM_V2 =
            MAKE_TAG (FAST_TYPE_U32, FAST_OP_COPY, OPRA_BASE_TID, 23),
        UNDERLYING_STOCK_PRICE_V2 =
            MAKE_TAG (FAST_TYPE_U32, FAST_OP_COPY, OPRA_BASE_TID, 24),

        BID_PRICE_V2 = 
            MAKE_TAG (FAST_TYPE_U32, FAST_OP_COPY, OPRA_BASE_TID, 25),
        BID_SIZE_V2 = 
            MAKE_TAG (FAST_TYPE_U32, FAST_OP_COPY, OPRA_BASE_TID, 26),
        OFFER_PRICE_V2 = 
            MAKE_TAG (FAST_TYPE_U32, FAST_OP_COPY, OPRA_BASE_TID, 27),
        OFFER_SIZE_V2 = 
            MAKE_TAG (FAST_TYPE_U32, FAST_OP_COPY, OPRA_BASE_TID, 28),
        SESSION_INDICATOR_V2 =
            MAKE_TAG (FAST_TYPE_U32, FAST_OP_COPY, OPRA_BASE_TID, 29),
        BBO_INDICATOR_V2 = 
            MAKE_TAG (FAST_TYPE_U32, FAST_OP_COPY, OPRA_BASE_TID, 30),

        BEST_BID_PARTICIPANT_ID_V2 =
            MAKE_TAG (FAST_TYPE_U32, FAST_OP_COPY, OPRA_BASE_TID, 31),
        BEST_BID_PRICE_DENOMINATOR_CODE_V2 =
            MAKE_TAG (FAST_TYPE_U32, FAST_OP_COPY, OPRA_BASE_TID, 32),
        BEST_BID_PRICE_V2 = 
            MAKE_TAG (FAST_TYPE_U32, FAST_OP_COPY, OPRA_BASE_TID, 33),
        BEST_BID_SIZE_V2 = 
            MAKE_TAG (FAST_TYPE_U32, FAST_OP_COPY, OPRA_BASE_TID, 34),

        BEST_OFFER_PARTICIPANT_ID_V2 =
            MAKE_TAG (FAST_TYPE_U32, FAST_OP_COPY, OPRA_BASE_TID, 35),

        BEST_OFFER_PRICE_DENOMINATOR_CODE_V2 =
            MAKE_TAG (FAST_TYPE_U32, FAST_OP_COPY, OPRA_BASE_TID, 36),
        BEST_OFFER_PRICE_V2 =
            MAKE_TAG (FAST_TYPE_U32, FAST_OP_COPY, OPRA_BASE_TID, 37),
        BEST_OFFER_SIZE_V2 = 
            MAKE_TAG (FAST_TYPE_U32, FAST_OP_COPY, OPRA_BASE_TID, 38),
    	NUMBER_OF_INDICES_IN_GROUP_V2 =
            MAKE_TAG (FAST_TYPE_U32, FAST_OP_COPY, OPRA_BASE_TID, 39),
    	NUMBER_OF_FOREIGN_CURRENCY_SPOT_VALUES_IN_GROUP_V2 =
            MAKE_TAG (FAST_TYPE_U32, FAST_OP_COPY, OPRA_BASE_TID, 40),
    	INDEX_SYMBOL_V2 = 
            MAKE_TAG (FAST_TYPE_STR, FAST_OP_COPY, OPRA_BASE_TID, 41),
    	INDEX_VALUE_V2 =
            MAKE_TAG (FAST_TYPE_STR, FAST_OP_COPY, OPRA_BASE_TID, 42),
    	BID_INDEX_VALUE_V2 =
            MAKE_TAG (FAST_TYPE_STR, FAST_OP_COPY, OPRA_BASE_TID, 43),
    	OFFER_INDEX_VALUE_V2 =
            MAKE_TAG (FAST_TYPE_STR, FAST_OP_COPY, OPRA_BASE_TID, 44),
    	FCO_SYMBOL_V2 =
            MAKE_TAG (FAST_TYPE_STR, FAST_OP_COPY, OPRA_BASE_TID, 45),
    	DECIMAL_PLACEMENT_INDICATOR_V2 =
            MAKE_TAG (FAST_TYPE_U32, FAST_OP_COPY, OPRA_BASE_TID, 46),
    	FOREIGN_CURRENCY_SPOT_VALUE_V2 =
            MAKE_TAG (FAST_TYPE_U32, FAST_OP_COPY, OPRA_BASE_TID, 47),
    	TEXT_V2 =
            MAKE_TAG (FAST_TYPE_STR, FAST_OP_COPY, OPRA_BASE_TID, 48),
    	DEF_MSG_V2 =
            MAKE_TAG (FAST_TYPE_STR, FAST_OP_COPY, OPRA_BASE_TID, 49),
};


////////////////////////////////////////////////////////////////////////////////
//
//  structure for opra header 
//
////////////////////////////////////////////////////////////////////////////////

typedef struct opraHdr_v2 
{
   unsigned char participantId;
   unsigned char retran; 
   unsigned char category;
   unsigned char type;
   unsigned char seqNumber[10];
   unsigned char time[9];
}OpraMsgHdr_v2;


////////////////////////////////////////////////////////////////////////////////
//
//  structure for Default message
//
////////////////////////////////////////////////////////////////////////////////

typedef struct defMsg
{
  unsigned char category;
  unsigned char body[450];
} CatDefMsg;

////////////////////////////////////////////////////////////////////////////////
//
//  common structure for best bid , best offer
//
////////////////////////////////////////////////////////////////////////////////

typedef struct append_v2
{
   unsigned char partId;
   unsigned char denominator;
   unsigned char price[8];
   unsigned char size[5];
}Appendage_v2;



///////////////////////////////////////////////////////////////////////////////
//  structure for best-bid offer
//
////////////////////////////////////////////////////////////////////////////////


typedef struct best_bid_offer_v2
{
   Appendage_v2 bestBid;
   Appendage_v2 bestOffer;
} Best_Bid_Offer_v2;


////////////////////////////////////////////////////////////////////////////////
//
//  structure for best-bid offer
//
////////////////////////////////////////////////////////////////////////////////

typedef union bbo_appendage_v2
{
   Appendage_v2      bestBid;
   Appendage_v2      bestOffer;
   Best_Bid_Offer_v2 bestBidOffer;
} BBO_Appendage_v2;


////////////////////////////////////////////////////////////////////////////////
//
//  structure for catgoty 'k' message
//
////////////////////////////////////////////////////////////////////////////////

typedef struct QuoteSizeBody_v2
{
   OpraMsgHdr_v2 hdr;
   unsigned   char symbol[5];
   unsigned   char expirationMonth;
   unsigned   char expirationDate[2];
   unsigned   char year[2];
   unsigned   char strikePriceDenomCode;
   unsigned   char explicitStrike[6];
   unsigned   char strikePriceCode;
   unsigned   char premiumPriceDenomCode;
   unsigned   char bidQuote[8];
   unsigned   char bidSize[5];
   unsigned   char askQuote[8];
   unsigned   char askSize[5];
   unsigned   char sessionIndicator;
   unsigned   char bboIndicator;
   BBO_Appendage_v2   bbo;          
} CatkMsg_v2; 


////////////////////////////////////////////////////////////////////////////////
//
//  structure for catgoty 'd' message
//
////////////////////////////////////////////////////////////////////////////////


typedef struct OpenIntBody_v2
{
   OpraMsgHdr_v2 hdr;
   unsigned   char symbol[5];
   unsigned   char expirationMonth;
   unsigned   char expirationDate[2];
   unsigned   char year[2];
   unsigned   char strikePriceDenomCode;
   unsigned   char explicitStrike[6];
   unsigned   char strikePriceCode;
   unsigned   char openIntVolume[7];
} CatdMsg_v2;

////////////////////////////////////////////////////////////////////////////////
//
//  structure for catgoty 'a' message
//
////////////////////////////////////////////////////////////////////////////////

typedef struct LastSaleBody_v2
{
   OpraMsgHdr_v2 hdr;
   unsigned   char symbol[5];
   unsigned   char expirationMonth;
   unsigned   char expirationDate[2];
   unsigned   char year[2];
   unsigned   char strikePriceDenomCode;
   unsigned   char explicitStrike[6];
   unsigned   char strikePriceCode;
   unsigned   char volume[6];
   unsigned   char premiumPriceDenomCode;
   unsigned   char premium[8];
   unsigned   char sessionIndicator;
} CataMsg_v2;

////////////////////////////////////////////////////////////////////////////////
//
//  structure for catgoty 'f' message
//
////////////////////////////////////////////////////////////////////////////////

typedef struct EodBody_v2
{
   OpraMsgHdr_v2 hdr;
   unsigned   char symbol[5];
   unsigned   char expirationMonth;
   unsigned   char expirationDate[2];
   unsigned   char year[2];
   unsigned   char strikePriceDenomCode;
   unsigned   char explicitStrike[6];
   unsigned   char strikePriceCode;
   unsigned   char volume[6];
   unsigned   char openIntVolume[7];
   unsigned   char premiumPriceDenomCode;
   unsigned   char open[8];
   unsigned   char high[8];
   unsigned   char low[8];
   unsigned   char last[8];
   unsigned   char netChangeIndicator;
   unsigned   char netChange[8];
   unsigned   char underlyingPriceDenomCode;
   unsigned   char underlyingStockPrice[11];
   unsigned   char bidQuote[8];
   unsigned   char askQuote[8];
} CatfMsg_v2;

////////////////////////////////////////////////////////////////////////////////
//
//  structure for catgoty 'Y' message body
//
////////////////////////////////////////////////////////////////////////////////

typedef struct ulValueBody_v2
{
    unsigned char numOfIndices[2];
    struct
    {
        unsigned char symbol[3];
        union
        {
            unsigned char indexValue[8];
            struct
            {
                unsigned char bidValueIndex[8];
                unsigned char offerValueIndex[8];
            } bidOffer;
            struct
            {
                unsigned char decimalPlacementIndicator[2];
                unsigned char foreignCurSpotValue[8];
            }fcSpotVal;

        } group;
    } indexGroup[5];
} CatYBody_v2;

////////////////////////////////////////////////////////////////////////////////
//
//  structure for catgoty 'Y' message
//
////////////////////////////////////////////////////////////////////////////////

typedef struct ulValueMsg_v2
{
   OpraMsgHdr_v2 hdr;
   CatYBody_v2 body;
} CatYMsg_v2;

////////////////////////////////////////////////////////////////////////////////
//
//  structure for catgoty 'C' message
//
////////////////////////////////////////////////////////////////////////////////


typedef struct adminMsg_v2
{
    OpraMsgHdr_v2 hdr;
    unsigned char text[450];    
} CatCMsg_v2;

////////////////////////////////////////////////////////////////////////////////
//
//  structure for catgoty 'H' message
//
////////////////////////////////////////////////////////////////////////////////


typedef struct ctrlMsg_v2
{
    OpraMsgHdr_v2 hdr;
    unsigned char text[450];    
} CatHMsg_v2;

////////////////////////////////////////////////////////////////////////////////
//
//  union of opra messages
//
////////////////////////////////////////////////////////////////////////////////

typedef union OperMessage_v2
{
    CatDefMsg  defMsg;
    CatkMsg_v2 quoteSizeBody;
    CatdMsg_v2 openIntBody;
    CataMsg_v2 lastSaleBody;
    CatfMsg_v2 eodBody;
    CatYMsg_v2 ulValueMsg;
    CatCMsg_v2 adminMsg;
    CatHMsg_v2 ctrlMsg;
}OpraMsg_v2;



unsigned char* u32_to_ascii (unsigned char* dest, size_t len, unsigned int ul);
unsigned char* u64_to_ascii (unsigned char* dest, size_t len, u64 ul);


#endif
