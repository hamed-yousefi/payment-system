/*
 * Copyright (c) 2019 TRIALBLAZE PTY. LTD. All rights reserved.
 *
 * Created by IntelliJ IDEA.
 * @author: Hamed Yousefi <hamed.yousefi@trialblaze.com>
 * @since: 1/14/21
 */
package com.farazpardazan.paymentgateway.http.api;

import com.farazpardazan.paymentgateway.db.model.Card;
import com.farazpardazan.paymentgateway.http.model.request.AddCardRequest;
import com.farazpardazan.paymentgateway.http.model.request.CardTransferRequest;
import com.farazpardazan.paymentgateway.http.model.request.ListUserCardsRequest;
import com.farazpardazan.paymentgateway.http.model.response.AddCardResponse;
import com.farazpardazan.paymentgateway.http.model.response.PaymentProviderTransferResponse;
import com.farazpardazan.paymentgateway.service.CardService;
import com.farazpardazan.paymentgateway.service.bo.CardBOImpl;
import com.farazpardazan.paymentgateway.service.bo.CardTransferBOImpl;
import com.farazpardazan.paymentgateway.service.exception.CardIsNotValidException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

import static com.farazpardazan.paymentgateway.http.model.response.enums.Status.FAILED;
import static com.farazpardazan.paymentgateway.http.model.response.enums.Status.SUCCESSFUL;

@RestController
@RequestMapping("/card")
public class CardController {

    private static final Logger logger = LoggerFactory.getLogger(CardController.class);

    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping(value = "/add",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<AddCardResponse> addCard(@RequestBody AddCardRequest request) {
        logger.info(request.toString());

        UUID cardId = null;
        try {
            cardId = cardService.addCard(
                    CardBOImpl.Builder.getInstance()
                            .setNumber(request.getNumber())
                            .setCvv2(request.getCvv2())
                            .setExpire(request.getExpire())
                            .setUserId(request.getUserId())
                            .setCreatedBy(request.getUserId())
                            .build()
            );

            return ResponseEntity.ok(new AddCardResponse(SUCCESSFUL, SUCCESSFUL.getDefaultMessage(), cardId.toString()));
        } catch (CardIsNotValidException e) {
            return ResponseEntity.badRequest().body(new AddCardResponse(FAILED, e.getMessage(), ""));
        }
    }

    @PostMapping(value = "/list",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<Card>> addCard(@RequestBody ListUserCardsRequest request) {
        logger.info(request.toString());
        return ResponseEntity.ok(cardService.listUserCards(request.getUserId()));
    }

    @PostMapping(value = "/transfer",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<PaymentProviderTransferResponse> transfer(@RequestBody CardTransferRequest request,
                                                                    HttpServletRequest httpRequest) {
        logger.info(request.toString());

        PaymentProviderTransferResponse response;
        try {
            response = cardService.CardTransfer(
                    CardTransferBOImpl.Builder.getInstance()
                            .setSourceNumber(request.getSource())
                            .setCvv2(request.getCvv2())
                            .setExpire(request.getExpire())
                            .setPin(request.getPin2())
                            .setAmount(request.getAmount())
                            .setTargetNumber(request.getTarget())
                            .build(),
                    request.getUserId(),
                    request.getClientVersion(),
                    httpRequest.getRemoteAddr()
            );

            return ResponseEntity.ok(response);
        } catch (CardIsNotValidException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
