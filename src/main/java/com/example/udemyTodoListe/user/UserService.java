package com.example.udemyTodoListe.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

	private final static String USER_NOT_FOUND_MSG =
			"user with email %s not found";
	@Autowired
	UserRepository userRepository;

	// CREATE NEW USER
	public User createNewUser(User user) {
		return  userRepository.save(user);
	}

	// TODOS OF USER
	public User findTodosOfUser (int id){
		Optional <User> todoById = userRepository.findById(id);
		return todoById.orElse(null);
	}

	// USER VALIDATION
	public Optional<User> userValidation(String email, String password){
		return userRepository.findByEmailAndPassword(email, password);
	}


	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
			return (UserDetails) userRepository.findByEmail(email).orElseThrow(
					() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
		}
	}

