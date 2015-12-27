package action;

import dao.factory.CourseDao;
import dao.factory.DaoFactory;
import dao.factory.GenericDao;
import entity.OptionalCourse;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by DiZi on 10.12.2015.
 */
public class UpdateCourse implements Strategy {

    /**
     *
     */
    private static final Logger log = LoggerFactory.getLogger(UpdateCourse.class);

    /**
     *
     */
    public static final String NAME_PARAMETER_NAME = "name";

    /**
     *
     */
    public static final String COURSE_DESCRIPTION_PARAMETER_NAME = "course-description";

    /**
     *
     */
    public static  final int textA = 0;

    /**
     *
     */
    public static final String ID = "id";

    /**
     * Update course by id
     *
     * @param request
     * @param response
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter(NAME_PARAMETER_NAME);
        String courseDescription = request.getParameter(COURSE_DESCRIPTION_PARAMETER_NAME);
        int id = Integer.parseInt(request.getParameter(ID));
        OptionalCourse course = new OptionalCourse();
        course.setName(name);
        course.setCourseDescription(courseDescription);

        DaoFactory daoFactory = DaoFactory.getInstance();
        daoFactory.beginTransaction();
        GenericDao genericDao = daoFactory.getDao(CourseDao.class);
        genericDao.update(course, id);
        daoFactory.commit();

        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }

    }
}
