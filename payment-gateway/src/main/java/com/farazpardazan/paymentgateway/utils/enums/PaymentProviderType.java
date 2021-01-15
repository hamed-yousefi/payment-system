/*
 * Copyright (c) 2019 TRIALBLAZE PTY. LTD. All rights reserved.
 *
 * Created by IntelliJ IDEA.
 * @author: Hamed Yousefi <hamed.yousefi@trialblaze.com>
 * @since: 1/14/21
 */
package com.farazpardazan.paymentgateway.utils.enums;

public enum PaymentProviderType {
    PAYMENT_PROVIDER_1("6307", "provider1"),
    PAYMENT_PROVIDER_2("", "provider2");

    private final String code;
    private final String configKey;

    PaymentProviderType(String code, String configKey) {
        this.code = code;
        this.configKey = configKey;
    }

    public String getCode() {
        return code;
    }

    public String getConfigKey() {
        return configKey;
    }
}
