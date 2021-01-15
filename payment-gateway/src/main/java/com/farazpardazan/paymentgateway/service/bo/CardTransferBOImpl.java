/*
 * Copyright (c) 2019 TRIALBLAZE PTY. LTD. All rights reserved.
 *
 * Created by IntelliJ IDEA.
 * @author: Hamed Yousefi <hamed.yousefi@trialblaze.com>
 * @since: 1/14/21
 */
package com.farazpardazan.paymentgateway.service.bo;

import com.farazpardazan.paymentgateway.http.model.response.PaymentProviderTransferResponse;
import com.farazpardazan.paymentgateway.utils.Utils;
import com.farazpardazan.paymentgateway.utils.enums.PaymentProviderType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import static com.farazpardazan.paymentgateway.utils.CardValidation.isExpirationValid;
import static com.farazpardazan.paymentgateway.utils.CardValidation.isNumberValid;
import static com.farazpardazan.paymentgateway.utils.enums.PaymentProviderType.PAYMENT_PROVIDER_1;
import static com.farazpardazan.paymentgateway.utils.enums.PaymentProviderType.PAYMENT_PROVIDER_2;

public class CardTransferBOImpl implements CardTransferBO {

    private final ObjectMapper objectMapper;

    private final String source;
    private final String cvv2;
    private final String expire;
    private final String pin2;
    private final String target;
    private final String amount;

    @JsonProperty(required = true)
    private PaymentProviderTransferResponse response;

    private CardTransferBOImpl(String source, String cvv2, String expire, String pin2, String target, String amount) {
        this.source = source;
        this.cvv2 = cvv2;
        this.expire = expire;
        this.pin2 = pin2;
        this.target = target;
        this.amount = amount;

        objectMapper = Utils.getDefaultObjectMapper();
    }

    public String getSource() {
        return source;
    }

    public String getCvv2() {
        return cvv2;
    }

    public String getExpire() {
        return expire;
    }

    public String getPin2() {
        return pin2;
    }

    public String getTarget() {
        return target;
    }

    public String getAmount() {
        return amount;
    }

    public PaymentProviderTransferResponse getResponse() {
        return response;
    }

    @Override
    public boolean isInformationValid() {
        return !source.equals(target)
                && isNumberValid(source)
                && isExpirationValid(expire)
                && isNumberValid(target);
    }

    @Override
    public PaymentProviderType getSourcePaymentProviderType() {
        if (source.startsWith(PAYMENT_PROVIDER_1.getCode())) {
            return PAYMENT_PROVIDER_1;
        }

        return PAYMENT_PROVIDER_2;
    }

    @Override
    @JsonIgnore
    public <T> String getInfoJsonStringWithResponse(T response) {
        if (response instanceof PaymentProviderTransferResponse) {
            this.response = (PaymentProviderTransferResponse) response;
        }
        return getInfoJsonString();
    }

    @Override
    @JsonIgnore
    public String getInfoJsonString() {
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "{\"parseError\":"+this.toString()+"}";
        }
    }

    @Override
    public String toString() {
        return "{" +
                "source='" + source + '\'' +
                ", cvv2='" + cvv2 + '\'' +
                ", expire='" + expire + '\'' +
                ", pin2='" + pin2 + '\'' +
                ", target='" + target + '\'' +
                ", amount='" + amount + '\'' +
                ", response=" + response.toString() +
                '}';
    }

    public static class Builder {

        private String sourceNumber;
        private String cvv2;
        private String expire;
        private String pin;
        private String targetNumber;
        private String amount;

        private Builder() {
        }

        public static Builder getInstance() {
            return new Builder();
        }

        public Builder setSourceNumber(String sourceNumber) {
            this.sourceNumber = sourceNumber;
            return this;
        }

        public Builder setCvv2(String cvv2) {
            this.cvv2 = cvv2;
            return this;
        }

        public Builder setExpire(String expire) {
            this.expire = expire;
            return this;
        }

        public Builder setPin(String pin) {
            this.pin = pin;
            return this;
        }

        public Builder setTargetNumber(String targetNumber) {
            this.targetNumber = targetNumber;
            return this;
        }

        public Builder setAmount(String amount) {
            this.amount = amount;
            return this;
        }

        public CardTransferBO build() {
            return new CardTransferBOImpl(this.sourceNumber, this.cvv2, this.expire, this.pin, this.targetNumber, this.amount);
        }
    }
}
