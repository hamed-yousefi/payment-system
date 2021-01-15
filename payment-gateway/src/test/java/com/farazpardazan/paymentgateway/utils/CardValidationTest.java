/*
 * Copyright (c) 2019 TRIALBLAZE PTY. LTD. All rights reserved.
 *
 * Created by IntelliJ IDEA.
 * @author: Hamed Yousefi <hamed.yousefi@trialblaze.com>
 * @since: 1/14/21
 */
package com.farazpardazan.paymentgateway.utils;

import static  org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CardValidationTest {

    @Test
    public void testIsNumberValid() {

        String[] wrongNumbers = {"12903810a123125312", "192301", "91283018293810283091839429342"};
        for(String str : wrongNumbers) {
            assertFalse(CardValidation.isNumberValid(str));
        }

        String[] correctNumbers = {"6543123478900654", "8345189284329123"};
        for(String str : correctNumbers) {
            assertTrue(CardValidation.isNumberValid(str));
        }
    }
    
    @Test
    public void testIsExpirationValid() {

        String[] wrongDates = {"02//05", "05/033", "0a/06", "04/13"};
        for(String str : wrongDates) {
            assertFalse(CardValidation.isExpirationValid(str));
        }

        String[] correctDates = {"01/02", "50/12", "10/05"};
        for(String str : correctDates) {
            assertTrue(CardValidation.isExpirationValid(str));
        }
    }
}
