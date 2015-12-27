package dao.factory;

import connection.PooledConnection;
import entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.List;

/**
 * Created by DiZi on 29.11.2015.
 */
public class StudentDao extends GenericDao<Student> {

    /**
     *
     */
    private static final Logger log = LoggerFactory.getLogger(StudentDao.class);

    /**
     *
     */
    public static final String CREATE_STUDENT = "INSERT INTO STUDENT (ID, NAME, SURNAME) VALUES (DEFAULT, ?, ?)";

    /**
     *
     */
    public static final String FIND_STUDENT = "SELECT * FROM STUDENT WHERE ID = (?)";

    /**
     *
     */
    public static final String DELETE_STUDENT = "DELETE FROM STUDENT WHERE ID = (?)";

    /**
     *
     */
    public static final  String UPDATE_STUDENT = "UPDATE STUDENT SET NAME = (?), SURNAME = (?) WHERE ID = (?)";

    /**
     *
     */
    public  static final String FIND_ALL = "SELECT * FROM student";

    /**
     *
     */
    private PooledConnection connection;

    /**
     *
     */
    private Student student;

    /**
     *
     * @param connection
     */
    public StudentDao (PooledConnection connection){
        this.connection = connection;
    }

    /**
     * Create student
     *
     * @param student
     * @return
     */
    @Override
    public Student create (Student student){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_STUDENT);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getSurname());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            long id = resultSet.getLong(1);
            student.setId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    /**
     * Update student
     *
     * @param student
     * @param id
     * @return
     */
    @Override
    public Student update(Student student, int id){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STUDENT);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getSurname());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return student;
    }

    /**
     * Delete student by id
     *
     * @param id
     * @return
     */
    @Override
    public boolean delete (int id){
        try {
            PreparedStatement ps = connection.prepareStatement(DELETE_STUDENT);
            ps.setInt(1, id);
            ps.executeUpdate();
            if (ps.getUpdateCount() != 0){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Find student by id
     *
     * @param id
     * @return
     */
    @Override
    public Student find(int id) {
        Student student = new Student();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_STUDENT);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.executeUpdate();
            ResultSet rs = null;
            while (rs.next()) {
                int id2 = rs.getInt(1);
                String name = rs.getString(2);
                Clob courseDescription = rs.getClob(3);
                long idLecturer = rs.getLong("ID_LECTURER");
                student.setId(id2);
                student.setName(name);
                student.setSurname(String.valueOf(courseDescription));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    /**
     * Find student by string ()
     *
     * @param string
     * @return
     */
    @Override
    public Student findBy(String string) {
        return null;
    }

    /**
     * Find all students
     *
     * @return
     */
    @Override
    public List<Student> findAll() {
        List<Student> list = null;
        try {
            PreparedStatement ps = connection.prepareStatement(FIND_ALL);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String surname = rs.getString(3);
                student = new Student();
                student.setId(id);
                student.setName(name);
                student.setSurname(surname);
                list.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}