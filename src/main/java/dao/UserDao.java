package dao;

import entity.Student;
import entity.User;

import java.sql.*;

/**
 * Created by DiZi on 29.11.2015.
 */
public class UserDao {

    public static  final String FIND_USER = "SELECT FROM USER (LOGIN) VALUES (?)";

    public static final String CREATE_USER = "INSERT INTO COURSE (ID, NAME, COURSEDESCRIPTION) VALUES (DEFAULT, ?, ?)";

    public static final String DELETE_USER = "DELETE FROM COURSE WHERE ID = (?)";

    public static final  String UPDATE_USER = "UPDATE FROM COURSE WHERE ID = (?)";

    public User create (User user){
        return user;
    }

    public User update (long id, User user){
        return user;
    }

    public boolean delete (long id){
        return false;
    }

    public User find (long id){
        User user = new User();
        return user;
    }

    public User find (String login){
        User user = new User();
        Connection connection = null;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:~/course","GOD","GOD");
            preparedStatement = connection.prepareStatement(FIND_USER);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getResultSet();
            resultSet.next();
            long id = resultSet.getLong(1);
            user.setId(id);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ignored) {
            }
        }
        return user;
    }
}
