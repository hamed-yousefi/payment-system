/*
 * Copyright (c) 2019 TRIALBLAZE PTY. LTD. All rights reserved.
 *
 * Created by IntelliJ IDEA.
 * @author: Hamed Yousefi <hamed.yousefi@trialblaze.com>
 * @since: 1/14/21
 */
package com.farazpardazan.paymentgateway.http.model.request;

import java.util.UUID;

public class ListUserCardsRequest {

    // TODO userId must be extracted from JWT token that is in request http header,
    //  for this demo that part has been skipped
    private UUID userId;

    public ListUserCardsRequest() {
    }

    public ListUserCardsRequest(UUID userId) {
        this.userId = userId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "ListUserCardsRequest{" +
                "userId='" + userId + '\'' +
                '}';
    }
}
