package me.avramovic.statisticsrestfulapi.persistance;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class Transaction {
    private Double amount;
    private ZonedDateTime timestamp;
}
