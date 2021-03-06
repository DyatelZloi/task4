package action;

import dao.CourseDao;
import dao.ExceptionDao;
import dao.FactoryDao;
import dao.GenericDao;
import entity.OptionalCourse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Malkov Nikifor on 10.12.2015.
 */
public class UpdateCourseAction implements Strategy {

    private static final Logger log = LoggerFactory.getLogger(UpdateCourseAction.class);

    private static final String NAME_PARAMETER_NAME = "name";
    private static final String COURSE_DESCRIPTION_PARAMETER_NAME = "course-description";
    private static final String ID = "id";
    private static final String DIRECTORY = "/WEB-INF/index.jsp";

    /**
     * Update a course by id
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.info("Begin update a course");
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
        return DIRECTORY;
    }
}
