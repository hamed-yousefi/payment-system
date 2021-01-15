/*
 * Copyright (c) 2019 TRIALBLAZE PTY. LTD. All rights reserved.
 *
 * Created by IntelliJ IDEA.
 * @author: Hamed Yousefi <hamed.yousefi@trialblaze.com>
 * @since: 1/15/21
 */
package com.farazpardazan.paymentgateway.service;

import com.farazpardazan.paymentgateway.utils.pulsar.event.SmsEvent;

import java.util.UUID;

public interface MessageService {

    void messageProducer(UUID userId, String message);

    void messageConsumer(SmsEvent event);

}
