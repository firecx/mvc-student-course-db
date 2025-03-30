package ru.ivt.mvc;

public class Task {
    private int id;
    private String description;
    private boolean isCompleted;

    public Task(int id, String description) {
        this.id = id;
        this.description = description;
        this.isCompleted = false;
    }

    // Геттеры и сеттеры
    public int getId() { return id; }
    public String getDescription() { return description; }
    public boolean isCompleted() { return isCompleted; }
    public void setCompleted(boolean completed) { isCompleted = completed; }
}
