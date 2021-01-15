/*
 * Copyright (c) 2019 TRIALBLAZE PTY. LTD. All rights reserved.
 *
 * Created by IntelliJ IDEA.
 * @author: Hamed Yousefi <hamed.yousefi@trialblaze.com>
 * @since: 1/15/21
 */
package com.farazpardazan.paymentgateway.service.factory;

import com.farazpardazan.paymentgateway.utils.pulsar.event.SmsEvent;
import io.github.majusko.pulsar.producer.ProducerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PulsarProducerFactory {

    public final static String smsTopic = "${pulsar.topic.smsTopic}";

    @Value(PulsarProducerFactory.smsTopic)
    private String topic;

    @Bean
    public ProducerFactory producerFactory() {
        return new ProducerFactory()
                .addProducer(topic, SmsEvent.class);
    }
}
