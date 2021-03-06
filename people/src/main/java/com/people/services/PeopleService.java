package com.people.services;

import com.people.entities.People;
import com.people.requests.PeopleDetailRequest;
import com.people.requests.PeopleRequest;
import com.people.responses.HistoryResponse;

import java.util.List;

public interface PeopleService {
    List<People> getPeopleDetail(PeopleRequest peopleRequest);
    People savePeopleDetail(PeopleDetailRequest peopleDetailRequest);
    List<HistoryResponse> getPeopleHistory(String id, String uri);
}
