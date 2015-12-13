package dao.factory;

import entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

/**
 * Created by DiZi on 29.11.2015.
 */
public class StudentDao extends GenericDao<Student> {

    private static final Logger log = LoggerFactory.getLogger(StudentDao.class);

    public static final String CREATE_STUDENT = "INSERT INTO STUDENT (ID, NAME, SURNAME) VALUES (DEFAULT, ?, ?)";

    public static final String FIND_STUDENT = "SELECT * FROM STUDENT WHERE ID = (?)";

    public static final String DELETE_STUDENT = "DELETE FROM STUDENT WHERE ID = (?)";

    public static final  String UPDATE_STUDENT = "UPDATE STUDENT SET NAME = (?), SURNAME = (?) WHERE ID = (?)";

    @Override
    public Student create (Student student){
        //TO DO
        // Вынести конекшн
        // Должен быть отдельный класс
        Connection connection = null;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:~/course","GOD","GOD");
            preparedStatement = connection.prepareStatement(CREATE_STUDENT);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getSurname());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            long id = resultSet.getLong(1);
            student.setId(id);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ignored) {
            }
        }
        return student;
    }

    @Override
    public Student update(Student student, int id){
        Connection connection = null;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        try {
            preparedStatement = connection.prepareStatement(UPDATE_STUDENT);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getSurname());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return student;
    }

    @Override
    public boolean delete (int id){
        Connection connection = null;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        try {
            preparedStatement = connection.prepareStatement(DELETE_STUDENT);
            preparedStatement.setString(1, String.valueOf(id));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Student find(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        ResultSet rs = null;
        Student student = new Student();
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:~/course","GOD","GOD");
            preparedStatement = connection.prepareStatement(FIND_STUDENT);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.executeUpdate();
            while (rs.next()) {
                int id2 = rs.getInt(1);
                String name = rs.getString(2);
                Clob courseDescription = rs.getClob(3);
                long idLecturer = rs.getLong("ID_LECTURER");
                student.setId(id2);
                student.setName(name);
                student.setSurname(String.valueOf(courseDescription));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ignored) {
            }
        }
        return student;
    }
}