package com.acompletenoobsmoke.refresher.person;

import java.util.List;
import java.util.Optional;

public interface PersonDAO {

    public List<Person> getPeople();
    public Optional<Person> getPerson(Integer id);
    public void insertPerson(NewPersonRecordRequest person);
    public void removePerson(Integer id);
    public Optional<Person> updatePerson(int id, Person person);
}
