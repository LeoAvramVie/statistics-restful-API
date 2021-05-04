package me.avramovic.statisticsrestfulapi.persistance;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
public class Transaction {
    private Double amount;
    private ZonedDateTime timestamp;
}
