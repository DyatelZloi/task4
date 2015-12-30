package dao;

import connection.PooledConnection;
import dao.ExceptionDao;

import entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.List;

/**
 * Created by DiZi on 29.11.2015.
 */
public class UserDao extends GenericDao<User> {

    private static final Logger log = LoggerFactory.getLogger(UserDao.class);

    /**
     *
     */
    public static  final String FIND_USER = "SELECT * FROM user WHERE ID = (?)";

    /**
     *
     */
    public static final String CREATE_USER = "INSERT INTO user (ID, LOGIN, PASSWORD) VALUES (DEFAULT, ?, ?)";

    /**
     *
     */
    public static final String DELETE_USER = "DELETE FROM user WHERE id = (?)";

    /**
     *
     */
    public static final  String UPDATE_USER = "UPDATE user SET login = (?), password = (?) WHERE ID = (?)";

    /**
     *
     */
    private static final String FIND_USER_BY_NAME = "SELECT * FROM user WHERE login = (?)";

    /**
     *
     */
    public  static final String FIND_ALL = "SELECT * FROM user";

    /**
     *
     */
    private PooledConnection connection;

    /**
     *
     */
    private User user;

    /**
     *
     * @param connection
     */
    public UserDao (PooledConnection connection){
        this.connection = connection;
    }

    /**
     * Create user
     *
     * @param user
     * @return
     */
    @Override
    public User create (User user){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            int id = resultSet.getInt(1);
            user.setId(id);
        } catch (SQLException e) {
            throw new ExceptionDao("Проблемы при создании курса", e);
        }
        return user;
    }

    /**
     * Update user by id
     *
     * @param user
     * @param id
     * @return
     */
    @Override
    public User update(User user, int id) {
        try {
            PreparedStatement ps = connection.prepareStatement(UPDATE_USER);
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.setInt(3, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new ExceptionDao("Ошибка при выполнении запроса",e);
        }
        return user;
    }

    /**
     * Find user by id
     *
     * @param id
     * @return
     */
    @Override
    public User find(int id) {
        User user = new User();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getResultSet();
            while (rs.next()) {
                int id2 = rs.getInt(1);
                String login = rs.getString(2);
                String password = rs.getString(3);
                user.setId(id2);
                user.setLogin(login);
                user.setPassword(password);
            }
        } catch (SQLException e) {
            throw new ExceptionDao("Проблемы при создании курса", e);
        }
        return user;
    }

    /**
     * Find user by string (login)
     *
     * @param string
     * @return
     */
    @Override
    public User findBy(String string) {
        User user = new User();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_NAME);
            preparedStatement.setString(1, string);
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getResultSet();
            while (rs.next()) {
                int id2 = rs.getInt(1);
                String login = rs.getString(2);
                String password = rs.getString(3);
                String role = rs.getString(4);
                user.setId(id2);
                user.setLogin(login);
                user.setPassword(password);
                user.setRole(role);
            }
        } catch (SQLException e) {
            throw new ExceptionDao("Проблемы при создании курса", e);
        }
        return user;
    }

    /**
     * Find all users
     *
     * @return list user
     */
    @Override
    public List<User> findAll() {

        List<User> list = null;
        try {
            PreparedStatement ps = connection.prepareStatement(FIND_ALL);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                int id = rs.getInt(1);
                String login = rs.getString(2);
                String password = rs.getString(3);
                user = new User();
                user.setId(id);
                user.setLogin(login);
                user.setPassword(password);
                list.add(user);
            }
        } catch (SQLException e) {
            throw new ExceptionDao("Проблемы при создании курса", e);
        }
        return list;
    }

    /**
     * Delete user by id
     *
     * @param id
     * @return
     */
    @Override
    public boolean delete (int id) {

        try {
            PreparedStatement ps = connection.prepareStatement(DELETE_USER);
            ps.setInt(1, id);
            ps.executeUpdate();
            if (ps.getUpdateCount() != 0) {
                return true;
            }
        } catch (SQLException e) {
            throw new ExceptionDao("Проблемы при создании курса", e);
        }
        return false;
    }
}

