package me.avramovic.statisticsrestfulapi.service;

import me.avramovic.statisticsrestfulapi.persistance.Transaction;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Service
@Scope("application")
public class TransactionService {

    private final List<Transaction> transactionList = new LinkedList<>();

    public ResponseEntity addTransaction(Transaction transaction){
        if (transaction.getAmount() == null || transaction.getTimestamp() == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else if (transaction.getTimestamp().isAfter(ZonedDateTime.now().plusSeconds(60))) {
            return new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
        } else if (transaction.getTimestamp().isBefore(ZonedDateTime.now().plusSeconds(60).truncatedTo(ChronoUnit.SECONDS))) {
            storeTransaction(transaction);

            return new ResponseEntity(HttpStatus.CREATED);
        }
            System.out.print(getTransactionList().size());
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    public void storeTransaction(Transaction transaction) {
        transactionList.add(transaction);
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }
}
