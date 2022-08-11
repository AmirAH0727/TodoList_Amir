package com.example.udemyTodoListe.Todo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TodoRepository extends JpaRepository <Todo, Integer> {

    // SELECT 1 FROM todos WHERE name = x
    Todo findByDescription (String name);

}


