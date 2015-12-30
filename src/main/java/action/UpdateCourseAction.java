package action;

import dao.CourseDao;
import dao.FactoryDao;
import dao.GenericDao;
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
public class UpdateCourseAction implements Strategy {

    private static final Logger log = LoggerFactory.getLogger(UpdateCourseAction.class);

    private static final String NAME_PARAMETER_NAME = "name";
    private static final String COURSE_DESCRIPTION_PARAMETER_NAME = "course-description";
    private static final String ID = "id";

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

        FactoryDao factoryDao = FactoryDao.getInstance();
        factoryDao.beginTransaction();
        GenericDao genericDao = factoryDao.getDao(CourseDao.class);
        genericDao.update(course, id);
        factoryDao.commit();

        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }

    }
}
