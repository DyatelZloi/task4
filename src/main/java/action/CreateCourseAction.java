package action;



import dao.ExceptionDao;
import dao.CourseDao;
import dao.GenericDao;
import dao.FactoryDao;
import entity.OptionalCourse;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import entity.FactoryEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Malkov Nikifor on 02.12.2015.
 */
public class CreateCourseAction implements Strategy {

    private static final Logger log = LoggerFactory.getLogger(CreateCourseAction.class);

    private static final String NAME_PARAMETER_NAME = "name";
    private static final String COURSE_DESCRIPTION_PARAMETER_NAME = "course-description";
    private static final String TEACHER_ID = "teacher-id";
    private static final String DIRECTORY = "/WEB-INF/index.jsp";

    /**
     * Create a new course
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.info("Begin to create a new course");
        String name = request.getParameter(NAME_PARAMETER_NAME);
        String courseDescription = request.getParameter(COURSE_DESCRIPTION_PARAMETER_NAME);
        int teacherId = Integer.parseInt(request.getParameter(TEACHER_ID));
        FactoryEntity factoryEntity = FactoryEntity.getInstance();
        OptionalCourse course = (OptionalCourse) factoryEntity.getEntity(OptionalCourse.class);
        course.setName(name);
        course.setCourseDescription(courseDescription);
        course.setLecturer(teacherId);
        log.debug("Fields are filled");
        FactoryDao factoryDao = FactoryDao.getInstance();
        GenericDao genericDao = factoryDao.getDao(CourseDao.class);
        factoryDao.beginTransaction();
        try{
            log.debug("Query execution");
            genericDao.create(course);
        } catch (ExceptionDao e){
            log.error("Unable to execute query");
            factoryDao.rollback();
        }
        factoryDao.commit();
        log.info("Course construction completed");
        return DIRECTORY;
    }
}