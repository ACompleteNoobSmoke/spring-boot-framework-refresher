package com.acompletenoobsmoke.refresher.person;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    public String firstName;
    public String lastName;
    public int age;
    public Gender gender;
    public String email;

    public Person(Integer id, String firstName, String lastName, int age, Gender gender, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.email = email;
    }

    public Person(String firstName, String lastName, int age, Gender gender, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.email = email;
    }

    public Person() {

    }


    public Gender getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public Integer getId() {
        return id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getProfile() {
        return toString();
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                '}';
    }
}

