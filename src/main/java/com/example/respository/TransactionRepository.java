package com.example.respository;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.DBTransaction;

@Repository
@Transactional(value = TxType.REQUIRES_NEW)
public interface TransactionRepository extends JpaRepository<DBTransaction, String>{
    Optional<DBTransaction> findByTransactionId(String transactionId);
}
