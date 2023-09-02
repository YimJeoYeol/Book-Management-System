package com.example.bookmanagementsystem.web;

import com.example.bookmanagementsystem.service.HistoryService;
import com.example.bookmanagementsystem.dto.*;
import com.example.bookmanagementsystem.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/histories")
public class ApiHistoryController {

    private HistoryService historyService;

    public ApiHistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }


    @GetMapping("{id}")
    public ResponseEntity<HistoryDto> show(@PathVariable Long id) {
        History history = historyService.show(id);
        return ResponseEntity.ok(history.toHistoryDto());
    }

    @GetMapping("")
    public ResponseEntity<HistoryDtos> search(String searchType, Long id) {
        HistoryDtos histories = historyService.search(searchType, id);
        return ResponseEntity.ok(histories);
    }
}