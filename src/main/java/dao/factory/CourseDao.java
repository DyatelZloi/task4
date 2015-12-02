package dao.factory;

import entity.OptionalCourse;

import java.sql.*;

/**
 * Created by DiZi on 01.12.2015.
 */
class CourseDao extends DaoFactory {     public static final String CREATE_COURSE = "INSERT INTO COURSE (ID, NAME, COLUMN_3) VALUES (DEFAULT, ?, ?)";

    public OptionalCourse create (OptionalCourse course){
        //TO DO
        // Вынести конекшн
        // Должен быть отдельный класс
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