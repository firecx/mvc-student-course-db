package org.example.mvc.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Database {
    private static Database instance;

    public static Database getInstance(String dbPath) {
        if (instance == null) {
            instance = new Database(dbPath);
        }
        return instance;
    }

    private static String databasePath = "/WEB-INF/db";

    private static File STUDENTS_FILE;
    private static File COURSES_FILE;

    private Database(String docBase) {
        STUDENTS_FILE = new File(docBase + databasePath + "/students.json");
        if (!STUDENTS_FILE.exists()) {
            if (STUDENTS_FILE.getParentFile() != null) {
                STUDENTS_FILE.getParentFile().mkdirs();
            }
            try {
                STUDENTS_FILE.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        COURSES_FILE = new File(docBase + databasePath + "/courses.json");
        if (!COURSES_FILE.exists()) {
            if (COURSES_FILE.getParentFile() != null) {
                COURSES_FILE.getParentFile().mkdirs();
            }
            try {
                COURSES_FILE.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        try (FileWriter initializator = new FileWriter(STUDENTS_FILE)) {
            initializator.write("[]");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try (FileWriter initializator = new FileWriter(COURSES_FILE)) {
            initializator.write("[]");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        students = loadFromFile(COURSES_FILE, new TypeReference<ArrayList<Student>>() {
        });
        courses = loadFromFile(COURSES_FILE, new TypeReference<ArrayList<Course>>() {
        });

    }

    private ArrayList<Student> students;
    private ArrayList<Course> courses;

    private final ObjectMapper mapper = new ObjectMapper();

    private <T> ArrayList<T> loadFromFile(File file, TypeReference<ArrayList<T>> typeRef) {
        ArrayList<T> data = null;
        try {
            data = mapper.readValue(file, typeRef);
        } catch (StreamReadException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DatabindException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return data;
    }

    public void addStudent(String id, String name) {
        students.add(new Student(id, name));
        saveData(STUDENTS_FILE, students);
    }

    public void addCourse(String id, String name) {
        courses.add(new Course(id, name));
        saveData(COURSES_FILE, courses);
    }

    private void saveData(File file, List<?> data) {
        try {
            mapper.writeValue(file, data);
        } catch (StreamWriteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DatabindException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }
}
