package com.example.udemyTodoListe.Todo;

import com.example.udemyTodoListe.validition.GetTodosValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/td")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @Autowired
    private GetTodosValidator valid;

    // ALL TODOS
    @GetMapping("/all")
    public ResponseEntity<List<Todo>> getAllTodos(@RequestParam String state, @RequestParam int limit, @RequestParam int offset) {

        List<Map<String, String>> errors = valid.validTodo(state,limit,offset);
        try {
            if (errors.size() > 0) {
                return  new ResponseEntity(errors, HttpStatus.BAD_REQUEST);
            }
            final List<Todo> todosList = this.todoService.getAllTodos(state, limit, offset);
            if (todosList.size() == 0) {
                Map<String, String> bodyErrors = new HashMap<>();
                bodyErrors.put("Code", "29");
                bodyErrors.put("Massage", "Empty list of Todos");
                return new ResponseEntity(bodyErrors, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(todosList, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // CREATE TODO
    @PostMapping("/ccTodo")
    public ResponseEntity<Todo> createTodo(@Valid @RequestBody  Todo newTodo) {
        Todo newCreatedTodo = todoService.creatTodoInDateBank(newTodo);
        return new ResponseEntity<>(newCreatedTodo, HttpStatus.OK);
    }

    // FIND TODO BY ID
    @GetMapping("/getByID")
    public ResponseEntity<Todo> getByID(@RequestParam(value = "id") int id) {
        Todo foundTodoById = todoService.findTodoById(id);
        Map<String, String> errors = new HashMap<>();
        try {
            if (!(id <= 0 || foundTodoById == null)) {
                return new ResponseEntity<Todo>(foundTodoById, HttpStatus.FOUND);
            } else {
                errors.put("status", "404");
                errors.put("description", "Todo not found");
                return new ResponseEntity(errors, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // DELETE Todo
    @DeleteMapping("/delete")
    public ResponseEntity<HttpStatus> deleteById(@RequestParam(value = "id") int id) {
        try {
            Map<String, String> errors = new HashMap<>();
            int result = todoService.deleteById(id);
            if (result == -1) {
                errors.put("description", "Todo not found");
                return new ResponseEntity(errors, HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                errors.put("description", "Todo deleted");
                return new ResponseEntity(errors, HttpStatus.OK);
            }
        } catch (Exception e) {

            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // UPDATE Todo
    @PutMapping("/edit")
    public ResponseEntity<HttpStatus> updateTodoById(@RequestParam int id, @RequestBody Todo todoToUpdate) {
        try{
            Map<String, String> massages = new HashMap<>();
            int result = todoService.updateTodoById(id, todoToUpdate);
            if (result == -1) {
                massages.put("Description", "Todo not found");
                return new ResponseEntity(massages, HttpStatus.NOT_FOUND);
            } else {
                massages.put("Description", "Todo is updated");
                return new ResponseEntity(massages, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // FIND BY Description
    @GetMapping("/findByName")
    public ResponseEntity<Todo> findByDescription(@RequestParam(value = "name") String name) {
        Todo foundTodoByName = todoService.findByDescription(name);
        return new ResponseEntity<Todo>(foundTodoByName, HttpStatus.FOUND);
    }
}
