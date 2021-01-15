/*
 * Copyright (c) 2019 TRIALBLAZE PTY. LTD. All rights reserved.
 *
 * Created by IntelliJ IDEA.
 * @author: Hamed Yousefi <hamed.yousefi@trialblaze.com>
 * @since: 1/13/21
 */
package com.farazpardazan.paymentprovider2.http.model.request;

public class CardTransfer
{

    private String source;
    private String target;
    private String cvv2;
    private String expire;
    private String pin2;
    private String amount;

    public CardTransfer() {
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "CardTransfer{" +
                "sourcePan='" + source + '\'' +
                ", targetPan='" + target + '\'' +
                ", cvv2='" + cvv2 + '\'' +
                ", expire='" + expire + '\'' +
                ", pin2='" + pin2 + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }
}
