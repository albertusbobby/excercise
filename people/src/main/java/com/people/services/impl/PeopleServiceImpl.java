package com.people.services.impl;

import com.people.entities.History;
import com.people.entities.People;
import com.people.entities.Request;
import com.people.repositories.HistoryRepository;
import com.people.repositories.PeopleRepository;
import com.people.repositories.RequestRepository;
import com.people.requests.PeopleDetailRequest;
import com.people.requests.PeopleRequest;
import com.people.responses.GeneralResponse;
import com.people.responses.HistoryResponse;
import com.people.services.PeopleService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PeopleServiceImpl implements PeopleService {
	
	private static Logger logger = LoggerFactory.getLogger(PeopleServiceImpl.class);

    @Autowired
    PeopleRepository peopleRepository;
    @Autowired
    HistoryRepository historyRepository;
    @Autowired
    RequestRepository requestRepository;

    @Override
    public List<People> getPeopleDetail(PeopleRequest peopleRequest) {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy");
        String maxAgeParam = null;

        if(peopleRequest.getMaxAge()!=null){
            maxAgeParam = String.valueOf(Integer.parseInt(df.format(date)) - peopleRequest.getMaxAge());;
        }

        Request request = new Request();
        request.setRequest(peopleRequest.toString());
        requestRepository.save(request);
		logger.info("info insert people detail: "+peopleRequest.toString());

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
    public List<HistoryResponse> getPeopleHistory(String id, String uri) {
        People people = peopleRepository.findById(Long.valueOf(id)).orElseThrow(() -> new NoSuchElementException("Id tidak ada"));
        if(people!=null){
            String [] strSplit = people.getHistory().split("\\|");
            List<HistoryResponse> list = new ArrayList<>();
            for (int i=0;i<strSplit.length;i++) {
                History history = historyRepository.findById(Long.valueOf(strSplit[i])).orElse(null);
                if (history != null) {
                    HistoryResponse historyResponse = new HistoryResponse();
                    historyResponse.setId(history.getId());
                    historyResponse.setDescription(history.getDescription());
                    list.add(i, historyResponse);
                }
            }
            Request request = new Request();
            request.setRequest(uri);
            requestRepository.save(request);
			logger.info("info insert history: "+uri);
            return list;
        }else{
            return null;
        }
    }
}
