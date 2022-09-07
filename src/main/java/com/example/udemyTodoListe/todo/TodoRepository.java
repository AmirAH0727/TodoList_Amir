package com.example.udemyTodoListe.todo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TodoRepository extends JpaRepository <Todo, Integer> {

    @Query(nativeQuery = true,
            value = "select * from todo ORDER by id limit ?1 offset ?2  ")
    List<Todo> getAllTodos(int limit, int offset);

    @Query(nativeQuery = true,
            value = "select * from todo where is_done=false ORDER by id limit ?1 offset ?2  ")
    List<Todo> getUnfinishedTodos(int limit, int offset);


    // SELECT 1 FROM todos WHERE name = x
    Todo findByDescription (String name);

}


