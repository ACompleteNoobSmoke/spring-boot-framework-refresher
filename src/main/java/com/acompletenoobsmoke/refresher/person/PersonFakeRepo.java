package com.acompletenoobsmoke.refresher.person;

import com.acompletenoobsmoke.refresher.exception.ResourceExistsException;
import com.acompletenoobsmoke.refresher.exception.ResourceNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class PersonFakeRepo implements PersonDAO{

    private static final List<Person> people = new ArrayList<>();
    private static AtomicInteger id = new AtomicInteger(0);

    static {
        people.add(new Person(id.incrementAndGet(), "Osaretin", "Omofonmwan", 30, Gender.MALE, "OsoInfinite95@gmail.com"));
        people.add(new Person(id.incrementAndGet(), "Victoria", "Marshall", 29, Gender.FEMALE, "Marshall_Victoria@yahoo.com"));
        people.add(new Person(id.incrementAndGet(), "Adedayo", "Uwensuyi", 31, Gender.MALE, "supposani616@yahoo.com"));
        people.add(new Person(id.incrementAndGet(), "Ajinboye", "Uwensuyi", 33, Gender.MALE, "uwensuyi@hotmail.com"));
        people.add(new Person(id.incrementAndGet(), "Amenzee", "Omofonmwan", 40, Gender.FEMALE, "AZee@yahoo.com"));
        people.add(new Person(id.incrementAndGet(), "Osato", "Omofonmwan", 45, Gender.FEMALE, "OO1980@yahoo.com"));
    }

    public List<Person> getPeople() {
        return people;
    }

    @Override
    public Optional<Person> getPerson(Integer id) {
        return people.stream().filter(p -> id.equals(p.getId())).findFirst();
    }

    @Override
    public void insertPerson(NewPersonRecordRequest person) {
        if(people.stream().anyMatch(p -> p.getEmail().equals(person.getEmail())))
            throw new ResourceExistsException("Person with email " + person.getEmail() + " already exists");
        people.add(new Person(id.incrementAndGet(), person.getFirstName(),
                person.getLastName(), person.getAge(), person.getGender(), person.getEmail()));
    }

    @Override
    public void removePerson(Integer id) {
        Optional<Person> person = getPerson(id);
        if (person.isEmpty()) { throw new ResourceNotFoundException("Person with id " + id + " does not exist"); }
        people.remove(person.get());
    }

    @Override
    public Optional<Person> updatePerson(int id, Person person) {
        Optional<Person> optionalPerson = getPerson(id);
        if (optionalPerson.isEmpty())
            throw new ResourceNotFoundException("Person with id " + id+ " does not exist");
        String firstName = person.getFirstName() != null && !person.getFirstName().isEmpty() &&
                !person.getFirstName().equals(optionalPerson.get().getFirstName()) ? person.getFirstName() : optionalPerson.get().getFirstName();
        String lastName = person.getLastName() != null && !person.getLastName().isEmpty() &&
                !person.getLastName().equals(optionalPerson.get().getLastName()) ? person.getLastName() : optionalPerson.get().getLastName();
        int age = (person.getAge() != 0 && person.getAge() != optionalPerson.get().getAge()) ? person.getAge() : optionalPerson.get().getAge();
        people.removeIf(u -> u.id.equals(id));
        people.add(new Person(id, firstName, lastName, age, optionalPerson.get().getGender(),  optionalPerson.get().getEmail()));
        return Optional.of(people.getLast());
    }
}
