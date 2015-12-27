package action;



import dao.factory.CourseDao;
import dao.factory.GenericDao;
import dao.factory.DaoFactory;
import entity.OptionalCourse;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by DiZi on 02.12.2015.
 */
public class ConcreteStrategyAdd implements Strategy {

    /**
     *
     */
    private static final Logger log = LoggerFactory.getLogger(ConcreteStrategyAdd.class);

    /**
     *
     */
    public static final String NAME_PARAMETER_NAME = "name";

    /**
     *
     */
    public static final String COURSE_DESCRIPTION_PARAMETER_NAME = "course-description";

    /**
     * Add course
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter(NAME_PARAMETER_NAME);
        String courseDescription = request.getParameter(COURSE_DESCRIPTION_PARAMETER_NAME);
        OptionalCourse course = new OptionalCourse();
        course.setName(name);
        course.setCourseDescription(courseDescription);
        DaoFactory daoFactory = DaoFactory.getInstance();
        daoFactory.beginTransaction();
        GenericDao genericDao = daoFactory.getDao(CourseDao.class);
        genericDao.create(course);
        daoFactory.commit();
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}