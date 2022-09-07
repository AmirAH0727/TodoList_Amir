package com.example.udemyTodoListe.todo;
import com.example.udemyTodoListe.enums.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TodoService {

	@Autowired
	private TodoRepository todoRepository;

	// Create
	public Todo creatTodoInDateBank(Todo newTodo){
		todoRepository.save(newTodo);
		return newTodo;
	}

	// All Todos
	public List<Todo> getAllTodos(String state, int limit, int offset){

		State userState = State.valueOf(state.toUpperCase());

		switch (userState) {
			case UNFINISHED:  return  todoRepository.getUnfinishedTodos(limit, offset);
			case All: return todoRepository.getAllTodos(limit,offset);
			default:
				return todoRepository.getAllTodos(limit,offset);
		}
	}

	// GET TODO BY ID
	public Todo findTodoById (int id){
		Optional <Todo> todoById = todoRepository.findById(id);
		return todoById.orElse(null);
	}

	// DELETE TODO BY ID
	public int deleteById (int id) {

		if (!todoRepository.existsById(id)){
			return -1;
		} else {
			todoRepository.deleteById(id);
			return id;
		}

	}

	// UPDATE Todo bei Id
	public int updateTodoById (int id, Todo todoToUpdate){

		Optional <Todo> updateTodo = todoRepository.findById(id);
		if (updateTodo.isEmpty()){
			return -1;
		}
		updateTodo.get().setDescription(todoToUpdate.getDescription());
		updateTodo.get().setIsDone(todoToUpdate.getIsDone());
		todoRepository.save(updateTodo.get());
			return  1;
	}

	// FIND BY NAME
	public Todo findByDescription (String name){
		return todoRepository.findByDescription(name);
	}

}



