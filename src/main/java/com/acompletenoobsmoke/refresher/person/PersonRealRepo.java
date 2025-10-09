package com.acompletenoobsmoke.refresher.person;

import com.acompletenoobsmoke.refresher.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Repository("real-people-repo")
public class PersonRealRepo implements PersonDAO {

    private final PersonRepository personRepository;

    public PersonRealRepo(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> getPeople() {
        return personRepository.findAll();
    }

    @Override
    public Optional<Person> getPerson(Integer id) {
        if (!personRepository.existsById(id)) throw new ResourceNotFoundException("Person with id " + id + " does not exist");
        return personRepository.findById(id);
    }

    @Override
    public void insertPerson(NewPersonRecordRequest person) {
        System.out.println(person.toString());
        personRepository.save(new Person(person.getFirstName(),
                person.getLastName(), person.getAge(), person.getGender(),
                person.getEmail()));
    }

    @Override
    public void removePerson(Integer id) {
        if (!personRepository.existsById(id)) throw new ResourceNotFoundException("Person with id " + id + " does not exist");
        personRepository.deleteById(id);
    }

    @Override
    public Optional<Person> updatePerson(int id, Person person) {
        Optional<Person> optionalPersonObject = personRepository.findById(id);
        if (optionalPersonObject.isEmpty()) throw new ResourceNotFoundException("Person not found");
        Person optionalPerson = optionalPersonObject.get();
        String firstName = person.getFirstName() != null && !person.getFirstName().isEmpty() &&
                !person.getFirstName().equals(optionalPerson.getFirstName()) ? person.getFirstName() : optionalPerson.getFirstName();
        String lastName = person.getLastName() != null && !person.getLastName().isEmpty() &&
                !person.getLastName().equals(optionalPerson.getLastName()) ? person.getLastName() : optionalPerson.getLastName();
        int age = (person.getAge() != 0 && person.getAge() != optionalPerson.getAge()) ? person.getAge() : optionalPerson.getAge();
        String email = person.getEmail() != null && !person.getEmail().isEmpty() && !person.getEmail().equals(optionalPerson.getEmail()) ? person.getEmail() : optionalPerson.getEmail();
        optionalPerson.setFirstName(firstName);
        optionalPerson.setLastName(lastName);
        optionalPerson.setAge(age);
        optionalPerson.setEmail(email);
        personRepository.save(optionalPerson);
        return Optional.of(optionalPerson);
    }
}
