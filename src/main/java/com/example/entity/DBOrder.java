package com.example.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "DB_ORDER")
public class DBOrder implements Serializable { 

    @Id
    @Column(name = "ORDER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String orderId;

    @Column(name = "CURRENCY")
    private String currency;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "ORDER_CREATED_TIME")
    private ZonedDateTime orderCreatedOn;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "CREATED_TIMESTAMP")
    private ZonedDateTime createdOn;

    @Column(name = "UPDATED_TIMESTAMP")
    private ZonedDateTime updatedOn;
    
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "order")
    private DBTransaction transaction;
      
}
