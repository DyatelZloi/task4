package dao.factory;

import connection.ConnectionPool;
import dao.DaoException;
import entity.OptionalCourse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

/**
 * Created by DiZi on 25.11.2015.
 */
public class CourseDao extends GenericDao<OptionalCourse> {

    private static final Logger log = LoggerFactory.getLogger(CourseDao.class);

    public static final String CREATE_COURSE = "INSERT INTO COURSE (ID, NAME, COURSEDESCRIPTION) VALUES (DEFAULT, ?, ?)";

    public static final String FIND_COURSE = "SELECT * FROM COURSE WHERE ID = (?)";

    public static final String DELETE_COURSE = "DELETE FROM COURSE WHERE ID = (?)";

    public static final  String UPDATE_COURSE = "UPDATE COURSE SET NAME = (?), COURSEDESCRIPTION = (?) WHERE ID = (?)";

    public static final String DRIVER_CLASS_NAME = "org.h2.Driver";

     public OptionalCourse optionalCourse;

    public OptionalCourse create (OptionalCourse course) throws DaoException  {
        //TODO
        // Вынести конекшн
        // Должен быть отдельный класс
        ConnectionPool pool = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        pool = ConnectionPool.getInstance();
        pool.setDriverClassName(DRIVER_CLASS_NAME);
        pool.setUserName("GOD");
        pool.setPassword("GOD");
        pool.setUrl("jdbc:h2:~/course");
        pool.setConnectionNumber(4);
        pool.initConnections();
        //вынести в инициализаццию конекшена, чтобы не прописывать такое количество методов
        con = pool.getConnection();
        // вынести в фабрику использование конекшена
        try {
            ps = con.prepareStatement(CREATE_COURSE);
            ps.setString(1, course.getName());
            ps.setString(2, course.getCourseDescription());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            rs.next();
            long id = rs.getLong(1);
            course.setId(id);
        } catch (SQLException e) {
            throw new DaoException("Ошибка при выполнении запроса",e);
        } finally {
            try {
                con.close();
            } catch (Exception ignored) {
            }
        }
        return course;
    }

    public OptionalCourse update (OptionalCourse course, int id){
        ConnectionPool pool = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        pool = ConnectionPool.getInstance();
        pool.setDriverClassName(DRIVER_CLASS_NAME);
        pool.setUserName("GOD");
        pool.setPassword("GOD");
        pool.setUrl("jdbc:h2:~/course");
        pool.setConnectionNumber(4);
        pool.initConnections();
        //вынести в инициализаццию конекшена, чтобы не прописывать такое количество методов
        con = pool.getConnection();
        // вынести в фабрику использование конекшена
        try {
            ps = con.prepareStatement(UPDATE_COURSE);
            ps.setString(1, course.getName());
            ps.setString(2, course.getCourseDescription());
            ps.setInt(3, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Ошибка при выполнении запроса",e);
        } finally {
            try {
                con.close();
            } catch (Exception ignored) {
            }
        }
        return course;
    }

    @Override
    public boolean delete (int id){
        ConnectionPool pool = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        pool = ConnectionPool.getInstance();
        pool.setDriverClassName(DRIVER_CLASS_NAME);
        pool.setUserName("GOD");
        pool.setPassword("GOD");
        pool.setUrl("jdbc:h2:~/course");
        pool.setConnectionNumber(4);
        pool.initConnections();
        //вынести в инициализаццию конекшена, чтобы не прописывать такое количество методов
        con = pool.getConnection();
        try {
            ps = con.prepareStatement(DELETE_COURSE);
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public OptionalCourse find (int id){
        ConnectionPool pool = null;

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        pool = ConnectionPool.getInstance();
        pool.setDriverClassName(DRIVER_CLASS_NAME);
        pool.setUserName("GOD");
        pool.setPassword("GOD");
        pool.setUrl("jdbc:h2:~/course");
        pool.setConnectionNumber(4);
        pool.initConnections();
        //вынести в инициализаццию конекшена, чтобы не прописывать такое количество методов
        con = pool.getConnection();
        try {
            ps = con.prepareStatement(FIND_COURSE);
            ps.setInt(1, id);
            ps.execute();
            rs = ps.getResultSet();
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

}