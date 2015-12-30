package dao;


import connection.PooledConnection;
import dao.ExceptionDao;
import entity.ParticipantList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.List;

/**
 * Created by DiZi on 29.11.2015.
 */
public class ParticipantListDao extends GenericDao<ParticipantList> {

    private static final Logger log = LoggerFactory.getLogger(ParticipantListDao.class);

    /**
     *
     */
    public static final String CREATE_PARTICIPANT_LIST = "INSERT INTO StudentRegisteredForCourses (ID, ID_COURSE, ID_STUDENT, SCORE, SHORTCOMMENT) VALUES (DEFAULT, ?, ?, ?, ?)";

    /**
     *
     */
    public static final String FIND_PARTICIPANT_LIST = "SELECT * FROM StudentRegisteredForCourses WHERE ID = (?)";

    /**
     *
     */
    public static final String DELETE_PARTICIPANT_LIST = "DELETE FROM StudentRegisteredForCourses WHERE ID = (?)";

    /**
     *
     */
    public static final  String UPDATE_PARTICIPANT_LIST = "UPDATE StudentRegisteredForCourses SET ID_COURSE = (?), ID_STUDENT = (?), SCORE = (?), SHORTCOMMENT = (?) WHERE ID = (?)";

    /**
     *
     */
    public  static final String FIND_ALL = "SELECT * FROM StudentRegisteredForCourses";

    /**
     *
     */
    private PooledConnection connection;

    /**
     *
     */
    private ParticipantList patricipianList;

    /**
     *
     * @param connection
     */
    public ParticipantListDao (PooledConnection connection){
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
            preparedStatement.setString(3, String.valueOf(participantList.getScore()));
            preparedStatement.setString(4, participantList.getShortComment());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            long id = resultSet.getLong(1);
            participantList.setId(id);
        } catch (SQLException e) {
            throw new ExceptionDao("Проблемы при создании курса", e);
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
            throw new ExceptionDao("Проблемы при создании курса", e);
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
            throw new ExceptionDao("Проблемы при создании курса", e);
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
                long idLecturer = rs.getLong("ID_LECTURER");
                patricipianList = new ParticipantList();
                patricipianList.setId(id);
                patricipianList.setIdCourse(idCourse);
                patricipianList.setIdStudent(idStudent);
                patricipianList.setScore(score);
                patricipianList.setShortComment(shortComment);
                list.add(patricipianList);
            }
        } catch (SQLException e) {
            throw new ExceptionDao("Проблемы при создании курса", e);
        }
        return list;
    }
}