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

import org.conch.Account;
import org.conch.ConchException;
import org.conch.db.DbIterator;
import org.conch.util.JSON;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;

public final class GetAccountAssets extends APIServlet.APIRequestHandler {

    static final GetAccountAssets instance = new GetAccountAssets();

    private GetAccountAssets() {
        super(new APITag[] {APITag.ACCOUNTS, APITag.AE}, "account", "asset", "height", "includeAssetInfo");
    }

    @Override
    protected JSONStreamAware processRequest(HttpServletRequest req) throws ConchException {

        long accountId = ParameterParser.getAccountId(req, true);
        int height = ParameterParser.getHeight(req);
        long assetId = ParameterParser.getUnsignedLong(req, "asset", false);
        boolean includeAssetInfo = "true".equalsIgnoreCase(req.getParameter("includeAssetInfo"));

        if (assetId == 0) {
            JSONObject response = new JSONObject();
            try (DbIterator<Account.AccountAsset> accountAssets = Account.getAccountAssets(accountId, height, 0, -1)) {
                JSONArray assetJSON = new JSONArray();
                while (accountAssets.hasNext()) {
                    assetJSON.add(JSONData.accountAsset(accountAssets.next(), false, includeAssetInfo));
                }
                response.put("accountAssets", assetJSON);
                return response;
            }
        } else {
            Account.AccountAsset accountAsset = Account.getAccountAsset(accountId, assetId, height);
            if (accountAsset != null) {
                return JSONData.accountAsset(accountAsset, false, includeAssetInfo);
            }
            return JSON.emptyJSON;
        }
    }

}