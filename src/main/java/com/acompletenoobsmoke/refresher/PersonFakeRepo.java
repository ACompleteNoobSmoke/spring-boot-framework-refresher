package com.acompletenoobsmoke.refresher;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
        return people.stream().filter(p -> id.equals(p.getId())).findFirst();
    }

    @Override
    public void insertPerson(Person person) {
        people.add(new Person(id.incrementAndGet(), person.getFirstName(),
                person.getLastName(), person.getAge(), person.getGender()));
    }

    @Override
    public void removePerson(Integer id) {
        Optional<Person> person = getPerson(id);
        if (!person.isPresent()) { throw new RuntimeException("Person with id " + id + " does not exist"); }
        people.remove(person.get());
    }

    @Override
    public Optional<Person> updatePerson(int id, Person person) {
        Optional<Person> optionalPerson = getPerson(id);
        if (!optionalPerson.isPresent())
            throw new RuntimeException("Person with id " + id+ " does not exist");
        String firstName = person.getFirstName() != null && !person.getFirstName().isEmpty() &&
                !person.getFirstName().equals(optionalPerson.get().getFirstName()) ? person.getFirstName() : optionalPerson.get().getFirstName();
        String lastName = person.getLastName() != null && !person.getLastName().isEmpty() &&
                !person.getLastName().equals(optionalPerson.get().getLastName()) ? person.getLastName() : optionalPerson.get().getLastName();
        int age = (person.getAge() != 0 && person.getAge() != optionalPerson.get().getAge()) ? person.getAge() : optionalPerson.get().getAge();
        people.removeIf(u -> u.id.equals(id));
        people.add(new Person(id, firstName, lastName, age, optionalPerson.get().getGender()));
        return Optional.of(people.getLast());
    }
}
