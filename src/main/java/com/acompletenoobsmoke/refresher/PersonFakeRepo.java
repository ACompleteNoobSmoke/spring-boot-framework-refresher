package com.acompletenoobsmoke.refresher;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class PersonFakeRepo implements PersonDAO{

    private static List<Person> people = new ArrayList<>();
    private static AtomicInteger id = new AtomicInteger(0);

    static {
        people.add(new Person(id.incrementAndGet(), "Osaretin", "Omofonmwan", 30, Gender.MALE));
        people.add(new Person(id.incrementAndGet(), "Victoria", "Marshall", 29, Gender.FEMALE));
        people.add(new Person(id.incrementAndGet(), "Adedayo", "Uwensuyi", 31, Gender.MALE));
        people.add(new Person(id.incrementAndGet(), "Ajinboye", "Uwensuyi", 33, Gender.MALE));
        people.add(new Person(id.incrementAndGet(), "Amenzee", "Omofonmwan", 40, Gender.FEMALE));
        people.add(new Person(id.incrementAndGet(), "Osato", "Omofonmwan", 45, Gender.FEMALE));
    }

    public List<Person> getPeople() {
        return people;
    }

    @Override
    public Optional<Person> getPerson(Integer id) {
        return people.stream().filter(u -> u.id() == id).findFirst();
    }

    @Override
    public void insertPerson(Person person) {
        people.add(new Person(id.incrementAndGet(), person.firstName(),
                person.lastName(), person.age(), person.gender()));
    }

    @Override
    public void removePerson(Integer id) {
        Optional<Person> person = getPerson(id);
        if (!person.isPresent()) { throw new RuntimeException("Person with id " + id + " does not exist"); }
        people.remove(person.get());
    }
}
