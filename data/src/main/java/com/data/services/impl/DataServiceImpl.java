package com.data.services.impl;

import com.data.requests.PeopleRequest;
import com.data.services.DataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DataServiceImpl implements DataService {

    @Value("${microservices.people.list.url}") private String peopleListUrl;

    @Override
    public Object getPeopleList(PeopleRequest peopleRequest) {
        RestTemplate restTemplate = new RestTemplate();
        Object result = restTemplate.postForObject(peopleListUrl, peopleRequest, Object.class);
        if(result!=null){
            return result;
        }else {
            return null;
        }
    }
}
