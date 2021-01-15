/*
 * Copyright (c) 2019 TRIALBLAZE PTY. LTD. All rights reserved.
 *
 * Created by IntelliJ IDEA.
 * @author: Hamed Yousefi <hamed.yousefi@trialblaze.com>
 * @since: 1/14/21
 */
package com.farazpardazan.paymentgateway.utils;

public class CardValidation {

    public static boolean isNumberValid(String cardNumber) {
        // card number is 16 digits in a raw
        return cardNumber.matches("[0-9]{16}");
    }

    public static boolean isExpirationValid(String expirationDate) {
        // expiration date format is {year}/{month} i.g. 05/12 is the last month of year 1405
        return expirationDate.matches("[0-9]{2}/(1[0-2]|0[1-9])");
    }
}
