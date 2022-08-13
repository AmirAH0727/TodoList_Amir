package com.example.udemyTodoListe.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
public class UserController {
	@Autowired
	UserService userService;
	@PostMapping("/createUser")
	public ResponseEntity <User> createUser (@Valid @RequestBody User userBody){
		User newUser = userService.createNewUser(userBody);
		return  new ResponseEntity<User>(newUser, HttpStatus.CREATED);
	}
	@GetMapping("/userTodos")
	public ResponseEntity<User> getUserTodos (@RequestParam("id") int id){
		User userTodosList = userService.findTodosOfUser(id);
		return  new ResponseEntity<User>(userTodosList, HttpStatus.FOUND);
	}
	@GetMapping("/validate")
	public boolean userValidate (@RequestParam(value = "email") String email,
								 @RequestParam(value = "password") String password){
		var valid = userService.userValidation(email,password);
		return valid.isPresent();
	}
}
