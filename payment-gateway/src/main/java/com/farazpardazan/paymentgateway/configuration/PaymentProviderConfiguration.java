/*
 * Copyright (c) 2019 TRIALBLAZE PTY. LTD. All rights reserved.
 *
 * Created by IntelliJ IDEA.
 * @author: Hamed Yousefi <hamed.yousefi@trialblaze.com>
 * @since: 1/15/21
 */
package com.farazpardazan.paymentgateway.configuration;

import com.farazpardazan.paymentgateway.utils.enums.PaymentProviderType;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "payment")
public class PaymentProviderConfiguration {

    private Map<String, Properties> provider;

    public Map<String, Properties> getProvider() {
        return provider;
    }

    public void setProvider(Map<String, Properties> provider) {
        this.provider = provider;
    }

    public Properties getProperties(PaymentProviderType type) {
        return provider.get(type.getConfigKey());
    }

    public static class Properties {

        private String serviceName;
        private String uri;

        public String getServiceName() {
            return serviceName;
        }

        public void setServiceName(String serviceName) {
            this.serviceName = serviceName;
        }

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }

    }
}
