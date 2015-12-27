package dao.factory;

import connection.PooledConnection;
import dao.DaoException;
import entity.OptionalCourse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DiZi on 25.11.2015.
 */
public class CourseDao extends GenericDao<OptionalCourse> {

    /**
     *
     */
    private static final Logger log = LoggerFactory.getLogger(CourseDao.class);

    /**
     *
     */
    public static final String CREATE_COURSE = "INSERT INTO COURSE (ID, NAME, COURSEDESCRIPTION) VALUES (DEFAULT, ?, ?)";

    /**
     *
     */
    public static final String FIND_COURSE = "SELECT * FROM COURSE WHERE ID = (?)";

    /**
     *
     */
    public static final String DELETE_COURSE = "DELETE FROM COURSE WHERE ID = (?)";

    /**
     *
     */
    public static final  String UPDATE_COURSE = "UPDATE COURSE SET NAME = (?), COURSEDESCRIPTION = (?) WHERE ID = (?)";

    /**
     *
     */
    public  static final String FIND_ALL = "SELECT * FROM course";

    /**
     *
     */
    public OptionalCourse optionalCourse;

    /**
     *
     */
    private PooledConnection connection;

    /**
     *
     */
    private List<OptionalCourse> list = new ArrayList<>();

    /**
     *
     * @param connection
     */
    public CourseDao (PooledConnection connection){
        this.connection = connection;
    }

    /**
     *
     */
    public CourseDao() {

    }

    /**
     * Create course
     *
     * @param course
     * @return
     * @throws DaoException
     */
    public OptionalCourse create (OptionalCourse course) {
        try {
            PreparedStatement ps = connection.prepareStatement(CREATE_COURSE);
            ps.setString(1, course.getName());
            ps.setString(2, course.getCourseDescription());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            long id = rs.getLong(1);
            course.setId(id);
        } catch (SQLException e) {
            throw new DaoException("Ошибка при выполнении запроса",e);
        }
        return course;
    }

    /**
     *
     * @param course
     * @param id
     * @return
     */
    public OptionalCourse update (OptionalCourse course, int id){
        try {
            PreparedStatement ps = connection.prepareStatement(UPDATE_COURSE);
            connection.setAutoCommit(false);
            ps.setString(1, course.getName());
            ps.setString(2, course.getCourseDescription());
            ps.setInt(3, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Ошибка при выполнении запроса",e);
        }
        return course;
    }

    /**
     * Delete course by id
     *
     * @param id
     * @return
     */
    @Override
    public boolean delete (int id){
        try {
            PreparedStatement ps = connection.prepareStatement(DELETE_COURSE);
            ps.setLong(1, id);
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
     * Find course by id
     *
     * @param id
     * @return
     */
    @Override
    public OptionalCourse find (int id){
        try {
            PreparedStatement ps = connection.prepareStatement(FIND_COURSE);
            ps.setInt(1, id);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                int id2 = rs.getInt(1);
                String name = rs.getString(2);
                Clob courseDescription = rs.getClob(3);
                long idLecturer = rs.getLong("ID_LECTURER");
                OptionalCourse optionalCourse = new OptionalCourse();
                optionalCourse.setId(id2);
                optionalCourse.setName(name);
                optionalCourse.setCourseDescription(String.valueOf(courseDescription));
                optionalCourse.setLecturer(idLecturer);
                this.optionalCourse = optionalCourse;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  this.optionalCourse;
    }

    /**
     * Find course by string (login )
     * @param string
     * @return
     */
    @Override
    public OptionalCourse findBy(String string) {
        return null;
    }

    /**
     * Find all courses
     *
     * @return
     */
    @Override
    public List<OptionalCourse> findAll() {
        try {
            PreparedStatement ps = connection.prepareStatement(FIND_ALL);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                Clob courseDescription = rs.getClob(3);
                long idLecturer = rs.getLong("ID_LECTURER");
                OptionalCourse optionalCourse = new OptionalCourse();
                optionalCourse.setId(id);
                optionalCourse.setName(name);
                optionalCourse.setCourseDescription(String.valueOf(courseDescription));
                optionalCourse.setLecturer(idLecturer);
                this.optionalCourse = optionalCourse;
                list.add(optionalCourse);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}