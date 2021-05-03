package me.avramovic.statisticsrestfulapi.service;

import me.avramovic.statisticsrestfulapi.persistance.Statistics;
import me.avramovic.statisticsrestfulapi.persistance.Transaction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

import static me.avramovic.statisticsrestfulapi.service.TransactionService.TRANSACTION_LIST;

@Service
public class StatisticsService {




    public ResponseEntity<Statistics> getStatistics(){
        Statistics statistics = new Statistics();

        List<Transaction> filteredList = TRANSACTION_LIST.stream()
                .filter(tr -> tr.getTimestamp().isBefore(ZonedDateTime.now()))
                .toList();

        return new ResponseEntity(statistics, HttpStatus.OK);
    }

}
