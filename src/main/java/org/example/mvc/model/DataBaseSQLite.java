package org.example.mvc.model;

import java.io.File;
import java.io.IOException;
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

    private DataBaseSQLite(){
        File DATABASE_FILE = new File(DATABASE);

        if (DATABASE_FILE.getParentFile() != null) {
            DATABASE_FILE.getParentFile().mkdirs();
        }
        try {
            DATABASE_FILE.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(Connection connection = DriverManager.getConnection(DATABASE_PATH)) {
            createTableStudents(connection);
            createTableCourses(connection);
        }
        catch (SQLException e) {
           e.printStackTrace();
        }
    }

    private void createTableStudents(Connection connection) throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS students (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL,
                phone TEXT,
                email TEXT);
                """;

        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTableCourses(Connection connection) throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS courses (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL,
                duration TEXT,
                description TEXT,
                price TEXT
                );
                """;

        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
        catch (SQLException e) {
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

    public void readData(Connection connection) throws SQLException {
        String sql = "SELECT id, name FROM students";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            System.out.println("\nСписок пользователей:");
            while (resultSet.next()) {
                System.out.println(
                        resultSet.getInt("id") + "\t" +
                                resultSet.getString("name")
                );
            }
        }
    }
}