package com.history.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
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
    public String getHistory(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<GeneralResponse> result = restTemplate.exchange(peopleHistoryUrl+id, HttpMethod.POST, null, GeneralResponse.class);
        if(result!=null){
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.convertValue(result.getBody().getData(), String.class);
        }else{
            return null;
        }
    }
}
