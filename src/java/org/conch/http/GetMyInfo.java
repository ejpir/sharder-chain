/*
 * Copyright © 2017 sharder.org.
 * Copyright © 2014-2017 ichaoj.com.
 *
 * See the LICENSE.txt file at the top-level directory of this distribution
 * for licensing information.
 *
 * Unless otherwise agreed in a custom licensing agreement with ichaoj.com,
 * no part of the COS software, including this file, may be copied, modified,
 * propagated, or distributed except according to the terms contained in the
 * LICENSE.txt file.
 *
 * Removal or modification of this copyright notice is prohibited.
 *
 */

package org.conch.http;

import org.json.simple.JSONObject;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;

public final class GetMyInfo extends APIServlet.APIRequestHandler {

    static final GetMyInfo instance = new GetMyInfo();

    private GetMyInfo() {
        super(new APITag[] {APITag.NETWORK});
    }

    @Override
    protected JSONStreamAware processRequest(HttpServletRequest req) {

        JSONObject response = new JSONObject();
        response.put("host", req.getRemoteHost());
        response.put("address", req.getRemoteAddr());
        return response;
    }

    @Override
    protected boolean allowRequiredBlockParameters() {
        return false;
    }

}
