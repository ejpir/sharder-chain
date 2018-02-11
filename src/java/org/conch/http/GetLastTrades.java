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

import org.conch.Trade;
import org.conch.Trade;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public final class GetLastTrades extends APIServlet.APIRequestHandler {

    static final GetLastTrades instance = new GetLastTrades();

    private GetLastTrades() {
        super(new APITag[] {APITag.AE}, "assets", "assets", "assets"); // limit to 3 for testing
    }

    @Override
    protected JSONStreamAware processRequest(HttpServletRequest req) throws ParameterException {
        long[] assetIds = ParameterParser.getUnsignedLongs(req, "assets");
        JSONArray tradesJSON = new JSONArray();
        List<Trade> trades = Trade.getLastTrades(assetIds);
        trades.forEach(trade -> tradesJSON.add(JSONData.trade(trade, false)));
        JSONObject response = new JSONObject();
        response.put("trades", tradesJSON);
        return response;
    }

}
