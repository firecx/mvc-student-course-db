package org.example.mvc.model;

public class Student {
    private String id;
    private String name;
    private String email;
    private String phone;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
