package Date;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataBase {
    private static volatile DataBase instance;
    private List<Student> students;
    private List<Course> courses;
    private final ObjectMapper mapper = new ObjectMapper();
    private static final String STUDENTS_FILE = "DataBase/students.json";
    private static final String COURSES_FILE = "DataBase/cours.json";

    private DataBase() throws IOException {
        // Загрузка данных из файлов
        loadData();
    }

    public static DataBase getInstance() throws IOException {
        if (instance == null) {
            synchronized (DataBase.class) {
                if (instance == null) {
                    instance = new DataBase();
                }
            }
        }
        return instance;
    }

    private void loadData() throws IOException {
        // Загрузка студентов
        students = loadFromFile(STUDENTS_FILE, new TypeReference<ArrayList<Student>>() {});

        // Загрузка курсов
        courses = loadFromFile(COURSES_FILE, new TypeReference<ArrayList<Course>>() {});
    }

    private <T> List<T> loadFromFile(String filename, TypeReference<ArrayList<T>> typeRef) throws IOException {
        File file = new File(filename);
        if (!file.exists() || file.length() == 0) {
            return Collections.synchronizedList(new ArrayList<>());
        }

        try {
            List<T> data = mapper.readValue(file, typeRef);
            return data != null ? Collections.synchronizedList(data) :
                    Collections.synchronizedList(new ArrayList<>());
        } catch (IOException e) {
            // Если ошибка чтения, вернуть пустой список
            throw new RuntimeException(e);
            //return Collections.synchronizedList(new ArrayList<>());
        }
    }

    public synchronized void addStudent(String id, String name) throws IOException {
        students.add(new Student(id, name));
        saveData(STUDENTS_FILE, students);
    }

    public synchronized void addCourse(Course course) throws IOException {
        courses.add(course);
        saveData(COURSES_FILE, courses);
    }

    private synchronized void saveData(String filename, List<?> data) throws IOException {
        mapper.writeValue(new File(filename), data);
    }

    public List<Student> getStudents() {
        return Collections.unmodifiableList(students);
    }

    public List<Course> getCourses() {
        return Collections.unmodifiableList(courses);
    }
}