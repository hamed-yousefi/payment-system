/*
 * Copyright (c) 2019 TRIALBLAZE PTY. LTD. All rights reserved.
 *
 * Created by IntelliJ IDEA.
 * @author: Hamed Yousefi <hamed.yousefi@trialblaze.com>
 * @since: 1/14/21
 */
package com.farazpardazan.paymentgateway.service.bo;

import com.farazpardazan.paymentgateway.db.model.Card;
import com.farazpardazan.paymentgateway.utils.enums.PaymentProviderType;

public interface CardBO {

    Card getCard();

    boolean isCardValid();

    PaymentProviderType getPaymentProviderType();
}
