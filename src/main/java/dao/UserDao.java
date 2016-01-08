package dao;

import connection.PooledConnection;

import entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DiZi on 29.11.2015.
 */
public class UserDao extends GenericDao<User> {

    private static final Logger log = LoggerFactory.getLogger(UserDao.class);
    private static  final String FIND_USER = "SELECT * FROM user WHERE id_user = (?)";
    private static final String CREATE_USER = "INSERT INTO user (id_user, login, password) VALUES (DEFAULT, ?, ?)";
    private static final String DELETE_USER = "DELETE FROM user WHERE id_user = (?)";
    private static final  String UPDATE_USER = "UPDATE user SET login = (?), password = (?), email = (?), name = (?), surname = (?) WHERE id_user = (?)";
    private static final  String SET_ROLE = "UPDATE user SET role = (?) WHERE id_user = (?)";
    private static final String FIND_USER_BY_NAME = "SELECT * FROM user WHERE login = (?)";
    private  static final String FIND_ALL = "SELECT * FROM user";
    private PooledConnection connection;
    private User user;

    /**
     *
     * @param connection
     */
    public UserDao (PooledConnection connection){
        this.connection = connection;
    }

    public UserDao() {

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
            throw new ExceptionDao("Error executing query", e);
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
            PreparedStatement ps = connection.prepareStatement(SET_ROLE);
            ps.setString(1, user.getRole());
            ps.setString(2, String.valueOf(user.getId()));
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new ExceptionDao("Error executing query",e);
        }
        return user;
    }

    public User update2(User user, int id){
        try {
            PreparedStatement ps = connection.prepareStatement(UPDATE_USER);
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getName());
            ps.setString(5, user.getSurname());
            ps.setString(6, String.valueOf(id));
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new ExceptionDao("Error executing query",e);
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
            throw new ExceptionDao("Error executing query", e);
        }
        return user;
    }

    /**
     * Find user by string (login)
     *
     * @return
     */
    @Override
    public User findBy(String login) {
        User user = new User();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_NAME);
            preparedStatement.setString(1, login);
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getResultSet();
            while (rs.next()) {
                int id2 = rs.getInt(1);
                String password = rs.getString(3);
                String role = rs.getString(4);
                String email = rs.getString(5);
                String name = rs.getString(6);
                String surname = rs.getString(7);
                user.setId(id2);
                user.setLogin(login);
                user.setPassword(password);
                user.setRole(role);
                user.setEmail(email);
                user.setName(name);
                user.setSurname(surname);
            }
        } catch (SQLException e) {
            throw new ExceptionDao("Error executing query", e);
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
        List list = new ArrayList();
        try {
            PreparedStatement ps = connection.prepareStatement(FIND_ALL);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                int id = rs.getInt(1);
                String login = rs.getString(2);
                String password = rs.getString(3);
                String role = rs.getString(4);
                String email = rs.getString(5);
                String name = rs.getString(6);
                String surname = rs.getString(7);
                user = new User();
                user.setId(id);
                user.setLogin(login);
                user.setPassword(password);
                user.setRole(role);
                user.setName(name);
                user.setSurname(surname);
                user.setEmail(email);
                list.add(user);
            }
        } catch (SQLException e) {
            throw new ExceptionDao("Error executing query", e);
        }
        return list;
    }

    @Override
    public List<User> findAllBy(int string) {
        return null;
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
            throw new ExceptionDao("Error executing query", e);
        }
        return false;
    }
}

