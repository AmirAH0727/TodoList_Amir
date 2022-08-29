package com.example.udemyTodoListe.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Optional;

@Service
public class UserService {

	UserRepository userRepository;
	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	// CREATE NEW USER
	public User createNewUser(User user) {
		return  userRepository.save(user);
	}

	// Todos of users
	public User findTodosOfUser (int id){
		Optional <User> todoById = userRepository.findById(id);
		return todoById.orElse(null);
	}

	// User Validation
	public Optional<User> userValidation(String email, String password){
		return userRepository.findByEmailAndPassword(email, password);
	}

}
