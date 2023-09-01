package com.example.bookmanagementsystem.service;
import com.example.bookmanagementsystem.domain.History;
import com.example.bookmanagementsystem.dto.HistoryDtos;
import com.example.bookmanagementsystem.exception.NotFoundException;
import com.example.bookmanagementsystem.repository.HistoryRepository;
import com.example.bookmanagementsystem.support.domain.HistoryType;
import com.example.bookmanagementsystem.support.exception.ErrorManager;
import org.springframework.stereotype.Service;

@Service
public class HistoryService {

    private HistoryRepository historyRepository;

    public HistoryService(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    public History show(Long id) {
        return historyRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorManager.NOT_EXIST_HISTORY));
    }

    public History save(Long bookId, Long userId, HistoryType historyType) {
        return historyRepository.save(new History(bookId, userId, historyType));
    }

    public HistoryDtos search(String searchType, Long id) {
        if (searchType.equals("user")) {
            return HistoryDtos.of(historyRepository.findAllByUserId(id));
        }
        if (searchType.equals("book")) {
            return HistoryDtos.of(historyRepository.findAllByBookId(id));
        }
        return HistoryDtos.of(historyRepository.findAll());
    }
}