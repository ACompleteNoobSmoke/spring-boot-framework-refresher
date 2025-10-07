package com.acompletenoobsmoke.refresher.person;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class NewPersonRecordRequest {

    @NotEmpty
    public final String firstName;
    @NotEmpty
    public final String lastName;
    @Min(18)
    public final int age;
    @NotNull
    public final Gender gender;

    public NewPersonRecordRequest(Integer id, String firstName, String lastName, int age, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
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


    public String getProfile() {
        return toString();
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }
}

