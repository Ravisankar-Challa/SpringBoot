package com.example.respository;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.TestBase;
import com.example.entity.DBOrder;
import com.example.entity.DBTransaction;

public class OrderRespositoryTest extends TestBase {

    @Autowired
    private OrderRepository orderRespository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    public void test_add_order() {

        DBTransaction transaction = DBTransaction.builder()
                                                 .amount(new BigDecimal("45.6"))
                                                 .authCode("AUTHCODE1")
                                                 .createdBy("RAVI")
                                                 .currency("AUD")
                                                 //.order(order)
                                                 .source("VISACARD")
                                                 .targetTransactionId("TAGR1")
                                                 .receipt("RECPT1")
                                                 .transactionId("TRANS1")
                                                 .createdOn(ZonedDateTime.now())
                                                 .updatedOn(ZonedDateTime.now())
                                                 .build();

        DBOrder order = DBOrder.builder()
                               .amount(new BigDecimal("30.19"))
                               .createdBy("RAVI")
                               .createdOn(ZonedDateTime.now())
                               .currency("INR")
                               .orderCreatedOn(ZonedDateTime.now())
                               .updatedOn(ZonedDateTime.now())
                               .transaction(transaction)
                               .build();

        orderRespository.saveAndFlush(order);

        DBTransaction transaction1 = transactionRepository.findOne("TRANS1");
        System.out.println(transaction1);
    }

}
