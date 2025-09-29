package com.acompletenoobsmoke.refresher;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final PersonDAO personDAO;

    public PersonService(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    public List<Person> getPeople() {
        return personDAO.getPeople();
    }
}
