/*
 * Copyright (c) 2019 TRIALBLAZE PTY. LTD. All rights reserved.
 *
 * Created by IntelliJ IDEA.
 * @author: Hamed Yousefi <hamed.yousefi@trialblaze.com>
 * @since: 1/14/21
 */
package com.farazpardazan.paymentgateway.http.model.request;

import java.util.UUID;

public class CardTransferRequest {

    private String source;

    private String cvv2;

    private String expire;

    private String pin2;

    private String target;

    private String amount;

    private UUID userId;

    private String clientVersion;

    public CardTransferRequest() {
    }

    public CardTransferRequest(String source, String cvv2, String expire, String pin2, String target, String amount, UUID userId, String clientVersion) {
        this.source = source;
        this.cvv2 = cvv2;
        this.expire = expire;
        this.pin2 = pin2;
        this.target = target;
        this.amount = amount;
        this.userId = userId;
        this.clientVersion = clientVersion;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCvv2() {
        return cvv2;
    }

    public void setCvv2(String cvv2) {
        this.cvv2 = cvv2;
    }

    public String getExpire() {
        return expire;
    }

    public void setExpire(String expire) {
        this.expire = expire;
    }

    public String getPin2() {
        return pin2;
    }

    public void setPin2(String pin2) {
        this.pin2 = pin2;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getClientVersion() {
        return clientVersion;
    }

    public void setClientVersion(String clientVersion) {
        this.clientVersion = clientVersion;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "CardTransferRequest{" +
                "source='" + source + '\'' +
                ", cvv2='" + cvv2 + '\'' +
                ", expire='" + expire + '\'' +
                ", pin2='" + pin2 + '\'' +
                ", target='" + target + '\'' +
                ", amount='" + amount + '\'' +
                ", userId=" + userId +
                ", clientVersion='" + clientVersion + '\'' +
                '}';
    }
}
