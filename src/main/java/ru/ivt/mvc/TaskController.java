package ru.ivt.mvc;

import java.util.List;

public class TaskController {
    private TaskDataBase dataBase;
    private TaskView view;

    public TaskController(TaskDataBase dataBase) {
        this.dataBase = dataBase;
        this.view = new TaskView();
    }

    public void addTask(String description) {
        Task task = new Task(dataBase.getNextId(), description);
        int id = dataBase.addTask(task);
        view.showMessage("Задача добавлена. ID:"+String.valueOf(id));
    }

    public void viewTasks() {
        List<Task> tasks = dataBase.getAllTasks();
        view.displayTasks(tasks);
    }

    public void markTaskAsCompleted(int id) {
        dataBase.markTaskAsCompleted(id);
        view.showMessage("Задача отмечена как выполненная. ID:"+String.valueOf(id));
    }

    public void deleteTask(int id) {
        dataBase.deleteTask(id);
        view.showMessage("Задача удалена. ID:"+String.valueOf(id));
    }
}
