package com.example.udemyTodoListe.registration;


import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class EmailValidator implements Predicate <String> {


    // true if the input argument matches the predicate, otherwise false
    @Override
    public boolean test(String s) {
        return true;
    }
}
