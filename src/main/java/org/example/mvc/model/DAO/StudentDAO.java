package org.example.mvc.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.example.mvc.model.DataBasePSQL;
import org.example.mvc.model.units.Student;

public class StudentDAO {

    public boolean createStudent(Student student) throws SQLException {
        String sql = "INSERT INTO students (name, email, phone) VALUES (?, ?, ?)";

        try (Connection connection = DataBasePSQL.getInstance().getDataSource().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql, new String[]{"id"})) {
            statement.setString(1, student.getName());
            statement.setString(2, student.getEmail());
            statement.setString(3, student.getPhone());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return true;
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        }
    }

    public Student getStudentById(int id) throws SQLException {
        String sql = "SELECT * FROM users WHERE id = ?";

        try (Connection connection = DataBasePSQL.getInstance().getDataSource().getConnection(); 
            PreparedStatement statement = connection.prepareStatement(sql)){

                statement.setInt(1, id);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return mapStudentFromResultSet(resultSet);
                    }
                    return null;
                }

        }
    }

    public List<Student> getAllStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";

        try (Connection connection = DataBasePSQL.getInstance().getDataSource().getConnection(); 
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()){

            while (resultSet.next()) {
                students.add(mapStudentFromResultSet(resultSet));
            }

        }

        return students;
    }

    private Student mapStudentFromResultSet(ResultSet resultSet) throws SQLException {
        Student student = new Student( 
            resultSet.getInt("id"),
            resultSet.getString("name"),
            resultSet.getString("email"),
            resultSet.getString("phone")
        );
        return student;
    }
}
