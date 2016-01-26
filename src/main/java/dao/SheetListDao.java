package dao;


import connection.PooledConnection;
import entity.SheetList;
import entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Malkov Nikifor on 29.11.2015.
 */
public class SheetListDao extends GenericDao<SheetList> {

    private static final Logger log = LoggerFactory.getLogger(SheetListDao.class);

    private static final String CREATE_PARTICIPANT_LIST = "INSERT INTO studentlist (id, id_course, id_user) VALUES (DEFAULT, ?, ?)";
    private static final String FIND_PARTICIPANT_LIST = "SELECT * FROM studentlist WHERE id = (?)";
    private static final String FIND_BY_COURSE_ID = "SELECT * FROM studentlist NATURAL JOIN user WHERE studentlist.id_course = (?)";
    private static final String DELETE_PARTICIPANT_LIST = "DELETE FROM studentlist WHERE id = (?)";
    private static final  String UPDATE_PARTICIPANT_LIST = "UPDATE studentlist SET score = (?), shortcomment = (?) WHERE id = (?)";
    private  static final String FIND_ALL = "SELECT * FROM studentlist";
    private static final String FIND_BY_STUDENT_ID = "SELECT * FROM studentlist JOIN course WHERE  course.id = studentlist.id_course AND studentlist.id_user = (?)";
    private PooledConnection connection;
    private SheetList patricipianList;

    /**
     *
     * @param connection
     */
    public SheetListDao(PooledConnection connection){
        this.connection = connection;
    }

    /**
     *
     * @param sheetList
     * @return
     */
    public SheetList create (SheetList sheetList){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_PARTICIPANT_LIST);
            preparedStatement.setString(1, String.valueOf(sheetList.getIdCourse()));
            preparedStatement.setString(2, String.valueOf(sheetList.getIdStudent()));
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            int id = resultSet.getInt(1);
            sheetList.setId(id);
        } catch (SQLException e) {
            log.error("Error executing query");
            throw new ExceptionDao(e);
        }
        return sheetList;
    }

    /**
     *
     * @param sheetList
     * @param id
     * @return
     */
    @Override
    public SheetList update(SheetList sheetList, int id){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PARTICIPANT_LIST);
            preparedStatement.setString(1, String.valueOf(sheetList.getScore()));
            preparedStatement.setString(2, sheetList.getShortComment());
            preparedStatement.setString(3, String.valueOf(id));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error("Error executing query");
            throw new ExceptionDao(e);
        }
        return sheetList;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public boolean delete(int id) {
        try {
            PreparedStatement ps = connection.prepareStatement(DELETE_PARTICIPANT_LIST);
            ps.setInt(1, id);
            ps.executeUpdate();
            if (ps.getUpdateCount() != 0){
                return true;
            }
        } catch (SQLException e) {
            log.error("Error executing query");
            throw new ExceptionDao(e);
        }
        return false;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public SheetList find(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_PARTICIPANT_LIST);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error("Error executing query");
            throw new ExceptionDao(e);
        }
        return null;
    }

    /**
     *
     * @param string
     * @return
     */
    @Override
    public SheetList findBy(String string) {
        return null;
    }

    /**
     *
     * @return
     */
    @Override
    public List<SheetList> findAll() {
        List<SheetList> list = null;
        try {
            PreparedStatement ps = connection.prepareStatement(FIND_ALL);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                int id = rs.getInt(1);
                int idCourse = rs.getInt(2);
                int idStudent = rs.getInt(3);
                int score = rs.getInt(4);
                String shortComment = String.valueOf(rs.getClob(5));
                patricipianList = new SheetList();
                patricipianList.setId(id);
                patricipianList.setIdCourse(idCourse);
                patricipianList.setIdStudent(idStudent);
                patricipianList.setScore(score);
                patricipianList.setShortComment(shortComment);
                list.add(patricipianList);
            }
        } catch (SQLException e) {
            log.error("Error executing query");
            throw new ExceptionDao(e);
        }
        return list;
    }

    @Override
    public List<SheetList> findAllBy(int course) {
        List list = new ArrayList();
        try {
            PreparedStatement ps = connection.prepareStatement(FIND_BY_COURSE_ID);
            ps.setInt(1, course);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                int id = rs.getInt(1);
                int idCourse = rs.getInt(2);
                int idStudent = rs.getInt(3);
                int score = rs.getInt(4);
                String shortComment = String.valueOf(rs.getClob(5));
                String name = rs.getString(10);
                String surname = rs.getString(11);
                patricipianList = new SheetList();
                patricipianList.setId(id);
                patricipianList.setIdCourse(idCourse);
                patricipianList.setIdStudent(idStudent);
                patricipianList.setScore(score);
                patricipianList.setShortComment(shortComment);
                patricipianList.setName(name);
                patricipianList.setSurname(surname);
                list.add(patricipianList);
            }
        } catch (SQLException e) {
            log.error("Error executing query");
            throw new ExceptionDao(e);
        }
        return list;
    }

    @Override
    public List<SheetList> findAllBy(long id_student) {
        List list = new ArrayList();
        try {
            PreparedStatement ps = connection.prepareStatement(FIND_BY_STUDENT_ID);
            ps.setLong(1, id_student);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                int id = rs.getInt(1);
                int idCourse = rs.getInt(2);
                int idStudent = rs.getInt(3);
                int score = rs.getInt(4);
                String shortComment = rs.getString(5);
                String courseName = rs.getString(7);
                patricipianList = new SheetList();
                patricipianList.setId(id);
                patricipianList.setIdCourse(idCourse);
                patricipianList.setIdStudent(idStudent);
                patricipianList.setScore(score);
                patricipianList.setShortComment(shortComment);
                patricipianList.setCourseName(courseName);
                list.add(patricipianList);
            }
        } catch (SQLException e) {
            log.error("Error executing query");
            throw new ExceptionDao(e);
        }
        return list;
    }

    @Override
    public SheetList update2(SheetList sheetList, int id) {
        return null;
    }
}