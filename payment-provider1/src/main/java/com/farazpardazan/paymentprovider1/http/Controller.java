/*
 * Copyright (c) 2019 TRIALBLAZE PTY. LTD. All rights reserved.
 *
 * Created by IntelliJ IDEA.
 * @author: Hamed Yousefi <hamed.yousefi@trialblaze.com>
 * @since: 1/13/21
 */
package com.farazpardazan.paymentprovider1.http;

import com.farazpardazan.paymentprovider1.http.model.request.CardTransfer;
import com.farazpardazan.paymentprovider1.http.model.response.CardTransferResponse;
import com.farazpardazan.paymentprovider1.http.model.response.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;

import static com.farazpardazan.paymentprovider1.http.model.response.CardTransferResponse.SUCCESS_MESSAGE;

@RestController
@RequestMapping("/card")
public class Controller {

    private static final Logger logger = LoggerFactory.getLogger(Controller.class);

    @PostMapping(value = "/transfer",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<CardTransferResponse> transfer(@RequestBody CardTransfer body){

        logger.info(body.toString());

        String message = String.format(SUCCESS_MESSAGE, body.getAmount(), body.getSource(), body.getTarget());

        return ResponseEntity.ok(new CardTransferResponse(message, Status.SUCCESSFUL, OffsetDateTime.now()));
    }
}
