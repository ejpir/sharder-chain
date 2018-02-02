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
import org.conch.Attachment;
import org.conch.ConchException;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;

public final class SendMessage extends CreateTransaction {

    static final SendMessage instance = new SendMessage();

    private SendMessage() {
        super(new APITag[] {APITag.MESSAGES, APITag.CREATE_TRANSACTION}, "recipient");
    }

    @Override
    protected JSONStreamAware processRequest(HttpServletRequest req) throws ConchException {
        long recipientId = ParameterParser.getAccountId(req, "recipient", false);
        Account account = ParameterParser.getSenderAccount(req);
        return createTransaction(req, account, recipientId, 0, Attachment.ARBITRARY_MESSAGE);
    }

}
