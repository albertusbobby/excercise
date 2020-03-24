package com.history.services.impl;

import com.history.responses.GeneralResponse;
import com.history.services.HistoryService;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Override
    public GeneralResponse getHistory(Long id) {
        String url = "http://localhost:8081/people/history/"+id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.POST, null, String.class);
        if(result!=null){
            return GeneralResponse.dialog(200, result.getBody());
        }else{
            return null;
        }
    }
}
