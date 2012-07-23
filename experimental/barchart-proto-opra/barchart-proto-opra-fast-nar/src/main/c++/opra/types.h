// $Id: types.h,v 1.2 2006/02/09 19:14:25 Daniel.May Exp $
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

#ifndef _types_h_
#define _types_h_ 

#include <assert.h>
#include <ctype.h>
#include <errno.h>
#include <stdio.h>
#include <stdlib.h>
#include <stdarg.h>
#include <string.h>
#include <stddef.h>

#if defined (WIN32)
#include <io.h>
#include <fcntl.h>
#include <winsock2.h>
#include <sys/stat.h>
#else
#include <unistd.h>
#include <fcntl.h>

#include <netinet/in.h>
#endif

#if ! defined (CYGWIN) && ! defined (WIN32)
#include <dlfcn.h>
#endif

                                                     
#	include <stddef.h>

#	include <limits.h>

#	if (UCHAR_MAX == 0xff)
		typedef unsigned char  u8;
#	else
#		error: Please define unsigned 8 bit type as u8
#	endif

#	if (USHRT_MAX == 0xffff)
		typedef unsigned short u16;
#	else
#		error: Please define unsigned 16 bit type as u16
#	endif

#	if (INT_MAX == 0x7fffffff)
		typedef int i32;
#	else
#		error: Pleaset define signed 32 bit type as i32
#   endif

#	if (UINT_MAX == 0xffffffff)
		typedef unsigned int u32;
#	else
#		error: Please define unsigned 32 bit type as u32
#   endif

/* The <limits.h> files in some gcc versions don't define LLONG_MIN,
   LLONG_MAX, and ULLONG_MAX.  Instead only the values gcc defined for
   ages are available.  
*/

#	if (LONG_MAX == 0x7fffffffffffffff)
		typedef long i64;
#	elif (LONG_MAX == 0x7fffffff)
		typedef long long int i64;
#	else
#		error: Please define signed 64 bit type as i64
#   endif

#	if (ULONG_MAX == 0xffffffffffffffff)
		typedef unsigned long u64;
#	elif (ULONG_MAX == 0xffffffff)
		typedef unsigned long long u64;
#	else
#		error: Please define unsigned 64 bit type as u64
#   endif


#if 0
typedef int i32;

typedef unsigned char  u8;
typedef unsigned short u16;
typedef unsigned int   u32;

typedef long long i64;
typedef unsigned long long u64;
#endif

typedef double f64;

#define FUNCTION __FUNCTION__

#if defined (WIN32)
#define inline __inline
#endif

#define swap32(x) (x) = ntohl (x)
#define swap16(x) (x) = ntohs (x)

#endif // _types_h_
