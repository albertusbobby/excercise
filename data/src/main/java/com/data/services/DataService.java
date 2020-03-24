package com.data.services;

import com.data.requests.PeopleRequest;

public interface DataService {
    Object getPeopleList(PeopleRequest peopleRequest);
}
