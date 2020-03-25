package com.people.controller;

import com.people.exception.CommonException;
import com.people.requests.PeopleDetailRequest;
import com.people.requests.PeopleRequest;
import com.people.responses.GeneralResponse;
import com.people.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/people")
public class PeopleController {

    @Autowired
    private PeopleService peopleService;

    @PostMapping("/list")
    private GeneralResponse getPeopleDetail(@RequestBody PeopleRequest peopleRequest){
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

    @GetMapping("/history/{id}")
    private GeneralResponse peopleHistory(@PathVariable String id, HttpServletRequest request) {
        try {
            return GeneralResponse.response(peopleService.getPeopleHistory(id, request.getRequestURL().toString()));
        } catch (Exception e) {
            throw new CommonException(400, e.getMessage());
        }
    }
}
