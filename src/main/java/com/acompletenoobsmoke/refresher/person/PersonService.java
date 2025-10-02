package com.acompletenoobsmoke.refresher.person;

import com.acompletenoobsmoke.refresher.util.SORT;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
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

    public List<Person> getPeople(String sort) {
        SORT sortecChoice = (sort != null && sort.toUpperCase().equals("DESC")) ? SORT.DESC : SORT.ASC;
        if (sortecChoice == SORT.DESC) {
            return getPeople().stream().sorted(Comparator.comparing(Person::getId).reversed()).collect(Collectors.toList());
        }
        return getPeople().stream().sorted(Comparator.comparing(Person::getId)).collect(Collectors.toList());
    }

    public void addPerson(Person person) {
        personDAO.insertPerson(person);
    }

    public Optional<Person> updatePerson(int id, Person person) {
        return personDAO.updatePerson(id, person);
    }

    public Person getPersonByID(Integer id) {
        return personDAO.getPerson(id).orElseThrow(() ->
                new IllegalArgumentException("No person found with id: " + id));
    }

    public void removePersonByID(Integer id) {
        personDAO.removePerson(id);
    }

}
