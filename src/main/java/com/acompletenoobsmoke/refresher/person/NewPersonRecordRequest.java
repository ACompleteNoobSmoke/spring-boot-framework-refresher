package com.acompletenoobsmoke.refresher.person;

import jakarta.validation.constraints.Email;
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
    @Email(message = "Format email BUSTA!")
    public final String email;

    public NewPersonRecordRequest(String firstName, String lastName, int age, Gender gender, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.email = email;
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

    public String getEmail() { return  email; }


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
                ", email='" + email + '\'' +
                '}';
    }
}

