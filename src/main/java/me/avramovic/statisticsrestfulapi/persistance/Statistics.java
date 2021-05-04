package me.avramovic.statisticsrestfulapi.persistance;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Statistics {

    private Double sum;
    private Double avg;
    private Double max;
    private Double min;
    private Long count;

}
