package com.people.services.impl;

import com.people.entities.History;
import com.people.entities.People;
import com.people.repositories.HistoryRepository;
import com.people.repositories.PeopleRepository;
import com.people.requests.PeopleDetailRequest;
import com.people.requests.PeopleRequest;
import com.people.responses.GeneralResponse;
import com.people.responses.HistoryResponse;
import com.people.services.PeopleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PeopleServiceImpl implements PeopleService {

    @Autowired
    PeopleRepository peopleRepository;
    @Autowired
    HistoryRepository historyRepository;

    @Override
    public List<People> getPeopleDetail(PeopleRequest peopleRequest) {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy");
        String maxAgeParam = null;

        if(peopleRequest.getMaxAge()!=null){
            maxAgeParam = String.valueOf(Integer.parseInt(df.format(date)) - peopleRequest.getMaxAge());;
        }

        return peopleRepository.selectPeopleList(peopleRequest.getName(), peopleRequest.getPlaceOfBirth(),
                peopleRequest.getMaxHeight(), peopleRequest.getMaxWeight(), maxAgeParam);
    }

    @Override
    public People savePeopleDetail(PeopleDetailRequest peopleDetailRequest) {
        ModelMapper modelMapper = new ModelMapper();
        People people = modelMapper.map(peopleDetailRequest, People.class);
        return peopleRepository.save(people);
    }

    @Override
    public GeneralResponse getPeopleHistory(String id) {
        People people = peopleRepository.findById(Long.valueOf(id)).orElseThrow(() -> new NoSuchElementException("Id tidak ada"));
        if(people!=null){
            List<String> list = new ArrayList<>();
            String [] strSplit = people.getHistory().split("\\|");
            for (String s : strSplit) {
                History history = historyRepository.findById(Long.valueOf(s)).orElse(null);
                if (history != null) {
                    list.add(history.getDescription());
                }
            }
            return GeneralResponse.dialog(200, String.valueOf(list));
        }else{
            return null;
        }

    }
}
