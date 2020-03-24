package com.history.controller;

import com.history.responses.GeneralResponse;
import com.history.services.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/history")
public class HistoryController {

    @Autowired
    HistoryService historyService;

    @PostMapping("/{id}")
    private GeneralResponse getHistory(@PathVariable Long id){
        return GeneralResponse.response(historyService.getHistory(id));
    }

}
