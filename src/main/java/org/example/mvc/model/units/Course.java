package org.example.mvc.model.units;

public class Course {
    private int id;
    private String name;
    private String description;
    private String duration;
    private int price;

    public Course(int id, String name, String description, String duration, int price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.price = price;
    }

    public Course(String name, String description, String duration, int price) {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDuration() {
        return duration;
    }

    public int getPrice() {
        return price;
    }
}
