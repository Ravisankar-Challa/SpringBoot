package com.example.respository;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.TestBase;
import com.example.entity.DBOrder;
import com.example.entity.DBTransaction;

public class TransactionRepositoryTest extends TestBase {

    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    public void test_store_transaction() throws Exception {
        DBOrder order = DBOrder.builder()
                               .amount(new BigDecimal("30.19"))
                               .createdBy("RAVI")
                               .createdOn(ZonedDateTime.now())
                               .currency("INR")
                               .orderCreatedOn(ZonedDateTime.now())
                               .updatedOn(ZonedDateTime.now())
                               .build();
        DBTransaction transaction = DBTransaction.builder()
                                                 .amount(new BigDecimal("45.6"))
                                                 .authCode("AUTHCODE1")
                                                 .createdBy("RAVI")
                                                 .currency("AUD")
                                                 .order(order)
                                                 .source("VISACARD")
                                                 .targetTransactionId("TAGR1")
                                                 .receipt("RECPT1")
                                                 .transactionId("TRANS1")
                                                 .createdOn(ZonedDateTime.now())
                                                 .updatedOn(ZonedDateTime.now())
                                                 .build();
        
        transactionRepository.saveAndFlush(transaction);
        
        DBTransaction transaction1 = transactionRepository.findByTransactionId("TRANS2").orElseThrow(() -> new Exception("Not found"));
        System.out.println(transaction1);
    }

}
