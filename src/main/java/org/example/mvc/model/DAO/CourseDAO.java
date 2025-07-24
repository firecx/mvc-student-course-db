package org.example.mvc.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.example.mvc.model.DataBasePSQL;
import org.example.mvc.model.units.Course;

public class CourseDAO {

    public boolean createCourse(Course course) throws SQLException {
        String sql = "INSERT INTO courses (name, description, duration, price) VALUES (?, ?, ?, ?)";

        try (Connection connection = DataBasePSQL.getInstance().getDataSource().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql, new String[]{"id"})) {
            statement.setString(1, course.getName());
            statement.setString(2, course.getDescription());
            statement.setString(3, course.getDuration());
            statement.setInt(4, course.getPrice());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating course failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return true;
                }
                else {
                    throw new SQLException("Creating course failed, no ID obtained.");
                }
            }
        }
    }

    public Course getCourseById(int id) throws SQLException {
        String sql = "SELECT * FROM courses WHERE id = ?";

        try (Connection connection = DataBasePSQL.getInstance().getDataSource().getConnection(); 
            PreparedStatement statement = connection.prepareStatement(sql)){

                statement.setInt(1, id);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return mapCourseFromResultSet(resultSet);
                    }
                    return null;
                }

        }
    }

    public Course getCourseByName(String name) throws SQLException {
        String sql = "SELECT * FROM courses WHERE name = ?";

        try (Connection connection = DataBasePSQL.getInstance().getDataSource().getConnection(); 
            PreparedStatement statement = connection.prepareStatement(sql)){

                statement.setString(1, name);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return mapCourseFromResultSet(resultSet);
                    }
                    return null;
                }

        }
    }

    public List<Course> getAllCourses() throws SQLException {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM courses";

        try (Connection connection = DataBasePSQL.getInstance().getDataSource().getConnection(); 
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()){

            while (resultSet.next()) {
                courses.add(mapCourseFromResultSet(resultSet));
            }

        }

        return courses;
    }

    private Course mapCourseFromResultSet(ResultSet resultSet) throws SQLException {
        Course course = new Course( 
            resultSet.getInt("id"),
            resultSet.getString("name"),
            resultSet.getString("description"),
            resultSet.getString("duration"),
            resultSet.getInt("price")
        );
        return course;
    }
}
