package com.history.services.impl;

import com.history.responses.GeneralResponse;
import com.history.responses.HistoryResponse;
import com.history.services.HistoryService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Value("${microservices.people.history.url}") private String peopleHistoryUrl;

    @Override
    public List<HistoryResponse> getHistory(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        GeneralResponse<List<HistoryResponse>> response = restTemplate.getForObject(peopleHistoryUrl+id, GeneralResponse.class);
        if(response.getCode()==200){
            return response.getData();
        }else{
            return null;
        }
    }
}
