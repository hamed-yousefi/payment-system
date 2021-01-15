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

import java.util.UUID;

import static com.farazpardazan.paymentgateway.utils.CardValidation.isExpirationValid;
import static com.farazpardazan.paymentgateway.utils.CardValidation.isNumberValid;
import static com.farazpardazan.paymentgateway.utils.enums.PaymentProviderType.PAYMENT_PROVIDER_1;
import static com.farazpardazan.paymentgateway.utils.enums.PaymentProviderType.PAYMENT_PROVIDER_2;

public class CardBOImpl implements CardBO {

    private final Card card;

    private CardBOImpl(Card card) {
        this.card = card;
    }

    @Override
    public Card getCard() {
        return card;
    }

    @Override
    public boolean isCardValid() {
        // TODO this validation also can be handled by bean validation annotation
        return isNumberValid(card.getNumber())
                && isExpirationValid(card.getExpire());
    }

    @Override
    public PaymentProviderType getPaymentProviderType() {
        if (card.getNumber().startsWith(PAYMENT_PROVIDER_1.getCode())) {
            return PAYMENT_PROVIDER_1;
        }

        return PAYMENT_PROVIDER_2;
    }

    public static class Builder {

        private final Card.Builder cardBuilder;

        private Builder(Card.Builder cardBuilder) {
            this.cardBuilder = cardBuilder;
        }

        public static Builder getInstance() {
            return new Builder(Card.Builder.getInstance());
        }

        public Builder setNumber(String number) {
            this.cardBuilder.setNumber(number);
            return this;
        }

        public Builder setExpire(String expire) {
            this.cardBuilder.setExpire(expire);
            return this;
        }

        public Builder setCvv2(String cvv2) {
            this.cardBuilder.setCvv2(cvv2);
            return this;
        }

        public Builder setUserId(UUID userId) {
            this.cardBuilder.setUserId(userId);
            return this;
        }

        public Builder setCreatedBy(UUID createdBy) {
            this.cardBuilder.setCreatedBy(createdBy);
            return this;
        }

        public CardBO build() {
            return new CardBOImpl(this.cardBuilder.build());
        }
    }
}
