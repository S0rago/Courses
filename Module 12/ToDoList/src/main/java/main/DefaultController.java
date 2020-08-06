package main;

import main.model.TodoTask;
import main.model.TodoTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
public class DefaultController {

    @Autowired
    TodoTaskRepository taskRepository;

    @RequestMapping("/")
    public String index(Model model) {
        Iterable<TodoTask> taskIterable = taskRepository.findAll();
        ArrayList<TodoTask> tasks = new ArrayList<>();
        for (TodoTask task : taskIterable) {
            tasks.add(task);
        }
        model.addAttribute("tasks", tasks);
        model.addAttribute("taskCount", tasks.size());
        return "index";
    }
}
