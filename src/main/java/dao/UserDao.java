package dao;

import entity.User;

import java.sql.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by DiZi on 29.11.2015.
 */
public class UserDao {

    private static final Logger log = LoggerFactory.getLogger(UserDao.class);

    public static  final String FIND_USER = "SELECT * FROM user WHERE login = (?)";

    public static final String CREATE_USER = "INSERT INTO user (id, login, password) VALUES (DEFAULT, ?, ?)";

    public static final String DELETE_USER = "DELETE FROM user WHERE id = (?)";

    public static final  String UPDATE_USER = "UPDATE FROM user WHERE id = (?)";

    public User create (User user){

        Connection connection = null;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:~/course","GOD","GOD");
            preparedStatement = connection.prepareStatement(CREATE_USER);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            int id = resultSet.getInt(1);
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
            preparedStatement.setString(1,user.getLogin());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getResultSet();
            resultSet.next();
            int id = resultSet.getInt(1);
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
