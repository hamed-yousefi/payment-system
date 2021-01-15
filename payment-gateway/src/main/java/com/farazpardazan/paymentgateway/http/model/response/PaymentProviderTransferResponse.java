/*
 * Copyright (c) 2019 TRIALBLAZE PTY. LTD. All rights reserved.
 *
 * Created by IntelliJ IDEA.
 * @author: Hamed Yousefi <hamed.yousefi@trialblaze.com>
 * @since: 1/13/21
 */
package com.farazpardazan.paymentgateway.http.model.response;

import com.farazpardazan.paymentgateway.http.model.response.enums.Status;

import java.time.OffsetDateTime;

public class PaymentProviderTransferResponse {

    private String provider;
    private String message;
    private Status status;
    private OffsetDateTime issuedDate;

    public PaymentProviderTransferResponse() {
    }

    private PaymentProviderTransferResponse(String message, Status status, String provider, OffsetDateTime issuedDate) {
        this.message = message;
        this.status = status;
        this.issuedDate = issuedDate;
        this.provider = provider;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public OffsetDateTime getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(OffsetDateTime issuedDate) {
        this.issuedDate = issuedDate;
    }

    @Override
    public String toString() {
        return "{" +
                "provider='" + provider + '\'' +
                ", message='" + message + '\'' +
                ", status=" + status +
                ", issuedDate=" + issuedDate +
                '}';
    }

    public static class Builder {

        private String provider;
        private String message;
        private Status status;
        private OffsetDateTime issuedDate;

        private Builder() {
        }

        public static Builder getInstance() {
            return new Builder();
        }

        public Builder setProvider(String provider) {
            this.provider = provider;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setStatus(Status status) {
            this.status = status;
            return this;
        }

        public Builder setIssuedDate(OffsetDateTime issuedDate) {
            this.issuedDate = issuedDate;
            return this;
        }

        public PaymentProviderTransferResponse build() {
            return new PaymentProviderTransferResponse(this.message, this.status, this.provider, this.issuedDate);
        }
    }
}
