package main;

import main.model.TodoTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TaskStorage {
    private static int currentId = 1;
    private static HashMap<Integer, TodoTask> todoTasks = new HashMap<>();

    public synchronized static List<TodoTask> getAllTasks() {
        return new ArrayList<>(todoTasks.values());
    }

    public synchronized static int addTask(TodoTask task) {
        int id = currentId++;
        task.setId(id);
        todoTasks.put(id, task);
        return id;
    }

    public synchronized static TodoTask getTask(int taskId) {
        return todoTasks.getOrDefault(taskId, null);
    }

    public synchronized static TodoTask removeTask(int id) {
        return todoTasks.remove(id);
    }
}
