/*
 * Copyright (c) 2019 TRIALBLAZE PTY. LTD. All rights reserved.
 *
 * Created by IntelliJ IDEA.
 * @author: Hamed Yousefi <hamed.yousefi@trialblaze.com>
 * @since: 1/13/21
 */
package com.farazpardazan.paymentprovider2.http.model.response;

import java.time.OffsetDateTime;

public class CardTransferResponse {

    public static final String PROVIDER = "Payment Provider 2";
    public static final String SUCCESS_MESSAGE = "%s transferred successfully from %s to %s";

    private final String provider;
    private final String message;
    private final Status status;
    private final OffsetDateTime issuedDate;

    public CardTransferResponse(String message, Status status, OffsetDateTime issuedDate) {
        this.message = message;
        this.status = status;
        this.issuedDate = issuedDate;
        this.provider = PROVIDER;
    }

    public String getProvider() {
        return provider;
    }

    public String getMessage() {
        return message;
    }

    public Status getStatus() {
        return status;
    }

    public OffsetDateTime getIssuedDate() {
        return issuedDate;
    }

    @Override
    public String toString() {
        return "CardTransferResponse{" +
                "provider='" + provider + '\'' +
                ", message='" + message + '\'' +
                ", status=" + status +
                ", issuedDate=" + issuedDate +
                '}';
    }
}
