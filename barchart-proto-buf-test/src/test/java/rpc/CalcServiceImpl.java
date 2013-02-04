/**
 * Copyright (C) 2011-2013 Barchart, Inc. <http://www.barchart.com/>
 *
 * All rights reserved. Licensed under the OSI BSD License.
 *
 * http://www.opensource.org/licenses/bsd-license.php
 */
package rpc;

import com.barchart.proto.buf.test.CalcRequest;
import com.barchart.proto.buf.test.CalcResponse;
import com.barchart.proto.buf.test.CalcService;
import com.google.protobuf.RpcCallback;
import com.google.protobuf.RpcController;

public class CalcServiceImpl extends CalcService {

	@Override
	public void add(final RpcController controller, final CalcRequest request,
			final RpcCallback<CalcResponse> done) {
		// TODO Auto-generated method stub

	}

	@Override
	public void subtract(final RpcController controller,
			final CalcRequest request, final RpcCallback<CalcResponse> done) {
		// TODO Auto-generated method stub

	}

	@Override
	public void multiply(final RpcController controller,
			final CalcRequest request, final RpcCallback<CalcResponse> done) {
		// TODO Auto-generated method stub

	}

	@Override
	public void divide(final RpcController controller,
			final CalcRequest request, final RpcCallback<CalcResponse> done) {
		// TODO Auto-generated method stub

	}

}
