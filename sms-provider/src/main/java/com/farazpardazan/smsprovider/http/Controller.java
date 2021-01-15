/*
 * Copyright (c) 2019 TRIALBLAZE PTY. LTD. All rights reserved.
 *
 * Created by IntelliJ IDEA.
 * @author: Hamed Yousefi <hamed.yousefi@trialblaze.com>
 * @since: 1/13/21
 */
package com.farazpardazan.smsprovider.http;

import com.farazpardazan.smsprovider.http.model.request.SendSMSRequest;
import com.farazpardazan.smsprovider.http.model.response.SendSMSResponse;
import com.farazpardazan.smsprovider.http.model.response.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;

@RestController
@RequestMapping("/messages")
public class Controller {

    private static final Logger logger = LoggerFactory.getLogger(Controller.class);

    @PostMapping(value = "/send-sms",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<SendSMSResponse> sendSMS(@RequestBody SendSMSRequest request){

        logger.info(request.toString());

        return ResponseEntity.ok(new SendSMSResponse(SendSMSResponse.SUCCESS_MESSAGE,Status.SUCCESSFUL, OffsetDateTime.now()));
    }
}
