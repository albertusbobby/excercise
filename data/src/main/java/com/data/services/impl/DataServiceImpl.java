package com.data.services.impl;

import com.data.requests.PeopleRequest;
import com.data.responses.GeneralResponse;
import com.data.services.DataService;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DataServiceImpl implements DataService {

    @Override
    public Object getPeopleList(PeopleRequest peopleRequest) {
        String url = "http://localhost:8081/people/list/";
        RestTemplate restTemplate = new RestTemplate();
        Object result = restTemplate.postForObject(url, peopleRequest, Object.class);
        if(result!=null){
            return result;
        }else {
            return null;
        }
    }
}
