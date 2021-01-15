/*
 * Copyright (c) 2019 TRIALBLAZE PTY. LTD. All rights reserved.
 *
 * Created by IntelliJ IDEA.
 * @author: Hamed Yousefi <hamed.yousefi@trialblaze.com>
 * @since: 1/14/21
 */
package com.farazpardazan.paymentgateway.service.bo;

import com.farazpardazan.paymentgateway.utils.enums.PaymentProviderType;

public interface CardTransferBO {

    boolean isInformationValid();

    PaymentProviderType getSourcePaymentProviderType();

    <T> String getInfoJsonStringWithResponse(T response);

    String getInfoJsonString();

    String toString();
}
