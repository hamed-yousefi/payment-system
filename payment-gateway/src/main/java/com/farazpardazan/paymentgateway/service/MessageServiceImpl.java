/*
 * Copyright (c) 2019 TRIALBLAZE PTY. LTD. All rights reserved.
 *
 * Created by IntelliJ IDEA.
 * @author: Hamed Yousefi <hamed.yousefi@trialblaze.com>
 * @since: 1/15/21
 */
package com.farazpardazan.paymentgateway.service;

import com.farazpardazan.paymentgateway.db.model.UserInfo;
import com.farazpardazan.paymentgateway.db.repository.UserInfoRepository;
import com.farazpardazan.paymentgateway.http.client.SmsProviderClient;
import com.farazpardazan.paymentgateway.http.model.response.SendSMSResponse;
import com.farazpardazan.paymentgateway.service.factory.PulsarProducerFactory;
import com.farazpardazan.paymentgateway.utils.pulsar.event.SmsEvent;
import io.github.majusko.pulsar.annotation.PulsarConsumer;
import io.github.majusko.pulsar.producer.PulsarTemplate;
import org.apache.pulsar.client.api.PulsarClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class MessageServiceImpl implements MessageService{

    private final static Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);

    private final static String USER_NOT_FOUND = "user does not exist.";

    @Value(PulsarProducerFactory.smsTopic)
    private String topic;

    private final UserInfoRepository userInfoRepository;

    private final PulsarTemplate<SmsEvent> producer;

    private final SmsProviderClient client;

    @Autowired
    public MessageServiceImpl(UserInfoRepository userInfoRepository, PulsarTemplate<SmsEvent> producer, SmsProviderClient client) {
        this.userInfoRepository = userInfoRepository;
        this.producer = producer;
        this.client = client;
    }

//    @Async
    @Override
    @Transactional
    public void messageProducer(UUID userId, String message) {
        Optional<UserInfo> userInfo = userInfoRepository.findById(userId);
        if (!userInfo.isPresent()) {
            logger.error(USER_NOT_FOUND);
            throw new RuntimeException();
        }

        try {
            producer.send(topic, new SmsEvent(userInfo.get().getPhoneNumber(), message));
        } catch (PulsarClientException e) {
            e.printStackTrace();
        }
    }

    @Override
    @PulsarConsumer(topic=PulsarProducerFactory.smsTopic, clazz=SmsEvent.class)
    public void messageConsumer(SmsEvent event) {

        // TODO we should store message keys in database

        logger.info(event.toString());

        // Send sms using sms provider api
        SendSMSResponse response = client.cardTransfer(event);

        logger.info(response.toString());
    }


}
