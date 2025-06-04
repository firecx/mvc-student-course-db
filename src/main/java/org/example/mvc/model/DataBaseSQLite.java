package org.example.mvc.model;

import java.io.File;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.sql.*;

public class DataBaseSQLite {

    private static volatile DataBaseSQLite instance;
    private String DATABASE = "WEB-INF/db/registry.db";
    private String DATABASE_PATH = "jdbc:sqlite:WEB-INF/db/registry.db";

    public static DataBaseSQLite getInstance() {
        if (instance == null) {
            instance = new DataBaseSQLite();
        }
        return instance;
    }

    private DataBaseSQLite() {
        File DATABASE_FILE = new File(DATABASE);

        if (DATABASE_FILE.getParentFile() != null) {
            DATABASE_FILE.getParentFile().mkdirs();
        }
        try {
            DATABASE_FILE.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection(DATABASE_PATH)) {
            createTableStudents(connection);
            createTableCourses(connection);
            createTableRecords(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTableStudents(Connection connection) throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS students (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT UNIQUE NOT NULL,
                phone TEXT,
                email TEXT);
                """;

        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTableCourses(Connection connection) throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS courses (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT UNIQUE NOT NULL,
                duration TEXT,
                description TEXT,
                price TEXT
                );
                """;

        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTableRecords(Connection connection) throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS records (
                courseID TEXT NOT NULL,
                studentID TEXT NOT NULL
                );
                """;

        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertDataStudent(String name, String phone, String email) throws SQLException {
        String sql = " INSERT INTO students(name, phone, email) VALUES(?, ?, ?)";

        try (PreparedStatement pstmt = DriverManager.getConnection(DATABASE_PATH).prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, phone);
            pstmt.setString(3, email);
            pstmt.executeUpdate();
        }
    }

    public void insertDataCourse(String name, String duration, String description, String price) throws SQLException {
        String sql = " INSERT INTO courses(name, duration, description, price) VALUES(?, ?, ?, ?)";

        try (PreparedStatement pstmt = DriverManager.getConnection(DATABASE_PATH).prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, duration);
            pstmt.setString(3, description);
            pstmt.setString(4, price);
            pstmt.executeUpdate();
        }
    }

    public String insertDataRecord(String courseName, String studentName) throws SQLException {

        String courseID;
        String studentID;

        // Запрос id курса
        try (Connection conn = DriverManager.getConnection(DATABASE_PATH)) {
            String sql = "SELECT id FROM courses WHERE name = ?";

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, courseName);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    courseID = rs.getString("id");
                } else {
                    String reply = "Курс '" + courseName + "' не найден.";
                    return reply;
                }
            }
        }

        // Запрос id студента
        try (Connection conn = DriverManager.getConnection(DATABASE_PATH)) {
            String sql = "SELECT id FROM students WHERE name = ?";

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, studentName);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    studentID = rs.getString("id");
                } else {
                    String reply = "Студент '" + studentName + "' не найден.";
                    return reply;
                }
            }
        }

        // Проверка на дубликат записи
        try (Connection conn = DriverManager.getConnection(DATABASE_PATH)) {
            // SQL-запрос для проверки существования записи
            String sql = "SELECT 1 FROM records WHERE courseID = ? AND studentID = ? LIMIT 1";

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, courseID); // Подставляем course_id
                stmt.setString(2, studentID); // Подставляем student_id

                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    String reply = "Запись существует!";
                    return reply;
                }
            }
        }

        // Запись студента на курс
        String sql = " INSERT INTO records(courseID, studentID) VALUES(?, ?)";

        try (PreparedStatement pstmt = DriverManager.getConnection(DATABASE_PATH).prepareStatement(sql)) {
            pstmt.setString(1, courseID);
            pstmt.setString(2, studentID);
            pstmt.executeUpdate();
        }

        return "Студент записан на курс";
    }

    public void readData(Connection connection) throws SQLException {
        String sql = "SELECT id, name FROM students";

        try (Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)) {

            System.out.println("\nСписок пользователей:");
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id") + "\t" + resultSet.getString("name"));
            }
        }
    }
}
