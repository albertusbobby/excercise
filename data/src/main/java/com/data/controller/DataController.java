package com.data.controller;

import com.data.requests.PeopleRequest;
import com.data.responses.GeneralResponse;
import com.data.services.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data")
public class DataController {

    @Autowired
    private DataService dataService;

    @PostMapping("/people/list")
    public Object getPeopleList(@ModelAttribute PeopleRequest peopleRequest){
        return dataService.getPeopleList(peopleRequest);
    }
}
