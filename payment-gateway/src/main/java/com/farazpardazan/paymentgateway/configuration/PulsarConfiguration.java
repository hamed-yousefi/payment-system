/*
 * Copyright (c) 2019 TRIALBLAZE PTY. LTD. All rights reserved.
 *
 * Created by IntelliJ IDEA.
 * @author: Hamed Yousefi <hamed.yousefi@trialblaze.com>
 * @since: 1/15/21
 */
package com.farazpardazan.paymentgateway.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "pulsar.topic")
public class PulsarConfiguration {

    private String smsTopic;

    public String getSmsTopic() {
        return smsTopic;
    }

    public void setSmsTopic(String smsTopic) {
        this.smsTopic = smsTopic;
    }

}
