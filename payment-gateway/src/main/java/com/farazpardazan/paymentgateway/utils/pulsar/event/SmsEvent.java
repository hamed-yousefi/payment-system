/*
 * Copyright (c) 2019 TRIALBLAZE PTY. LTD. All rights reserved.
 *
 * Created by IntelliJ IDEA.
 * @author: Hamed Yousefi <hamed.yousefi@trialblaze.com>
 * @since: 1/15/21
 */
package com.farazpardazan.paymentgateway.utils.pulsar.event;

public class SmsEvent {

    private String mobileNo;
    private String message;

    public SmsEvent() {
    }

    public SmsEvent(String phoneNumber, String message) {
        this.mobileNo = phoneNumber;
        this.message = message;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "PulsarEvent{" +
                "mobileNo='" + mobileNo + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

}
