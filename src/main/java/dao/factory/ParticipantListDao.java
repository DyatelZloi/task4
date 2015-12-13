package dao.factory;


import entity.ParticipantList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

/**
 * Created by DiZi on 29.11.2015.
 */
public class ParticipantListDao extends Dao<ParticipantList>{

    private static final Logger log = LoggerFactory.getLogger(ParticipantListDao.class);

    public static final String CREATE_PARTICIPANT_LIST = "INSERT INTO StudentRegisteredForCourses (ID, ID_COURSE, ID_STUDENT, SCORE, SHORTCOMMENT) VALUES (DEFAULT, ?, ?, ?, ?)";

    public static final String FIND_PARTICIPANT_LIST = "SELECT * FROM StudentRegisteredForCourses WHERE ID = (?)";

    public static final String DELETE_PARTICIPANT_LIST = "DELETE FROM StudentRegisteredForCourses WHERE ID = (?)";

    public static final  String UPDATE_PARTICIPANT_LIST = "UPDATE StudentRegisteredForCourses SET ID_COURSE = (?), ID_STUDENT = (?), SCORE = (?), SHORTCOMMENT = (?) WHERE ID = (?)";

    public ParticipantList create (ParticipantList participantList){
        Connection connection = null;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:~/course","GOD","GOD");
            preparedStatement = connection.prepareStatement(CREATE_PARTICIPANT_LIST);
            preparedStatement.setString(1, String.valueOf(participantList.getIdCourse()));
            preparedStatement.setString(2, String.valueOf(participantList.getIdStudent()));
            preparedStatement.setString(3, String.valueOf(participantList.getScore()));
            preparedStatement.setString(4, participantList.getShortComment());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            long id = resultSet.getLong(1);
            participantList.setId(id);
        } catch (ClassNotFoundException | SQLException e) {
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
        Connection connection = null;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:~/course","GOD","GOD");
            preparedStatement = connection.prepareStatement(DELETE_PARTICIPANT_LIST);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return  false;
    }

    //TODO сделать джойны, чтобы можно было посмотреть к кому относится.
    @Override
    public ParticipantList find(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:~/course", "GOD", "GOD");
            preparedStatement = connection.prepareStatement(FIND_PARTICIPANT_LIST);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}