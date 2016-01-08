package action;

import dao.CourseDao;
import dao.ExceptionDao;
import dao.FactoryDao;
import dao.GenericDao;
import entity.OptionalCourse;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import entity.ParticipantList;
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
    private static final String DIRECTORY = "directory";

    /**
     * Update a course by id
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        log.info("Begin update a course");
        String directory = request.getParameter(DIRECTORY);
        String moveDirectory = "/WEB-INF/" + directory + ".jsp";
        String name = request.getParameter(NAME_PARAMETER_NAME);
        String courseDescription = request.getParameter(COURSE_DESCRIPTION_PARAMETER_NAME);
        int id = Integer.parseInt(request.getParameter(ID));
        OptionalCourse course = new OptionalCourse();
        course.setName(name);
        course.setCourseDescription(courseDescription);
        log.debug("Fields are filled");
        FactoryDao factoryDao = FactoryDao.getInstance();
        GenericDao genericDao = factoryDao.getDao(CourseDao.class);
        factoryDao.beginTransaction();
        try{
            log.debug("Query execution");
            genericDao.update(course, id);
        } catch (ExceptionDao e) {
            log.error("Unable to execute query");
            factoryDao.rollback();
        }
        factoryDao.commit();
        try {
            request.getRequestDispatcher(moveDirectory).forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }

    }
}
