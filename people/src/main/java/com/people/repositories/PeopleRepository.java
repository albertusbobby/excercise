package com.people.repositories;

import com.people.entities.People;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PeopleRepository extends CrudRepository<People, Long> {
    @Query("SELECT people from People people where (:name is null or people.name like %:name%)" +
            "and (:placeOfBirth is null or people.placeOfBirth = :placeOfBirth)" +
            "and (:maxHeight is null or people.height <= :maxHeight)" +
            "and (:maxWeight is null or people.weight <= :maxWeight)" +
            "and (:maxAge is null or :maxAge <= substring(people.dateOfBirth, 1, 4))")
    List<People> selectPeopleList(@Param("name") String name, @Param("placeOfBirth") String placeOfBirth,
                                  @Param("maxHeight") Double maxHeight, @Param("maxWeight") Double maxWeight
                                , @Param("maxAge") String maxAge);
}
