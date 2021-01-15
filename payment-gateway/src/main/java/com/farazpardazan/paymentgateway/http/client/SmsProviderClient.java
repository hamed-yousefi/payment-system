/*
 * Copyright (c) 2019 TRIALBLAZE PTY. LTD. All rights reserved.
 *
 * Created by IntelliJ IDEA.
 * @author: Hamed Yousefi <hamed.yousefi@trialblaze.com>
 * @since: 1/14/21
 */
package com.farazpardazan.paymentgateway.http.client;

import com.farazpardazan.paymentgateway.configuration.PaymentProviderConfiguration;
import com.farazpardazan.paymentgateway.configuration.SmsProviderConfiguration;
import com.farazpardazan.paymentgateway.http.model.response.PaymentProviderTransferResponse;
import com.farazpardazan.paymentgateway.http.model.response.SendSMSResponse;
import com.farazpardazan.paymentgateway.http.model.response.enums.Status;
import com.farazpardazan.paymentgateway.service.bo.CardTransferBO;
import com.farazpardazan.paymentgateway.utils.pulsar.event.SmsEvent;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.OffsetDateTime;

@Component
public class SmsProviderClient {

    private static final Logger logger = LoggerFactory.getLogger(SmsProviderClient.class);

    private LoadBalancerClient client;

    private SmsProviderConfiguration config;

    @Autowired
    public SmsProviderClient(
            LoadBalancerClient client,
            SmsProviderConfiguration config) {
        this.client = client;
        this.config = config;
    }

    @HystrixCommand(fallbackMethod = "remoteServerFailure")
    public SendSMSResponse cardTransfer(SmsEvent event) {

        ServiceInstance serviceInstance = client.choose(config.getServiceName());
        String url = serviceInstance.getUri() + config.getUri();

        RestTemplate rest = new RestTemplate();
        ResponseEntity<SendSMSResponse> response =
                rest.postForEntity(
                        url,
                        event,
                        SendSMSResponse.class);

        if (response.getBody() != null) {
            logger.info(url + " -> status: " +response.getStatusCode().toString());
        }

        return response.getBody();
    }

    public SendSMSResponse remoteServerFailure(SmsEvent event) {
        return new SendSMSResponse(
                String.format("Service '%s' is unreachable!", config.getServiceName()),
                Status.FAILED,
                OffsetDateTime.now()
        );
    }
}
