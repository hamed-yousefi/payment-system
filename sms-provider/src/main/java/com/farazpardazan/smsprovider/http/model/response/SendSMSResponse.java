/*
 * Copyright (c) 2019 TRIALBLAZE PTY. LTD. All rights reserved.
 *
 * Created by IntelliJ IDEA.
 * @author: Hamed Yousefi <hamed.yousefi@trialblaze.com>
 * @since: 1/13/21
 */
package com.farazpardazan.smsprovider.http.model.response;

import java.time.OffsetDateTime;

public class SendSMSResponse {

    public static final String SUCCESS_MESSAGE = "SMS has been sent successfully";

    private String message;
    private Status status;
    private OffsetDateTime issuedDate;

    public SendSMSResponse() {
    }

    public SendSMSResponse(String message, Status status, OffsetDateTime issuedDate) {
        this.message = message;
        this.status = status;
        this.issuedDate = issuedDate;
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

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setIssuedDate(OffsetDateTime issuedDate) {
        this.issuedDate = issuedDate;
    }

    @Override
    public String toString() {
        return "SendSMSResponse{" +
                "message='" + message + '\'' +
                ", status=" + status +
                ", issuedDate=" + issuedDate +
                '}';
    }
}
