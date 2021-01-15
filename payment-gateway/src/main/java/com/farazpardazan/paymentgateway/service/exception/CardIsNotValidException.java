/*
 * Copyright (c) 2019 TRIALBLAZE PTY. LTD. All rights reserved.
 *
 * Created by IntelliJ IDEA.
 * @author: Hamed Yousefi <hamed.yousefi@trialblaze.com>
 * @since: 1/14/21
 */
package com.farazpardazan.paymentgateway.service.exception;

public class CardIsNotValidException extends Exception {

    private final static String EXCEPTION_MESSAGE = "Card Validation Exception: ";

    public CardIsNotValidException(String message) {
        super(EXCEPTION_MESSAGE + message);
    }
}
