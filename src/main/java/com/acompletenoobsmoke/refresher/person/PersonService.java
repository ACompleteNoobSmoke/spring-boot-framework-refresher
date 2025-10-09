package com.acompletenoobsmoke.refresher.person;

import com.acompletenoobsmoke.refresher.exception.ResourceNotFoundException;
import com.acompletenoobsmoke.refresher.util.SORT;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface PersonService {

    List<Person> getPeople();

    List<Person> getPeople(String sort);

    void addPerson(NewPersonRecordRequest person);

    Optional<Person> updatePerson(int id, Person person);

    Person getPersonByID(Integer id);

    void removePersonByID(Integer id);

}
