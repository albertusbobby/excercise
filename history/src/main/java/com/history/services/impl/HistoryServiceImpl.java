package com.history.services.impl;

import com.history.responses.GeneralResponse;
import com.history.services.HistoryService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Value("${microservices.people.history.url}") private String peopleHistoryUrl;

    @Override
    public GeneralResponse getHistory(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> result = restTemplate.exchange(peopleHistoryUrl+id, HttpMethod.POST, null, String.class);
        if(result!=null){
            return GeneralResponse.dialog(200, result.getBody());
        }else{
            return null;
        }
    }
}
