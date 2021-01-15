/*
 * Copyright (c) 2019 TRIALBLAZE PTY. LTD. All rights reserved.
 *
 * Created by IntelliJ IDEA.
 * @author: Hamed Yousefi <hamed.yousefi@trialblaze.com>
 * @since: 1/14/21
 */
package com.farazpardazan.paymentgateway.service;

import com.farazpardazan.paymentgateway.configuration.AppConfiguration;
import com.farazpardazan.paymentgateway.db.model.Card;
import com.farazpardazan.paymentgateway.db.model.EventLog;
import com.farazpardazan.paymentgateway.db.model.enums.Event;
import com.farazpardazan.paymentgateway.db.repository.CardRepository;
import com.farazpardazan.paymentgateway.db.repository.EventLogRepository;
import com.farazpardazan.paymentgateway.http.client.PaymentProviderClient;
import com.farazpardazan.paymentgateway.http.model.response.PaymentProviderTransferResponse;
import com.farazpardazan.paymentgateway.service.bo.CardBO;
import com.farazpardazan.paymentgateway.service.bo.CardTransferBO;
import com.farazpardazan.paymentgateway.service.exception.CardIsNotValidException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class CardServiceImpl implements CardService {

    private static final Logger logger = LoggerFactory.getLogger(CardServiceImpl.class);

    private final CardRepository cardRepository;

    private final PaymentProviderClient client;

    private final EventLogRepository eventLogRepository;

    private final AppConfiguration appConfig;

    private final MessageService messageService;

    @Autowired
    public CardServiceImpl(CardRepository cardRepository, PaymentProviderClient client,
                           EventLogRepository eventLogRepository, AppConfiguration appConfig, MessageService messageService) {
        this.cardRepository = cardRepository;
        this.client = client;
        this.eventLogRepository = eventLogRepository;
        this.appConfig = appConfig;
        this.messageService = messageService;
    }


    @Override
    @Transactional
    public UUID addCard(CardBO cardBO) throws CardIsNotValidException {
        if (!cardBO.isCardValid()) {
            logger.error("Card is not valid, " + cardBO.getCard().toString());
            throw new CardIsNotValidException(cardBO.getCard().toString());
        }

        Card card = cardRepository.save(cardBO.getCard());
        return card.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Card> listUserCards(UUID userId) {
        return cardRepository.findCardByUserId(userId);
    }

    @Override
    @Transactional
    public PaymentProviderTransferResponse CardTransfer(
            CardTransferBO cardTransfer, UUID userId, String clientVersion, String ipAddress
    ) throws CardIsNotValidException {

        if (!cardTransfer.isInformationValid()) {
            logger.error("Cards information are not valid, %s" + cardTransfer.toString());
            throw new CardIsNotValidException(cardTransfer.toString());
        }

        // NOTE: regarding Business Object(BO) pattern which we used there's no
        //       need to implement Factory pattern, CardTransferBO knows about
        //       its own identity by the method called getSourcePaymentProviderType.
        //       providers have not much separate logics to use factory, both have
        //       same function with same logic to use.
        PaymentProviderTransferResponse response = client.cardTransfer(cardTransfer);

        logger.info(response.getMessage());

        // store payment transaction into database
        eventLogRepository.save(
                EventLog.Builder.getInstance()
                        .setUserId(userId)
                        .setClientVersion(clientVersion)
                        .setAppVersion(appConfig.getVersion())
                        .setEvent(Event.CARD_TRANSFER)
                        .setInfo(cardTransfer.getInfoJsonStringWithResponse(response))
                        .setIpAddress(ipAddress)
                .build()
        );

        // send sms to user
        messageService.messageProducer(userId, cardTransfer.getInfoJsonStringWithResponse(response));

        return response;
    }
}
