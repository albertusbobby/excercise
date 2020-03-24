package com.people.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class PeopleDto {

    private Long id;
    private String name;
    private Date dateOfBirth;
    private String placeOfBirth;
    private Double height;
    private Double weight;
    private String history;
}
