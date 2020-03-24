package com.people.controller;

import com.people.exception.CommonException;
import com.people.requests.PeopleDetailRequest;
import com.people.requests.PeopleRequest;
import com.people.responses.GeneralResponse;
import com.people.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/people")
public class PeopleController {

    @Autowired
    private PeopleService peopleService;

    @PostMapping("/list")
    private GeneralResponse getPeopleDetail(@RequestBody PeopleRequest peopleRequest){
        System.out.println(peopleRequest.getName());
        try {
            return GeneralResponse.response(peopleService.getPeopleDetail(peopleRequest));
        } catch (CommonException e) {
            throw new CommonException(400, e.getMessage());
        }
    }

    @PostMapping("/save")
    private GeneralResponse savePeopleDetail(@ModelAttribute PeopleDetailRequest peopleDetailRequest){
        try {
            return GeneralResponse.response(peopleService.savePeopleDetail(peopleDetailRequest));
        } catch (CommonException e) {
            throw new CommonException(400, e.getMessage());
        }
    }

    @PostMapping("/history/{id}")
    private GeneralResponse peopleHistory(@PathVariable String id){
        return peopleService.getPeopleHistory(id);
    }
}
