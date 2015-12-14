package dao.factory;

import connection.PooledConnection;
import entity.Lecturer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

/**
 * Created by DiZi on 29.11.2015.
 */
public class LecturerDao extends GenericDao<Lecturer> {
    private static final Logger log = LoggerFactory.getLogger(LecturerDao.class);

    public static final String CREATE_LECTURER = "INSERT INTO LECTURER (ID, NAME, SURNAME) VALUES (DEFAULT, ?, ?)";

    public static final String FIND_LECTURER = "SELECT * FROM LECTURER WHERE ID = (?)";

    public static final String DELETE_LECTURER = "DELETE FROM LECTURER WHERE ID = (?)";

    public static final  String UPDATE_LECTURER = "UPDATE LECTURER SET NAME = (?), SURNAME = (?) WHERE ID = (?)";

    private PooledConnection connection;

    public LecturerDao (PooledConnection connection){
        this.connection = connection;
    }

    @Override
    public Lecturer create (Lecturer lecturer){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_LECTURER);
            preparedStatement.setString(1, lecturer.getName());
            preparedStatement.setString(2, lecturer.getSurname());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            long id = resultSet.getLong(1);
            lecturer.setId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ignored) {
            }
        }
        return lecturer;
    }

    public  boolean delete(long id){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_LECTURER);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  false;
    }

    @Override
    public Lecturer update(Lecturer lecturer, int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_LECTURER);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lecturer;
    }

    @Override
    public boolean delete(int i) {
        return false;
    }

    @Override
    public Lecturer find(int i) {
        return null;
    }
}