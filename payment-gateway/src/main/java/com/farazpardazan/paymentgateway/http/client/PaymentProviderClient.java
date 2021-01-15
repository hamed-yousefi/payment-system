/*
 * Copyright (c) 2019 TRIALBLAZE PTY. LTD. All rights reserved.
 *
 * Created by IntelliJ IDEA.
 * @author: Hamed Yousefi <hamed.yousefi@trialblaze.com>
 * @since: 1/14/21
 */
package com.farazpardazan.paymentgateway.http.client;

import com.farazpardazan.paymentgateway.configuration.PaymentProviderConfiguration;
import com.farazpardazan.paymentgateway.http.model.response.PaymentProviderTransferResponse;
import com.farazpardazan.paymentgateway.http.model.response.enums.Status;
import com.farazpardazan.paymentgateway.service.CardServiceImpl;
import com.farazpardazan.paymentgateway.service.bo.CardTransferBO;
import com.farazpardazan.paymentgateway.utils.enums.PaymentProviderType;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.OffsetDateTime;

import static com.farazpardazan.paymentgateway.utils.enums.PaymentProviderType.PAYMENT_PROVIDER_1;
import static com.farazpardazan.paymentgateway.utils.enums.PaymentProviderType.PAYMENT_PROVIDER_2;

@Component
public class PaymentProviderClient {

    private static final Logger logger = LoggerFactory.getLogger(PaymentProviderClient.class);

    private LoadBalancerClient client;

    private PaymentProviderConfiguration config;

    @Autowired
    public PaymentProviderClient(
            LoadBalancerClient client,
            PaymentProviderConfiguration config) {
        this.client = client;
        this.config = config;
    }

    @HystrixCommand(fallbackMethod = "remoteServerFailure")
    public PaymentProviderTransferResponse cardTransfer(CardTransferBO cardTransferBO) {

        ServiceInstance serviceInstance = client.choose(
                config.getProperties(cardTransferBO.getSourcePaymentProviderType()).getServiceName()
        );
        String url = serviceInstance.getUri() + config.getProperties(cardTransferBO.getSourcePaymentProviderType()).getUri();

        RestTemplate rest = new RestTemplate();
        ResponseEntity<PaymentProviderTransferResponse> response =
                rest.postForEntity(
                        url,
                        cardTransferBO,
                        PaymentProviderTransferResponse.class);

        if (response.getBody() != null) {
            logger.info(url + " -> status: " +response.getStatusCode().toString());
        }

        return response.getBody();
    }

    public PaymentProviderTransferResponse remoteServerFailure(CardTransferBO cardTransferBO) {
        return PaymentProviderTransferResponse.Builder.getInstance()
                .setProvider(cardTransferBO.getSourcePaymentProviderType().toString())
                .setStatus(Status.FAILED)
                .setMessage(
                        String.format("Service '%s' is unreachable!",
                                config.getProperties(cardTransferBO.getSourcePaymentProviderType()).getServiceName()))
                .setIssuedDate(OffsetDateTime.now())
                .build();
    }
}
