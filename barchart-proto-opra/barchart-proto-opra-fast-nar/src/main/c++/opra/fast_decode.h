/**************************************************************************
*  System        : OPRA
*
*  Module Name   : fast_process.h
*  Copyright (c) : SIAC 2007
*  Date          : 1/2008
*
*  Description   : Header for decode API for Ver 1 and Ver 2
***************************************************************************/

#ifndef _fastdecode_h_
#define _fastdecode_h_

////////////////////////////////////////////////////////////////////////////////
//
//	DECODING FOR VERSION 2
//
////////////////////////////////////////////////////////////////////////////////

//  decodes fast opra default message. 
int 
decode_OpraFastDefMsg_v2(Fast* fast, char *buf, CatDefMsg* msg);

//  decode category k message
int 
decode_OpraFastQuoteSizeMsg_v2(Fast* fast, char *buf, CatkMsg_v2* msg);

//  decode category d message
int 
decode_OpraFastOpenIntMsg_v2(Fast* fast, char *buf, CatdMsg_v2 *msg);

//  decode category a message
int 
decode_OpraFastLastSaleMsg_v2(Fast* fast, char *buf, CataMsg_v2 *msg);

//  decode category f message
int 
decode_OpraFastEodMsg_v2(Fast* fast, char *buf, CatfMsg_v2 *msg);

// decode category Y message
int 
decode_OpraFastUlValueMsg_v2(Fast* fast, char *buf, CatYMsg_v2 *msg);

// decode category C message
int 
decode_OpraFastAdminMsg_v2(Fast* fast, char *buf, CatCMsg_v2 *msg);

// decode category H message
int 
decode_OpraFastControlMsg_v2(Fast* fast, char *buf, CatHMsg_v2 *msg);

#endif
