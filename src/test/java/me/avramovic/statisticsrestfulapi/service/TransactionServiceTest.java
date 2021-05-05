package me.avramovic.statisticsrestfulapi.service;

import me.avramovic.statisticsrestfulapi.persistance.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {

    private TransactionService transactionService;

    @Test
    public void addTransaction_whenDataIsValid_thenReturnStatusCreated() {
        Transaction newTransaction = new Transaction(100.22, ZonedDateTime.now());
        ResponseEntity<Transaction> responseEntity = transactionService.addTransaction(newTransaction);
        assertThat(responseEntity
                .getStatusCode())
                .isEqualTo(HttpStatus.CREATED);
    }
    @Test
    public void addTransaction_whenDataIsNull_thenReturnStatusBadRequest() {
        Transaction newTransaction = new Transaction(null, null);
        ResponseEntity<Transaction> responseEntity = transactionService.addTransaction(newTransaction);
        assertThat(responseEntity
                .getStatusCode())
                .isEqualTo(HttpStatus.BAD_REQUEST);
    }
    @Test
    public void addTransaction_whenDataOlderthen60Sec_thenReturnStatusUNPROCESSABLE_ENTITY() {
        Transaction newTransaction = new Transaction(100.22, ZonedDateTime.now().plusSeconds(90));
        ResponseEntity<Transaction> responseEntity = transactionService.addTransaction(newTransaction);
        assertThat(responseEntity
                .getStatusCode())
                .isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
    }
    @Test
    public void addTransaction_whenDataOlderthen60Sec_thenReturnStatusNO_CONTENT() {
        ResponseEntity<Transaction> responseEntity = transactionService.addTransaction(null);
        assertThat(responseEntity
                .getStatusCode())
                .isEqualTo(HttpStatus.NO_CONTENT);
    }

}
