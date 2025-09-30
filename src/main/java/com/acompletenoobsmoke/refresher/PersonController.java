package com.acompletenoobsmoke.refresher;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/people")
@RestController
public class PersonController {

    private PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<List<Person>> getPeople(@RequestParam(value = "sort", required = false, defaultValue = "ASC") SORT sort) {
        return new ResponseEntity<>(personService.getPeople(sort), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Integer id) {
        return new ResponseEntity<>(personService.getPersonByID(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> addPerson(@RequestBody Person person) {
        personService.addPerson(person);
        String message = person.firstName() + " " + person.lastName() + " added successfully!";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updatePerson(@PathVariable Integer id, @RequestBody Person person) {
        try {
            return new ResponseEntity<>(personService.updatePerson(id, person), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deletePersonById(@PathVariable Integer id) {
        try {
            personService.removePersonByID(id);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Person with id " + id + " was removed from database", HttpStatus.OK);
    }


}
