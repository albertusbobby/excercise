package com.history.services;

import com.history.responses.HistoryResponse;

import java.util.List;

public interface HistoryService {
    List<HistoryResponse> getHistory(Long id);
}
