package com.acompletenoobsmoke.refresher;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private final PersonDAO personDAO;

    public PersonService(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    private List<Person> getPeople() {
        return personDAO.getPeople();
    }

    public List<Person> getPeople(SORT sort) {
        if (sort == SORT.DESC) {
            return personDAO.getPeople().stream().sorted((a, b) -> b.id() - a.id()).toList();
        }
        return getPeople();
    }

    public void addPerson(Person person) {
        personDAO.insertPerson(person);
    }

    public Person getPersonByID(Integer id) {
        return personDAO.getPerson(id).orElseThrow(() ->
                new IllegalArgumentException("No person found with id: " + id));
    }

    public void removePersonByID(Integer id) {
        personDAO.removePerson(id);
    }

}
