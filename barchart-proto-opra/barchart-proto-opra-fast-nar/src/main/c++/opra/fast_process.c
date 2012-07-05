/*****************************************************************************
*  System        : OPRA
*
*  Module Name   : fast_process.c
*  Copyright (c) : SIAC 2007
*  Date          : 3/2007
*
*  Description	 : This file contains the definition of the main decode 
*                  function. The function reads the category of the message
*                  and calls appropriate decoding functions.     
******************************************************************************/

#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>
#include <errno.h>
#include "fast_process.h"
#include "fast_wrapper.h"
#include "opra_fast.h"
#include "fast_decode.h"

///////////////////////////////////////////////////////////////////////////
//
//  int decode(unsigned int rec_len, unsigned char* encoded_packet, 
//	unsigned char* decoded_packet)
//
//  Reads the category for each encoded message and calls the appropriate
//  decoding function for that message. The decoded message is then appended 
//  to the decoded packet. 
// 
//  Decoding function first checks for the version it got in the header.
//  If the version is not is  not equal to   2 , it assumes that its a 
//  version 1 message. If version is equal to 2, it starts decoding from
//  the offset starting from size of total size of offsets else offset 
//  is default set to 0. The version follows after SOH
//
//  Header for Version 2 
//   - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
//  | SOH(1B) | VERSION(1B) | SEQ NO(10B) | NO OF MSGS(3B) |LENGTH(1B) |
//   - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
//
//  Header for Version 1 
//   - - - - - - - - - - -
//  | SOH(1B) | LENGTH(1B)|
//   - - - - - - - - - - -
///////////////////////////////////////////////////////////////////////////


int decode(Fast* fast, unsigned char* decoded_packet,
        unsigned char* encoded_packet, unsigned int rec_len)
{
    int            esize     = 0;
    char           category  = 0;
    char           soh       = 0;
    int            read_len  = rec_len;
	unsigned char  msize     = 0;
    OpraMsg_v2     msg_v2    = {'\0'};
    unsigned char  offset    = 0;
    ushort         version   = VERSION_1; // default version

    char  decoded_msg[PACKET_SIZE+1]  = {'\0'};
    unsigned int   packet_len = 0;

    // Strip off the SOH from Encoded packet.
    encoded_packet++;

    // Add SOH as the first byte of the decoded packet and
    // then decode each message from encoded packet and append
    // to the decoded packet to get the final ascii OPRA packet
    decoded_packet[0] = SOH;
	read_len--;	// read SOH


	//Note: As in version 1, the 1st byte is msg length which is
	// 		never less than 10
	//get the version number from 2nd byte of the packet
    if(encoded_packet[offset] == VERSION_2)
    {
        //get the version
        version = encoded_packet[offset];

        //change the offset if version is equal to versionSION_2
		offset = SEQ_OFFSET + VERSION_OFFSET + NUM_MSG_OFFSET;
    }
	else
	{
		printf("WARNING : Header not  in opra for symbology format \n");

	}

    // Be in loop till all messages are read
    while(read_len > 1) //last byte is ETX
    {
        // Each message has 1 bytes for size at the begining 
        // of the message
    
	    msize = *(encoded_packet + offset);
    	encoded_packet += offset + LEN_OFFSET;

        // read msg len byte
	    read_len = read_len - offset - LEN_OFFSET;  

        //reset the offset to ZERO.
        offset=0;

        // if the size field contains 0xFF then it is the last 
        // message in the packet so decode till the end of packet
        // i.e. ETX else decode for the message size 
		if (msize == 0xFF)
			fast->setBuffer(fast, encoded_packet, rec_len);
		else
    		// initialize the fast buffer with encoded packet 
    		fast->setBuffer(fast, encoded_packet, msize);

        // Test if more messages are pending in the encoded packet
        if(fast->decode_new_msg(fast, OPRA_BASE_TID) < 0)
            break;

		/*
		#	 FAST VERSION is set initially at with packet,
		#	 does not change with individual messages like
		#	 TEMPLATE ID
		*/

        if(version == VERSION_2)
        {
             //version 2 decoding.

			 category = fast->decode_u32(fast, MESSAGE_CATEGORY_V2);
			 // Read the category and call appropriate decoding function 
			 switch(category)
			 {
				case 'k':
                    // decode category k message
					esize = decode_OpraFastQuoteSizeMsg_v2(fast, decoded_msg, 
						&(msg_v2.quoteSizeBody));
					break;
				case 'd':
                    // decode category d message
					esize = decode_OpraFastOpenIntMsg_v2(fast, decoded_msg, 
						&(msg_v2.openIntBody));
					break;
				case 'a':
                    // decode category a message
					esize = decode_OpraFastLastSaleMsg_v2(fast, decoded_msg, 
						&(msg_v2.lastSaleBody));
					break;
				case 'f':
                    // decode category f message
					esize = decode_OpraFastEodMsg_v2(fast, decoded_msg, 
						&(msg_v2.eodBody));
					break;
				case 'Y':
                    // decode category Y message
					esize = decode_OpraFastUlValueMsg_v2(fast, decoded_msg, 
						&(msg_v2.ulValueMsg));
					break;
				case 'C':
                    // decode category C message
					esize = decode_OpraFastAdminMsg_v2(fast, decoded_msg, 
						&(msg_v2.adminMsg));
					break;
				case 'H':
                    // decode category H message
					esize = decode_OpraFastControlMsg_v2(fast, decoded_msg, 
						&(msg_v2.ctrlMsg));
					break;
				default:
                    // decode default  message
					esize = decode_OpraFastDefMsg_v2(fast, decoded_msg, 
						&(msg_v2.defMsg));
			}
        }

        // appends the decoded message to the decoded packet
        packet_len = strlen((const char*)decoded_packet);
        memcpy((char*)(decoded_packet + packet_len), (char*)decoded_msg, esize);
        decoded_packet[packet_len+esize] = US;

        if (fast->decode_end_msg(fast, OPRA_BASE_TID) < 0)
            break;
 
		encoded_packet += msize;
        
        // length of encoded packet left to be decoded
		read_len -= msize;
    }

    // For adding ETX for last message in packet
    decoded_packet[strlen((char*)decoded_packet)-1] = ETX;
 
    // reset the fast buffer 
    fast->reset(fast);

    return strlen((char*)decoded_packet);
}
