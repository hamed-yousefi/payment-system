/*
 * Copyright (c) 2019 TRIALBLAZE PTY. LTD. All rights reserved.
 *
 * Created by IntelliJ IDEA.
 * @author: Hamed Yousefi <hamed.yousefi@trialblaze.com>
 * @since: 1/14/21
 */
package com.farazpardazan.paymentgateway.service;

import com.farazpardazan.paymentgateway.db.model.Card;
import com.farazpardazan.paymentgateway.db.model.UserInfo;
import com.farazpardazan.paymentgateway.db.repository.CardRepository;
import com.farazpardazan.paymentgateway.db.repository.UserInfoRepository;
import com.farazpardazan.paymentgateway.service.bo.CardBO;
import com.farazpardazan.paymentgateway.service.bo.CardBOImpl;
import com.farazpardazan.paymentgateway.service.data.SampleData;
import com.farazpardazan.paymentgateway.service.exception.CardIsNotValidException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CardServiceTest {

    private CardService cardService;
    private UserInfoRepository userInfoRepository;
    private CardRepository cardRepository;

    private UserInfo userInfo;

    @Autowired
    public CardServiceTest(CardService cardService, UserInfoRepository userInfoRepository, CardRepository cardRepository) {
        this.cardService = cardService;
        this.userInfoRepository = userInfoRepository;
        this.cardRepository = cardRepository;
    }

    @BeforeEach
    void setUp() {
        userInfo = userInfoRepository.save(SampleData.sampleUser);
    }

    @AfterEach
    void tearDown() {
        userInfoRepository.deleteById(userInfo.getId());
    }

    @Test
    public void testAddCardWithInvalidCard() {

        assertThrows(CardIsNotValidException.class, () -> {
            cardService.addCard(
                    CardBOImpl.Builder.getInstance()
                            .setNumber("125312315jk")
                            .setCvv2("4512")
                            .setExpire("22/42")
                            .setUserId(userInfo.getId())
                            .setCreatedBy(userInfo.getId())
                            .build()
            );
        });
    }

    @Test
    public void testAddCard() {
        assertDoesNotThrow(() -> {

            CardBO cardBO = CardBOImpl.Builder.getInstance()
                    .setNumber("4356143209809078")
                    .setCvv2("4512")
                    .setExpire("22/05")
                    .setUserId(userInfo.getId())
                    .setCreatedBy(userInfo.getId())
                    .build();

            UUID cardId = cardService.addCard(cardBO);

            Optional<Card> card = cardRepository.findById(cardId);
            assertTrue(card.isPresent());
            assertEquals(cardBO.getCard().getNumber(), card.get().getNumber());

        });
    }
}
