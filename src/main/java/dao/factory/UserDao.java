package dao.factory;

import connection.ConnectionPool;
import connection.PooledConnection;
import dao.DaoException;

import entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

/**
 * Created by DiZi on 29.11.2015.
 */
public class UserDao extends GenericDao<User> {

    private static final Logger log = LoggerFactory.getLogger(UserDao.class);

    public static  final String FIND_USER = "SELECT * FROM USER WHERE ID = (?)";

    public static final String CREATE_USER = "INSERT INTO USER (ID, LOGIN, PASSWORD) VALUES (DEFAULT, ?, ?)";

    public static final String DELETE_USER = "DELETE FROM user WHERE id = (?)";

    public static final  String UPDATE_USER = "UPDATE USER SET LOGIN = (?), PASSWORD = (?) WHERE ID = (?)";

    public static final String DRIVER_CLASS_NAME = "org.h2.Driver";

    public UserDao(PooledConnection connection) {
        super(connection);
    }

    @Override
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

    @Override
    public User update(User user, int id) {
        ConnectionPool pool = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        pool = ConnectionPool.getInstance();
        pool.setDriverClassName(DRIVER_CLASS_NAME);
        pool.setUserName("GOD");
        pool.setPassword("GOD");
        pool.setUrl("jdbc:h2:~/course");
        pool.setConnectionNumber(4);
        pool.initConnections();
        //вынести в инициализаццию конекшена, чтобы не прописывать такое количество методов
        con = pool.getConnection();
        // вынести в фабрику использование конекшена
        try {
            ps = con.prepareStatement(UPDATE_USER);
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.setInt(3, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Ошибка при выполнении запроса",e);
        } finally {
            try {
                con.close();
            } catch (Exception ignored) {
            }
        }
        return user;
    }

    @Override
    public boolean delete(int i) {
        return false;
    }

    @Override
    public User find(int id) {
        User user = new User();
        Connection connection = null;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        ResultSet rs = null;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:~/course","GOD","GOD");
            preparedStatement = connection.prepareStatement(FIND_USER);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            rs = preparedStatement.getResultSet();
            while (rs.next()) {
                int id2 = rs.getInt(1);
                String login = rs.getString(2);
                String password = rs.getString(3);
                user.setId(id2);
                user.setLogin(login);
                user.setPassword(password);
            }
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

    public User update (String string, User user){
        return user;
    }

    public boolean delete (long id){
        return false;
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

