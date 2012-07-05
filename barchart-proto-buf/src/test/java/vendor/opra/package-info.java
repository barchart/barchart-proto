/**
 * Copyright (C) 2011-2012 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package vendor.opra;

/**
 * 
 * OPRA feed model
 * 
 * rules:
 * 
 * book top has entry.index = 0; non book to has no entry.index
 * 
 * separate instrument definitions processor listens to the same feed and
 * auto-generates market-id & instrument-meta-data
 * 
 * "OPRA appendage" is unwrapped into separate data.entry with market-id
 * generated from participant-id, and option meta-data
 * 
 */

/**
 *
 * OPRA -> proto.buf marketId mapping:
 * 
 * option GUID components:
 * 
 * 		// exchange-id
 * 		PARTICIPANT_ID / BEST_BID_PARTICIPANT_ID / BEST_OFFER_PARTICIPANT_ID
 * 
 * 		// security-id
 * 		SECURITY_SYMBOL
 *  
 * 		// more security-id
 * 		EXPIRATION_DATE
 * 		YEAR
 * 		
 * 		// more security-id
 * 		STRIKE_PRICE_CODE
 * 		STRIKE_PRICE_DENOMINATOR_CODE
 * 		EXPLICIT_STRIKE_PRICE
 * 
 * index GUID components:
 * 
 * 		// exchange-id
 * 		PARTICIPANT_ID / BEST_BID_PARTICIPANT_ID / BEST_OFFER_PARTICIPANT_ID
 * 
 * 		// security-id
 * 		INDEX_SYMBOL
 * 
 */
