/*
 * Copyright (c) 2019 TRIALBLAZE PTY. LTD. All rights reserved.
 *
 * Created by IntelliJ IDEA.
 * @author: Hamed Yousefi <hamed.yousefi@trialblaze.com>
 * @since: 1/13/21
 */
package com.farazpardazan.smsprovider.http.model.request;

public class SendSMSRequest
{

    private String mobileNo;
    private String message;

    public SendSMSRequest() {
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
        return "SendSMS{" +
                "mobileNo='" + mobileNo + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
