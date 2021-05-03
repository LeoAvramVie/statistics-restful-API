package me.avramovic.statisticsrestfulapi.controller;

import lombok.RequiredArgsConstructor;
import me.avramovic.statisticsrestfulapi.persistance.Transaction;
import me.avramovic.statisticsrestfulapi.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static me.avramovic.statisticsrestfulapi.service.TransactionService.TRANSACTION_LIST;

@RequiredArgsConstructor
@Controller
@RequestMapping("/transactions")
public class TransactionsController {

    private final TransactionService transactionService;

    @PostMapping()
    public ResponseEntity<HttpStatus> addTransaction(@RequestBody Transaction transaction) {
        return transactionService.addTransaction(transaction);
    }

    @DeleteMapping()
    public ResponseEntity<HttpStatus> deleteTransaction() {
        TRANSACTION_LIST.clear();

        return ResponseEntity.ok().build();
    }
}
