package dao;

import entity.Lecturer;

import java.sql.*;

/**
 * Created by DiZi on 29.11.2015.
 */
public class LecturerDao {
    public static final String CREATE_LECTURER = "INSERT INTO LECTURER (ID, NAME, SURNAME) VALUES (DEFAULT, ?, ?)";

    public Lecturer create (Lecturer lecturer){
        Connection connection = null;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:~/course","GOD","GOD");
            preparedStatement = connection.prepareStatement(CREATE_LECTURER);
            preparedStatement.setString(1, lecturer.getName());
            preparedStatement.setString(2, lecturer.getSurname());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            long id = resultSet.getLong(1);
            lecturer.setId(id);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ignored) {
            }
        }
        return lecturer;
    }

}