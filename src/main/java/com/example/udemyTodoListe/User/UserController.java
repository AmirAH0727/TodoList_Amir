package com.example.udemyTodoListe.User;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import javax.validation.Valid;



@RestController
@RequestMapping("/td")
public class UserController {

	UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	private  static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@PostMapping("/createUser")
	public ResponseEntity <User> createUser (@Valid @RequestBody  User userBody){

		LOGGER.info(
				"RequestBody: email: {}, password: {}",
				userBody.getEmail(),
				userBody.getPassword()
		);

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
