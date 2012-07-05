/*******************************************************************************
*  System        : OPRA
*
*  Module Name   : fast_main.c
*  Copyright (c) : SIAC 2007
*  Date:         : 3/2007
*  
*  Description   : Main function is written to simulate the UDP packet from
*                  a file instead of packet received from multicast
*******************************************************************************/

#include <unistd.h>
#include <fcntl.h>
#include <stdlib.h>
#include "fast_wrapper.h"
#include "opra_fast.h"
#include "fast_process.h"

//////////////////////////////////////////////////////////////////////////////////
//
//  void usage()
//
//  Displays the parameters to the program.
//
/////////////////////////////////////////////////////////////////////////////////

void usage(char** argv)
{
    printf("\n			USAGE :");
    printf(" %s <inputfilename> <packetfilename>\n\n",argv[0]);
    return;
}

/////////////////////////////////////////////////////////////////////////////////
//
//  int initialize(Fast* fast)
//
//  Initializes fast and opens input file to read encoded data and
//  outfile to write decoded messages
//
////////////////////////////////////////////////////////////////////////////////

int initialize(Fast* fast, char* infile, char* packet_file, FILE**fp1, FILE** fp2,char* outfile)
{
    //initialize fast codec
    FILE*       fp  = NULL;
    init_fast(fast);

    if((*fp1 = fopen(infile, "rb"))== NULL)
    {
        printf("Cannot Open file %s\n", infile);
        return 0;
    }
    printf("Reading from %s\n", infile);

    if((*fp2 = fopen(packet_file, "w+")) == NULL)
    {
        printf("Cannot open %s\n", packet_file);
        fclose (*fp1);
        return 0;
    }

    strcat(outfile,infile);;
    strcat(outfile,"_decoded");
    if((fp = fopen(outfile, "w+")) == NULL)
    {
        fprintf(stderr, "Cannot open packet file\n");
        return 0 ; 
    }

    return 1;
}


////////////////////////////////////////////////////////////////////////////////
//
//  void cleanup(Fast* fast)
//
//  closes all the open files and destroys the Fast.
//
////////////////////////////////////////////////////////////////////////////////

void cleanup(Fast* fast, FILE** fp1, FILE** fp2)
{
    destroy_fast(fast);
    fclose(*fp1);
    fclose(*fp2);
}

#if DEBUG
void writeMsgsFromPkt(unsigned char* pkt,char* outfile)
{
    const char* p = (const char*)pkt;
    const char* temp;
    int         done = 0;
    int         len = 0;
    FILE*       fp  = NULL;
    char msg [PACKET_SIZE] = {'\0'};

    ++p;    // skip SOH

    if((fp = fopen(outfile, "a")) == NULL)
    {
        fprintf(stderr, "Cannot open packet file\n");
        return ; 
    }
    
    do
    {
        temp = strchr(p, US); // search for US
        if(!temp)
        {
            len = strchr(p, ETX) - p;    // last or the only msg in pkt
            done = 1;
        }
        else
            len = temp - p;
 
        memcpy(msg, p, len);
        fwrite(msg, 1, len, fp);
        fwrite("\n", 1, 1, fp);
        fflush(fp);
        memset(msg, 0, PACKET_SIZE);
        p += len+1; // skip last US
    }
    while(!done);
    fclose(fp);
}
#endif


////////////////////////////////////////////////////////////////////////////////
//
//  int main(int argc, char** argv)
//
//  Main function.
//  
//  Read Command line arguments. Initializes the fast and I/O. Decodes
//  each packet read from the input file and writes it to the output 
//  packet_file. Calls cleanup in the end to close all the open file
//  and to destroy the Fast.
//
////////////////////////////////////////////////////////////////////////////////


int main(int argc, char** argv)
{
    Fast          fast;           
    unsigned int  nbytes      = 0;
    unsigned int  rec_len     = 0;  
    char*         infile      = {'\0'};
    char*         packet_file = {'\0'};
    FILE*         fp1         = NULL;
    FILE*         fp2         = NULL;
    char          outfile[200] = {'\0'};
    unsigned char decoded_packet[PACKET_SIZE + 1] = {'\0'};
    unsigned char encoded_packet[PACKET_SIZE + 1] = {'\0'};

    memset(&fast, 0, sizeof(Fast));

    if(argc < 3)
    {
        usage(argv);
        return -1;
    }
    infile = argv[1];
    packet_file = argv[2];

    // initialize fast and open files for read and write
    if (!initialize(&fast, infile, packet_file, &fp1, &fp2,outfile))
        return -1;

    // the encoded file contains the  len + encoded_data. This format is
    // for demonstration purpose only. The actual data in  production will
    // contain only the data and would not have the length (here defined as rec_len). 

    // reads the length of encoded packet
    while (fread(&rec_len, sizeof(unsigned int),  1, fp1) != 0) 
    {
        rec_len = ntohl(rec_len);
        memset(encoded_packet, '\0', PACKET_SIZE);
        // reads the actual data of the size rec_len
    	fread(encoded_packet, 1, rec_len, fp1);
        
        // this function takes in encoded packet, decodes the packet and  
        // puts in decoded_packet file. The function returns the number of bytes
        // in decoded packet
        nbytes = decode(&fast, decoded_packet, encoded_packet, rec_len);
        fwrite(decoded_packet, nbytes, 1, fp2);
#if DEBUG
        writeMsgsFromPkt(decoded_packet,outfile);
#endif
        // resets the buffer for next packet
        memset(decoded_packet, 0, PACKET_SIZE);
    } // Per Packet

    // destroy fast codec and close all input/output files
    cleanup(&fast, &fp1, &fp2);

    return 0;
}
