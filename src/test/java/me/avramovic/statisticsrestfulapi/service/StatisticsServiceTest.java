package me.avramovic.statisticsrestfulapi.service;

import me.avramovic.statisticsrestfulapi.persistance.Statistics;
import me.avramovic.statisticsrestfulapi.persistance.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
class StatisticsServiceTest {

    @Mock
    private TransactionService transactionService;
    @InjectMocks
    private StatisticsService statisticsService;

    @BeforeEach
    public void init() {
        initMocks(this);
    }

    @Test
    public void getStatistics_whenNoTransactionGiven_thenReturnZeros() {
        List<Transaction> transactionList = new LinkedList<>();
        when(transactionService
                .getTransactionList())
                .thenReturn(transactionList);

        ResponseEntity<Statistics> statistics = statisticsService.getStatistics();
        assertThat(statistics.getBody().getSum()).isEqualTo(0.0);
        assertThat(statistics.getBody().getMax()).isEqualTo(0.0);
        assertThat(statistics.getBody().getMin()).isEqualTo(0.0);
        assertThat(statistics.getBody().getAvg()).isEqualTo(0.0);
    }

    @Test
    public void getStatistics_whenTransactionGiven_thenReturnValues() {
        List<Transaction> transactionList = new LinkedList<>();
        Transaction transaction1 = new Transaction(10.273, ZonedDateTime.now().plusSeconds(30));
        Transaction transaction2 = new Transaction(10.562, ZonedDateTime.now().plusSeconds(30));
        transactionList.add(transaction1);
        transactionList.add(transaction2);
        when(transactionService
                .getTransactionList())
                .thenReturn(transactionList);

        ResponseEntity<Statistics> statistics = statisticsService.getStatistics();
        assertThat(statistics.getBody().getSum()).isEqualTo(20.84);
        assertThat(statistics.getBody().getMax()).isEqualTo(10.56);
        assertThat(statistics.getBody().getMin()).isEqualTo(10.27);
        assertThat(statistics.getBody().getAvg()).isEqualTo(10.42);
    }

    @Test
    public void getStatistics_whenTransactionOlderThen30Sec_thenReturnOnlyOlderValues() {
        List<Transaction> transactionList = new LinkedList<>();
        Transaction transaction1 = new Transaction(10.273, ZonedDateTime.now().plusSeconds(30));
        Transaction transaction2 = new Transaction(10.562, ZonedDateTime.now().minusSeconds(30));
        transactionList.add(transaction1);
        transactionList.add(transaction2);
        when(transactionService.getTransactionList()).thenReturn(transactionList);

        ResponseEntity<Statistics> statistics = statisticsService.getStatistics();
        assertThat(statistics.getBody().getSum()).isEqualTo(10.27);
        assertThat(statistics.getBody().getMax()).isEqualTo(10.27);
        assertThat(statistics.getBody().getMin()).isEqualTo(10.27);
        assertThat(statistics.getBody().getAvg()).isEqualTo(10.27);
    }

}
