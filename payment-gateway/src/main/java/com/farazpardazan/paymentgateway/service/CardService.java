/*
 * Copyright (c) 2019 TRIALBLAZE PTY. LTD. All rights reserved.
 *
 * Created by IntelliJ IDEA.
 * @author: Hamed Yousefi <hamed.yousefi@trialblaze.com>
 * @since: 1/14/21
 */
package com.farazpardazan.paymentgateway.service;

import com.farazpardazan.paymentgateway.db.model.Card;
import com.farazpardazan.paymentgateway.http.model.response.PaymentProviderTransferResponse;
import com.farazpardazan.paymentgateway.service.bo.CardBO;
import com.farazpardazan.paymentgateway.service.bo.CardTransferBO;
import com.farazpardazan.paymentgateway.service.exception.CardIsNotValidException;

import java.util.List;
import java.util.UUID;

public interface CardService {

    UUID addCard(CardBO card) throws CardIsNotValidException;

    List<Card> listUserCards(UUID userId);

    PaymentProviderTransferResponse CardTransfer(
            CardTransferBO cardTransfer,UUID userId, String clientVersion, String ipAddress
    ) throws CardIsNotValidException;
}
