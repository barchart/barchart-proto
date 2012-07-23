/**************************************************************************
*  System        : OPRA
*
*  Module Name   : fast_process.h
*  Copyright (c) : SIAC 2007
*  Date          : 3/2007
*
*  Description   : Header for decode API
***************************************************************************/

#ifndef _fastprocess_h_
#define _fastprocess_h_

#include "fast_wrapper.h"

#define PACKET_SIZE  1024*10       // Opra Packet Size
#define US           0x1F       // Unit Separator
#define SOH          0x01       // Start of Header
#define ETX          0x03       // End of Text

// offsets for V2 messages
#define SEQ_OFFSET 		10
#define VERSION_OFFSET 	1
#define NUM_MSG_OFFSET 	3
#define LEN_OFFSET 		1
#define VERSION_2 		2
#define VERSION_1 		1

// function to decode fast opra messages

int 
decode(Fast* fast, unsigned char* decoded_packet, unsigned char* encoded_packet, unsigned int rec_len);

#endif 
