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

import org.conch.Conch;
import org.conch.ConchException;
import org.json.simple.JSONObject;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;

public final class GetAccountBlockCount extends APIServlet.APIRequestHandler {

    static final GetAccountBlockCount instance = new GetAccountBlockCount();

    private GetAccountBlockCount() {
        super(new APITag[] {APITag.ACCOUNTS}, "account");
    }

    @Override
    protected JSONStreamAware processRequest(HttpServletRequest req) throws ConchException {

        long accountId = ParameterParser.getAccountId(req, true);
        JSONObject response = new JSONObject();
        response.put("numberOfBlocks", Conch.getBlockchain().getBlockCount(accountId));

        return response;
    }

}
