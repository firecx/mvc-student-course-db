package org.example.mvc.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.example.mvc.model.DataBasePSQL;
import org.example.mvc.model.units.Course;
import org.example.mvc.model.units.Student;
import  org.example.mvc.model.units.Record;

public class RecordDAO {

    public boolean createRecord(Course course, Student student) throws SQLException {
        String sql = "INSERT INTO records (courseId, studentId) VALUES (?, ?)";

        try (Connection connection = DataBasePSQL.getInstance().getDataSource().getConnection();
        PreparedStatement statement = connection.prepareStatement(sql, new String[]{"courseId", "studentId"})) {
            statement.setInt(1, course.getId());
            statement.setInt(2, student.getId());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating record failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return true;
                }
                else {
                    throw new SQLException("Creating record failed, no ID obtained.");
                }
            }
        }
    }

    private Record mapRecordFromResultSet(ResultSet resultSet) throws SQLException {
        
        Record record = new Record( 
            resultSet.getInt("courseId"),
            resultSet.getInt("studentId")
        );

        return record;
    }
}
