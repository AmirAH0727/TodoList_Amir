package com.example.udemyTodoListe.validition;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


// Validation for FIND ALL Todo Methode

@Configuration
@Component
public class Valid {

    public List<Map<String, String>> validTodo(String state, int limit, int offset) {

        List <Map<String, String>> body = new ArrayList<>();

        if (!state.toUpperCase().equals("ALL") && !state.toUpperCase().equals("UNFINISHED")) {
            Map<String, String> error = new HashMap<>();
            error.put("code", "STATE_INVALID");
            error.put("message", "state must be ALL or UNFINISHED");
            body.add(error);
        }

        if (limit < 0) {
            Map<String, String> error = new HashMap<>();
            error.put("code", "LIMIT_MIN");
            error.put("message", "limit must be greater or equal to 0");
            body.add(error);
        }
        if (limit > 10) {
            Map<String, String> error = new HashMap<>();
            error.put("code", "LIMIT_MAX");
            error.put("message", "limit must be less or equal to 10");
            body.add(error);
        }
        if (offset < 0) {
            Map<String, String> error = new HashMap<>();
            error.put("code", "OFFSET_MIN");
            error.put("message", "offset must be greater or equal to 0");
            body.add(error);
        }
        if (offset > 100) {
            Map<String, String> error = new HashMap<>();
            error.put("code", "OFFSET_MAX");
            error.put("message", "offset must be less or equal to 100");
            body.add(error);
        }

        return body;
    }
}
