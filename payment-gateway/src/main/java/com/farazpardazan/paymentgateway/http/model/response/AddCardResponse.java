/*
 * Copyright (c) 2019 TRIALBLAZE PTY. LTD. All rights reserved.
 *
 * Created by IntelliJ IDEA.
 * @author: Hamed Yousefi <hamed.yousefi@trialblaze.com>
 * @since: 1/14/21
 */
package com.farazpardazan.paymentgateway.http.model.response;

import com.farazpardazan.paymentgateway.http.model.response.enums.Status;

public class AddCardResponse {

    private final Status status;
    private final String message;
    private final String cardId;

    public AddCardResponse(Status status, String message, String cardId) {
        this.status = status;
        this.message = message;
        this.cardId = cardId;
    }

    public Status getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getCardId() {
        return cardId;
    }

    @Override
    public String toString() {
        return "AddCardResponse{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", cardId='" + cardId + '\'' +
                '}';
    }
}
