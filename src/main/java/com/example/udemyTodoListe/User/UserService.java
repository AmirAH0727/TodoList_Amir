package com.example.udemyTodoListe.user;

import com.example.udemyTodoListe.registration.token.ConfirmationToken;
import com.example.udemyTodoListe.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
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
    public String signUpUser (User user){

        boolean userExists = userRepository.findById(user.getId())
                .isPresent();
        if(userExists){
            throw new IllegalStateException();
        }
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMonths(15),
                user
        );

        confirmationTokenService.saveConfirmationToke(confirmationToken);
        return token;


    }
}

