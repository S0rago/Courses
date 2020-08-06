package main;

import main.model.TodoTask;
import main.model.TodoTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class TaskController {

    @Autowired
    private TodoTaskRepository taskRepository;

    @GetMapping("/tasks/")
    public List<TodoTask> list() {
        Iterable<TodoTask> taskIterable = taskRepository.findAll();
        ArrayList<TodoTask> tasks = new ArrayList<>();
        for (TodoTask task : taskIterable) {
            tasks.add(task);
        }
        return tasks;
    }

    @PostMapping("/tasks/")
    public int add(TodoTask task) {
        TodoTask newTask = taskRepository.save(task);
        return newTask.getId();
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity get(@PathVariable int id) {
        Optional<TodoTask> optionalTask = taskRepository.findById(id);
        if (optionalTask.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(optionalTask.get(), HttpStatus.OK);
    }

    @PostMapping("/tasks/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        Optional<TodoTask> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            taskRepository.deleteById(id);
            return new ResponseEntity(optionalTask.get(), HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}
