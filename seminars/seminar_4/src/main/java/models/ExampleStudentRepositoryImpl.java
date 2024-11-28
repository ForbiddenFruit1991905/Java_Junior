package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class ExampleStudentRepositoryImpl implements ExampleStudentsRepository{
    @Override
    public void add(Student item) throws SQLException {
        String URL = "jdbc:mysql://localhost:3306";
        String USER = "root";
        String PASSWORD = "!ForbiddenFruit19911905";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);) {
            String insertDataSQL = "INSERT INTO students (name, age) VALUES (?, ?);";
            try (PreparedStatement statement = connection.prepareStatement(insertDataSQL)) {
                statement.setString(1, item.getName());
                statement.setInt(2, item.getAge());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        }

    @Override
    public void update(Student item) {

    }

    @Override
    public void delete(Student item) {

    }

    @Override
    public Student getById(Integer item) {
        return null;
    }

    @Override
    public Collection<Student> getAll() {
        return List.of();
    }
}



