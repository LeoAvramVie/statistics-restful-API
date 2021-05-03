package me.avramovic.statisticsrestfulapi.controller;

import me.avramovic.statisticsrestfulapi.persistance.Statistics;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/statistics")
public class StatisticsController {

    @GetMapping
    public ResponseEntity<Statistics> getStatistics(){
        return new ResponseEntity<Statistics>(new Statistics(), HttpStatus.OK);
    }
}
