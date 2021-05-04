package me.avramovic.statisticsrestfulapi.controller;

import lombok.RequiredArgsConstructor;
import me.avramovic.statisticsrestfulapi.persistance.Statistics;
import me.avramovic.statisticsrestfulapi.service.StatisticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/statistics")
public class StatisticsController {

    private final StatisticsService statisticsService;


    @GetMapping("")
    public ResponseEntity<Statistics> getStatistics(){

        return statisticsService.getStatistics();
    }
}
