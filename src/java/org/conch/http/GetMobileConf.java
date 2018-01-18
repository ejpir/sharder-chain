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

import org.conch.ConchException;
import org.json.simple.JSONObject;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * Special conf for mobile app:
 * 1. hide "send cc" for app store
 * <p>
 */
public final class GetMobileConf extends APIServlet.APIRequestHandler {

    static final GetMobileConf instance = new GetMobileConf();

    private GetMobileConf() {
        super(new APITag[] {APITag.INFO});
    }

    @Override
    protected JSONStreamAware processRequest(HttpServletRequest req) throws ConchException {
        JSONObject response = new JSONObject();
        response.put("hideSendCC", true);
        response.put("hideBeta", true);
        return response;
    }

    /**
     * No required block parameters
     * @return FALSE to disable the required block parameters
     */
    @Override
    protected boolean allowRequiredBlockParameters() {
        return false;
    }
}
