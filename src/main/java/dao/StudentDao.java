package dao;

import entity.OptionalCourse;
import entity.Student;

import java.sql.*;

/**
 * Created by DiZi on 29.11.2015.
 */
public class StudentDao {
    public static final String CREATE_STUDENT = "INSERT INTO STUDENT (ID, NAME, SURNAME) VALUES (DEFAULT, ?, ?)";

    public Student create (Student student){
        //TO DO
        // Вынести конекшн
        // Должен быть отдельный класс
        Connection connection = null;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:~/course","GOD","GOD");
            preparedStatement = connection.prepareStatement(CREATE_STUDENT);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getSurname());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            long id = resultSet.getLong(1);
            student.setId(id);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ignored) {
            }
        }
        return student;
    }
}