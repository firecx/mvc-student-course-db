package org.example.mvc.model.units;

public class Record {
    private String courseId;
    private String studentId;

    public Record(String courseId, String studentId) {
        this.courseId = courseId;
        this.studentId = studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getStudentId() {
        return studentId;
    }

}
