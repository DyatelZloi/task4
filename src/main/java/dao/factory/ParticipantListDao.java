package dao.factory;


import connection.PooledConnection;
import entity.ParticipantList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

/**
 * Created by DiZi on 29.11.2015.
 */
public class ParticipantListDao extends GenericDao<ParticipantList> {

    private static final Logger log = LoggerFactory.getLogger(ParticipantListDao.class);

    public static final String CREATE_PARTICIPANT_LIST = "INSERT INTO StudentRegisteredForCourses (ID, ID_COURSE, ID_STUDENT, SCORE, SHORTCOMMENT) VALUES (DEFAULT, ?, ?, ?, ?)";

    public static final String FIND_PARTICIPANT_LIST = "SELECT * FROM StudentRegisteredForCourses WHERE ID = (?)";

    public static final String DELETE_PARTICIPANT_LIST = "DELETE FROM StudentRegisteredForCourses WHERE ID = (?)";

    public static final  String UPDATE_PARTICIPANT_LIST = "UPDATE StudentRegisteredForCourses SET ID_COURSE = (?), ID_STUDENT = (?), SCORE = (?), SHORTCOMMENT = (?) WHERE ID = (?)";

    private PooledConnection connection;

    public ParticipantListDao (PooledConnection connection){
        this.connection = connection;
    }

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
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ignored) {
            }
        }
        return participantList;
    }

    @Override
    public ParticipantList update(ParticipantList participantList, int id){
        return participantList;
    }

    @Override
    public boolean delete(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PARTICIPANT_LIST);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  false;
    }

    //TODO сделать джойны, чтобы можно было посмотреть к кому относится.
    @Override
    public ParticipantList find(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_PARTICIPANT_LIST);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}