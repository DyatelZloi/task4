package dao;


import connection.PooledConnection;
import entity.ParticipantList;
import entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DiZi on 29.11.2015.
 */
public class SheetListDao extends GenericDao<ParticipantList> {

    private static final Logger log = LoggerFactory.getLogger(SheetListDao.class);

    private static final String CREATE_PARTICIPANT_LIST = "INSERT INTO studentlist (id, id_course, id_user) VALUES (DEFAULT, ?, ?)";
    private static final String FIND_PARTICIPANT_LIST = "SELECT * FROM studentlist WHERE id = (?)";
    private static final String FIND_BY_COURSE_ID = "SELECT * FROM studentlist NATURAL JOIN user WHERE studentlist.id_course = (?)";
    private static final String DELETE_PARTICIPANT_LIST = "DELETE FROM studentlist WHERE id = (?)";
    private static final  String UPDATE_PARTICIPANT_LIST = "UPDATE studentlist SET score = (?), shortcomment = (?) WHERE id = (?)";
    private  static final String FIND_ALL = "SELECT * FROM studentlist";
    private PooledConnection connection;
    private ParticipantList patricipianList;

    /**
     *
     * @param connection
     */
    public SheetListDao(PooledConnection connection){
        this.connection = connection;
    }

    /**
     *
     * @param participantList
     * @return
     */
    public ParticipantList create (ParticipantList participantList){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_PARTICIPANT_LIST);
            preparedStatement.setString(1, String.valueOf(participantList.getIdCourse()));
            preparedStatement.setString(2, String.valueOf(participantList.getIdStudent()));
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            int id = resultSet.getInt(1);
            participantList.setId(id);
        } catch (SQLException e) {
            throw new ExceptionDao("Error executing query", e);
        }
        return participantList;
    }

    /**
     *
     * @param participantList
     * @param id
     * @return
     */
    @Override
    public ParticipantList update(ParticipantList participantList, int id){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PARTICIPANT_LIST);
            preparedStatement.setString(1, String.valueOf(participantList.getScore()));
            preparedStatement.setString(2, participantList.getShortComment());
            preparedStatement.setString(3, String.valueOf(id));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new ExceptionDao("Error executing query", e);
        }
        return participantList;
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
            throw new ExceptionDao("Error executing query", e);
        }
        return false;
    }

    //TODO сделать джойны, чтобы можно было посмотреть к кому относится.

    /**
     *
     * @param id
     * @return
     */
    @Override
    public ParticipantList find(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_PARTICIPANT_LIST);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new ExceptionDao("Error executing query", e);
        }
        return null;
    }

    /**
     *
     * @param string
     * @return
     */
    @Override
    public ParticipantList findBy(String string) {
        return null;
    }

    /**
     *
     * @return
     */
    @Override
    public List<ParticipantList> findAll() {
        List<ParticipantList> list = null;
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
                long idLecturer = rs.getLong("idteacher");
                patricipianList = new ParticipantList();
                patricipianList.setId(id);
                patricipianList.setIdCourse(idCourse);
                patricipianList.setIdStudent(idStudent);
                patricipianList.setScore(score);
                patricipianList.setShortComment(shortComment);
                list.add(patricipianList);
            }
        } catch (SQLException e) {
            throw new ExceptionDao("Error executing query", e);
        }
        return list;
    }

    @Override
    public List<ParticipantList> findAllBy(int course) {
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
                patricipianList = new ParticipantList();
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
            throw new ExceptionDao("Error executing query", e);
        }
        return list;
    }

    @Override
    public User update2(User user, int id) {
        return null;
    }
}