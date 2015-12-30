package dao;

import connection.PooledConnection;
import dao.ExceptionDao;
import entity.Lecturer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.List;

/**
 * Created by DiZi on 29.11.2015.
 */
public class LecturerDao extends GenericDao<Lecturer> {

    private static final Logger log = LoggerFactory.getLogger(LecturerDao.class);

    /**
     *
     */
    public static final String CREATE_LECTURER = "INSERT INTO LECTURER (ID, NAME, SURNAME) VALUES (DEFAULT, ?, ?)";

    /***
     *
     */
    public static final String FIND_LECTURER = "SELECT * FROM LECTURER WHERE ID = (?)";

    /**
     *
     */
    public static final String DELETE_LECTURER = "DELETE FROM LECTURER WHERE ID = (?)";

    /**
     *
     */
    public static final  String UPDATE_LECTURER = "UPDATE LECTURER SET NAME = (?), SURNAME = (?) WHERE ID = (?)";

    /**
     *
     */
    public  static final String FIND_ALL = "SELECT * FROM lecturer";

    /**
     *
     */
    private PooledConnection connection;

    /**
     *
     */
    private Lecturer lecturer;

    /**
     *
     * @param connection
     */
    public LecturerDao (PooledConnection connection){
        this.connection = connection;
    }

    /**
     * Create teacher
     *
     * @param lecturer
     * @return
     */
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
            throw new ExceptionDao("Проблемы при создании курса", e);
        }
        return lecturer;
    }

    /**
     * Update teacher by id
     *
     * @param lecturer
     * @param id
     * @return
     */
    @Override
    public Lecturer update(Lecturer lecturer, int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_LECTURER);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new ExceptionDao("Проблемы при создании курса", e);
        }
        return lecturer;
    }

    /**
     * Delete teacher by id
     *
     * @param id
     * @return
     */
    @Override
    public boolean delete(int id) {
        try {
            PreparedStatement ps = connection.prepareStatement(DELETE_LECTURER);
            ps.setInt(1, id);
            ps.executeUpdate();
            if (ps.getUpdateCount() != 0){
                return true;
            }
        } catch (SQLException e) {
            throw new ExceptionDao("Проблемы при создании курса", e);
        }
        return false;
    }

    /**
     *
     * @param i
     * @return
     */
    @Override
    public Lecturer find(int i) {
        return null;
    }

    /**
     * Find teacher by string ()
     *
     * @param string
     * @return
     */
    @Override
    public Lecturer findBy(String string) {
        return null;
    }

    /***
     * Find all teachers
     *
     * @return
     */
    @Override
    public List<Lecturer> findAll() {
        List<Lecturer> list = null;
        try {
            PreparedStatement ps = connection.prepareStatement(FIND_ALL);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String surName = rs.getString(3);
                lecturer = new Lecturer();
                lecturer.setId(id);
                lecturer.setName(name);
                lecturer.setSurname(surName);
                list.add(lecturer);
            }
        } catch (SQLException e) {
            throw new ExceptionDao("Проблемы при создании курса", e);
        }
        return list;
    }
}