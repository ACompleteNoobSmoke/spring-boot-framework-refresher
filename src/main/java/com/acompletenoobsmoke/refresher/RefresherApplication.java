package com.acompletenoobsmoke.refresher;

import com.acompletenoobsmoke.refresher.person.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RefresherApplication {

    public static void main(String[] args) {
        SpringApplication.run(RefresherApplication.class, args);
    }

    @Bean
    CommandLineRunner commandlineRunner(ObjectMapper objectMapper) throws JsonProcessingException {
        String personString = "{\"id\":1,\"name\":\"Rock Lee\",\"age\":18}";
        Person person = objectMapper.readValue(personString, Person.class);
        System.out.println(person);
        System.out.println(objectMapper.writeValueAsString(person));
        return args -> {

        };
    }

}
