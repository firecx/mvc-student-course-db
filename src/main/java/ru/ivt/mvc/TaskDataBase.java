package ru.ivt.mvc;

import java.util.ArrayList;
import java.util.List;

public class TaskDataBase {
    private static TaskDataBase instance;
    private List<Task> tasks;
    private static int nextId;

    private TaskDataBase() {
        tasks = new ArrayList<>();
        nextId = 1;
    }

    public static TaskDataBase getInstance() {
        if (instance == null) {
            instance = new TaskDataBase();
        }
        return instance;
    }

    public static int getNextId() {
        return nextId++;
    }

    public int addTask(Task task) {
        tasks.add(task);
        return task.getId();
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public Task markTaskAsCompleted(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setCompleted(true);
                return task;
            }
        }
        return null;
    }

    public boolean deleteTask(int id) {
        return tasks.removeIf(task -> task.getId() == id);
    }
}
