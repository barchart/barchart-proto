################################################################################
#
#  Copyright (c) :	SIAC 2007
#  Date          :	Jan 15, 2008
#  File			 :	readme.txt
#				 :  FAST for OPRA V2	
#  Note:	This decoder supports both version of FAST for OPRA.  
#			V1			: released on Mar 26 2007
#			V2			: released on Jan 15 2008
#
#  V2 Decode U64    :   This enables the decoder to decode the sequence 
#						number greater than 4 bytes, ie. max 5 bytes. 
#						This enables  read sequence number till 9999999999.
#						Also it compiles for  32bit and 64bit   .
#
#  History:
#			V2 decode 64: released on Dec 01 2008
#  There is no phase 2 anymore.
#  Version 1 support removed.
#  FCO support removed, all FCO messages are encoded as default.
#  decoding buffer size changed to 10*1024 from 1024 bytes 
#
################################################################################


This package contains source code and test data files for the Opra FAST Specification.  
The code and sample programs have been tested on HP-UX using aCC and Linux
using G++. 

NOTE: Please use specific compiler for operating system other than those mentioned above. 

The encoded file contains encoded packet size followed by encoded packet. Each message 
in the encoded packet contains the message size. The message size is the first field
(1 byte) of the encoded message. This will help to skip to the next messsge in the packet,
if needed. The UNIT SEPARATOR is not sent in each message on the encoded packet.  

SIAC provided OPRA FAST deocder will append UNIT SEPARATOR at the end of each message to comply
with OPRA specification.

The FASTforOPRA_Decode_V2.tar.gz contains following files

   - readme.txt   			# Explanation of package content and usage
   - Makefile				# Makefile used to compile and link software (HP-UX and LINUX)
   - types.h				# header file
   - common.h				# header file
   - opra_fast.h			# Definition of opra messages
   - fast_process.h			# Header for decode API
   - fast_decode.h			# Header for decode APIs
   - fast_wrapper.h			# Declaration of fast_wrapper structure
   - fast_api.h				# FAST API definition
   - fast_main.c			# Main function is written to simulate the UDP packet
   - fast_process.c			# definition of the main decode function
   - fast_wrapper.c			# Function definitions to interact with the FAST CODEC
   - fast_decode.c			# Function definitions to decode various fields of the encoded OPRA message.
   - fast_api.c				# FAST CODEC implementation
   - testmsgs.base_v2       # Ascii OPRA file for version 2
   - testmsgs.encoded_v2    # Encoded file for version 2
  

COMPILATION:
   Build the executable using the following command for HP-UX and LINUX OS:

   make -f Makefile
 
   This will create executable with name "opra_c_decoder".
   
   NOTE:	Currently, the makefile supports "g++" and "aCC" compilers. 
   			Please uncomment the required compilation statement inside 
   			the makefile. Also, please feel free to edit the makefile to add new 
   			compiler if neither "g++" nor "aCC" is supported.


TESTING:
   To test, compile the source code with the DEBUG flag ON
   and run the executable as follows:

   		<executableName> <encodedfile> <decodefile>
   	e.g. for version 2  opra_c_decoder testmsgs.encoded_v2 decoded.new.packet
    Where:
         opra_c_decoder         -> executable name
         testmsg.encoded_v2     -> encoded test file for version 2 (provided in the package)
         decoded.new.packet     -> output OPRA ASCII packet file
      
   After runing the executable, a new ouput file "testmsg.encode_v2_decoded"
    would be generated.

   To verify the test results please run the diff command as follows:


   To verify the version 2 messages use the following diff command.
       			diff testmsgs.base_v2 testmsgs.encoded_v2_decoded

   There should be no difference between the two files for both versions , whichever is used .
   This test would ensure that the executable is not corrupted and is runing as expected.

   Always make sure that proper version files are considered for finding the difference.

NOTE: The encoded file is for the format length of binary data followed by actual binary
      data. The test program is made to work in the same way. But in actual production, 
      there will be  length field in the  host order. 

