package dao;

import connection.PooledConnection;
import entity.OptionalCourse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Malkov Nikifor on 25.11.2015.
 */
public class CourseDao extends GenericDao<OptionalCourse> {

    private static final Logger log = LoggerFactory.getLogger(CourseDao.class);

    private static final String CREATE_COURSE = "INSERT INTO course (id, name, coursedescription, idteacher) VALUES (DEFAULT, ?, ?, ?)";
    private static final String FIND_COURSE = "SELECT * FROM course WHERE id = (?)";
    private static final String DELETE_COURSE = "DELETE FROM course WHERE id = (?)";
    private static final  String UPDATE_COURSE = "UPDATE course SET name = (?), coursedescription = (?) WHERE id = (?)";
    private  static final String FIND_ALL_BY_ID_TEACHER = "SELECT * FROM course WHERE idteacher = (?)";
    private  static final String FIND_ALL = "SELECT * FROM course";
    private OptionalCourse optionalCourse;
    private PooledConnection connection;
    private List list = new ArrayList();

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
     * @throws ExceptionDao
     */
    @Override
    public OptionalCourse create (OptionalCourse course) throws ExceptionDao {
        try {
            PreparedStatement ps = connection.prepareStatement(CREATE_COURSE);
            ps.setString(1, course.getName());
            ps.setString(2, course.getCourseDescription());
            ps.setInt(3, course.getLecturer());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            long id = rs.getLong(1);
            course.setId(id);
        } catch (SQLException e) {
            log.error("Error executing query");
            throw new ExceptionDao(e);
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
            log.error("Error executing query");
            throw new ExceptionDao(e);
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
            log.error("Error executing query");
            throw new ExceptionDao(e);}
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
                int idTeacher = rs.getInt("idteacher");
                OptionalCourse optionalCourse = new OptionalCourse();
                optionalCourse.setId(id2);
                optionalCourse.setName(name);
                optionalCourse.setCourseDescription(String.valueOf(courseDescription));
                optionalCourse.setLecturer(idTeacher);
                this.optionalCourse = optionalCourse;

            }
        } catch (SQLException e) {
            log.error("Error executing query");
            throw new ExceptionDao(e);
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
                Clob courseDescription = rs.getClob(4);
                int idLecturer = rs.getInt("idteacher");
                OptionalCourse optionalCourse = new OptionalCourse();
                optionalCourse.setId(id);
                optionalCourse.setName(name);
                optionalCourse.setCourseDescription(String.valueOf(courseDescription));
                optionalCourse.setLecturer(idLecturer);
                this.optionalCourse = optionalCourse;
                list.add(optionalCourse);
            }
        } catch (SQLException e) {
            log.error("Error executing query");
            throw new ExceptionDao(e);
        }
        return list;
    }

    @Override
    public List<OptionalCourse> findAllBy(int idTeacher) {
        try {
            PreparedStatement ps = connection.prepareStatement(FIND_ALL_BY_ID_TEACHER);
            ps.setInt(1, idTeacher);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                Clob courseDescription = rs.getClob(3);
                int idLecturer = rs.getInt(4);
                OptionalCourse optionalCourse = new OptionalCourse();
                optionalCourse.setId(id);
                optionalCourse.setName(name);
                optionalCourse.setCourseDescription(String.valueOf(courseDescription));
                optionalCourse.setLecturer(idLecturer);
                this.optionalCourse = optionalCourse;
                list.add(optionalCourse);
            }
        } catch (SQLException e) {
            log.error("Error executing query");
            throw new ExceptionDao(e);
        }
        return list;
    }

    @Override
    public OptionalCourse update2(OptionalCourse course, int id) {
        return null;
    }

    @Override
    public List<OptionalCourse> findAllBy(long id) {
        return null;
    }
}