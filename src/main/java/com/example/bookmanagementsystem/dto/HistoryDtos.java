package com.example.bookmanagementsystem.dto;

import com.example.bookmanagementsystem.domain.History;
import net.minidev.json.annotate.JsonIgnore;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HistoryDtos {

    private List<HistoryDto> histories;

    public HistoryDtos() {

    }

    public HistoryDtos(List<HistoryDto> histories) {
        this.histories = histories;
    }


    public static HistoryDtos of(List<History> histories) {
        List<HistoryDto> historyDtos = histories.stream().map(b -> b.toHistoryDto()).collect(Collectors.toList());
        return new HistoryDtos(historyDtos);
    }

    @JsonIgnore
    public int getSize() {
        return histories.size();
    }

    public List<HistoryDto> getHistories() {
        return histories;
    }

    public void setHistories(List<HistoryDto> histories) {
        this.histories = histories;
    }

}