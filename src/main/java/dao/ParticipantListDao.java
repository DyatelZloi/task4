package dao;

import entity.Lecturer;
import entity.ParticipantList;

import java.sql.*;

/**
 * Created by DiZi on 29.11.2015.
 */
public class ParticipantListDao {
    public static final String CREATE_PARTICIPANT_LIST = "INSERT INTO StudentRegisteredForCourses (ID, ID_COURSE, ID_STUDENT, SCORE, SHORTCOMMENT) VALUES (DEFAULT, ?, ?, ?, ?)";

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

}