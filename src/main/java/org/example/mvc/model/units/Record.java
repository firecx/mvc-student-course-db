package org.example.mvc.model.units;

public class Record {
    private int courseId;
    private int studentId;

    public Record(int courseId, int studentId) {
        this.courseId = courseId;
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public int getStudentId() {
        return studentId;
    }

}
