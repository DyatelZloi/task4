package dao;

import model.OptionalCourse;

import java.sql.*;

/**
 * Created by DiZi on 25.11.2015.
 */
public class CourseDao {

    public static final String CREATE_COURSE = "INSERT INTO COURSE (ID, NAME, COLUMN_3) VALUES (DEFAULT, ?, ?)";

    public OptionalCourse create (OptionalCourse course){
        Connection connection = null;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:~/course","GOD","GOD");
            preparedStatement = connection.prepareStatement(CREATE_COURSE);
            preparedStatement.setString(1, course.getName());
            preparedStatement.setString(2, course.getCourseDescription());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            long id = resultSet.getLong(1);
            course.setId(id);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ignored) {
            }
        }
        return course;
    }

}