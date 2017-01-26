package com.example.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TRANSACTION")
public class DBTransaction implements Serializable {

    @Id
    @Column(name = "TRANSACTION_ID")
    private String transactionId;

    @OneToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "ORDER_ID")
    private DBOrder order;

    @Column(name = "AUTH_CODE")
    private String authCode;

    @Column(name = "RECEIPT")
    private String receipt;

    @Column(name = "CURRENCY")
    private String currency;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "SOURCE")
    private String source;

    @Column(name = "TARGET_TRANS_ID")
    private String targetTransactionId;

    @Column(name = "TRANSACTION_TYPE")
    private String transactionType;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "CREATED_TIMESTAMP")
    private ZonedDateTime createdOn;

    @Column(name = "UPDATED_TIMESTAMP")
    private ZonedDateTime updatedOn;

}
