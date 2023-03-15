package com.backend.librarymanagementsystem.Repository;

import com.backend.librarymanagementsystem.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {

    @Query(value = "select * from transaction t where t.card_id=:cardId AND t.transaction_status='SUCCESS'", nativeQuery = true)
    List<Transaction> getAllSuccessfullTxnsWithCardId(int cardId);
}
