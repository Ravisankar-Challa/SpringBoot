package com.example.respository;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.DBOrder;

@Repository
@Transactional(value = TxType.REQUIRES_NEW)
public interface OrderRepository extends JpaRepository<DBOrder, Long>{

}
