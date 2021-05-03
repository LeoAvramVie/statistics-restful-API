package me.avramovic.statisticsrestfulapi.service;

import me.avramovic.statisticsrestfulapi.persistance.Transaction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    public static final List<Transaction> TRANSACTION_LIST = new ArrayList<>();

    public ResponseEntity addTransaction(Transaction transaction){
        if (transaction.getAmount() == null || transaction.getTimestamp() == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else if (transaction.getTimestamp().isAfter(ZonedDateTime.now().plusSeconds(60))) {
            return new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
        } else if (transaction.getTimestamp().isBefore(ZonedDateTime.now().plusSeconds(60).truncatedTo(ChronoUnit.SECONDS))) {
            TRANSACTION_LIST.add(transaction);

            return new ResponseEntity(HttpStatus.CREATED);
        }

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
