package me.avramovic.statisticsrestfulapi.service;

import me.avramovic.statisticsrestfulapi.persistance.Statistics;
import me.avramovic.statisticsrestfulapi.persistance.Transaction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatisticsService {

    private DecimalFormat formatedValueSum = formatedValueSum = new DecimalFormat("###.##");

    private final TransactionService transactionService;

    public ResponseEntity<Statistics> getStatistics() {
        Statistics statistics = new Statistics(0.0, 0.0, 0.0, 0.0, 0L);

        List<Transaction> filteredList = transactionService.getTransactionList().stream()
                .filter(tr -> tr.getTimestamp().isBefore(ZonedDateTime.now().plusSeconds(60)) && tr.getTimestamp().isAfter(ZonedDateTime.now()))
                .collect(Collectors.toList());

        if (!filteredList.isEmpty()) {
            filteredList.sort(Comparator.comparingDouble(Transaction::getAmount));

            calculateSum(filteredList, statistics);
            avaerageSum(filteredList, statistics);
            getMax(filteredList, statistics);
            getMin(filteredList, statistics);
            statistics.setCount((long) filteredList.size());
        }

        return new ResponseEntity(statistics, HttpStatus.OK);
    }

    private void calculateSum(List<Transaction> filteredList, Statistics statistics) {
        double sum = 0.0;
        for (Transaction transaction : filteredList) {
            sum += transaction.getAmount();
        }

        statistics.setSum(Double.parseDouble(formatedValueSum.format(sum)));
    }

    private void avaerageSum(List<Transaction> filteredList, Statistics statistics) {
        double avg = statistics.getSum() / filteredList.size();

        statistics.setAvg(Double.parseDouble(formatedValueSum.format(avg)));
    }

    private void getMax(List<Transaction> filteredList, Statistics statistics) {
        double max = 0;
        max += filteredList.get(filteredList.size() - 1).getAmount();

        statistics.setMax(Double.parseDouble(formatedValueSum.format(max)));
    }

    private void getMin(List<Transaction> filteredList, Statistics statistics) {
        double min = 0;
        min += filteredList.get(0).getAmount();

        statistics.setMin(Double.parseDouble(formatedValueSum.format(min)));
    }
}
