package com.history.services;

import com.history.responses.GeneralResponse;

public interface HistoryService {
    GeneralResponse getHistory(Long id);
}
