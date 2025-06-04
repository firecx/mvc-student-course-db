package org.example.mvc.model;

public class Record {
    private String id;
    private String courseId;
    private String studentId;

    public Record (String id, String courseId, String studentId) {
        this.id = id;
        this.courseId = courseId;
        this.studentId = studentId;
        }

    public String getId() {
            return id;
        }

    public String courseId() {
            return courseId;
        }

    public String studentId() {
        return studentId;
    }


}
