package me.avramovic.statisticsrestfulapi.service;

import me.avramovic.statisticsrestfulapi.persistance.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.ZonedDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
class TransactionServiceTest {

    @Mock
    private TransactionService transactionService;

    @BeforeEach
    public void init() {
        initMocks(this);
    }

    @Test
    public void addTransaction_whenDataIsValid_thenReturnStatusCreated() {
        Transaction newTransaction = new Transaction(100.22, ZonedDateTime.now());

        ResponseEntity<Transaction> responseEntity = transactionService.addTransaction(newTransaction);
        assertThat(responseEntity
                .getStatusCode())
                .isEqualTo(HttpStatus.CREATED);
    }
}
