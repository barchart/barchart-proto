// $Id: common.h,v 1.2 2006/02/09 19:14:24 Daniel.May Exp $
//
// FIX Adapted for STreaming (sm) (FAST Protocol (sm)) 
//
// Copyright (c) 2005-2006, Pantor Engineering AB (http://www.pantor.com)
// Copyright (c) 2005-2006, SpryWare LLC (http://www.spryware.com)
// Copyright (c) 2005-2006, FIX Protocol Ltd (http://www.fixprotocol.org)
// All Rights Reserved.
//
// This work is distributed under the W3C® Software License [1] in the
// hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
// implied warranty of MERCHANTABILITY, SATISFACTORY QUALITY or FITNESS 
// FOR A PARTICULAR PURPOSE.
//
// [1] http://www.w3.org/Consortium/Legal/2002/copyright-software-20021231
// [FPL's Intellectual Property details] http://www.fixprotocol.org/ip
// [FAST Protocol details] http://www.fixprotocol.org/fast
// [FAST Protocol credits] http://fixprotocol.org/fastcredits

#ifndef _common_h_
#define _common_h_ 1

#include "types.h"

#ifndef max
#define max(a,b) ((a) > (b) ? (a) : (b))
#endif

#ifndef min
#define min(a,b) ((a) < (b) ? (a) : (b))
#endif

//////////////////////////////////////////////////////////////////////

#if defined (WIN32)

extern int optind;
extern char *optarg;

int getopt(int, char *const *, const char *);

#define snprintf _snprintf
#define vsnprintf _vsnprintf
#define strcasecmp stricmp

#endif // defined (WIN32)

//static inline u32 align (u32 x, u32 size)
static u32 align (u32 x, u32 size)
{
   return (x + size - 1) / size;
}

//static inline u32 align7 (u32 x) { return align (x, 7); }
static u32 align7 (u32 x) { return align (x, 7); }

//////////////////////////////////////////////////////////////////////

size_t strnlen (const char*, size_t); // missing in string.h

void init_platform_io (void);

int  read_n  (int fd, void* data, int size);
int  write_n (int fd, const void* data, int size);

#endif // _common_h_
