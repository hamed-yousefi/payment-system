/*
 * Copyright (c) 2019 TRIALBLAZE PTY. LTD. All rights reserved.
 *
 * Created by IntelliJ IDEA.
 * @author: Hamed Yousefi <hamed.yousefi@trialblaze.com>
 * @since: 1/14/21
 */
package com.farazpardazan.paymentgateway.http.model.request;

import java.util.UUID;

public class AddCardRequest {

    private String number;

    private String expire;

    private String cvv2;

    // TODO userId must be extracted from JWT token that is in request http header,
    //  for this demo that part has been skipped
    private UUID userId;

    public AddCardRequest() {
    }

    public AddCardRequest(String number, String expire, String cvv2, UUID userId) {
        this.number = number;
        this.expire = expire;
        this.cvv2 = cvv2;
        this.userId = userId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getExpire() {
        return expire;
    }

    public void setExpire(String expire) {
        this.expire = expire;
    }

    public String getCvv2() {
        return cvv2;
    }

    public void setCvv2(String cvv2) {
        this.cvv2 = cvv2;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "AddCardRequest{" +
                "number='" + number + '\'' +
                ", expire='" + expire + '\'' +
                ", cvv2='" + cvv2 + '\'' +
                ", userId=" + userId +
                '}';
    }
}
