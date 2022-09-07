package com.example.udemyTodoListe.registration.token;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;

    // SAVE
    public void saveConfirmationToke(ConfirmationToken token){
        confirmationTokenRepository.save(token);
    }

    // GET
    public Optional<ConfirmationToken> getToken (String token){
        return  confirmationTokenRepository.findByToken(token);
    }

    // UPDATE
    public int setConfirmedAt(String token) {
        return confirmationTokenRepository.updateConfirmedAt(
                token, LocalDateTime.now());
    }
}
