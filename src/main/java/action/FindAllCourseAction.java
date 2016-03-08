package action;


import dao.CourseDao;
import dao.ExceptionDao;
import dao.FactoryDao;
import dao.GenericDao;
import entity.OptionalCourse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Malkov Nikifor on 21.12.2015.
 */
public class FindAllCourseAction implements Strategy {

    /**
     *
     */
    private static final Logger log = LoggerFactory.getLogger(FindCourseAction.class);

    private static final String DIRECTORY = "/WEB-INF/find-all-course-g.jsp";

    /**
     * Find all courses
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.info("Begin search all courses");
        List list = null;
        log.debug("Fields are filled");
        FactoryDao factoryDao = FactoryDao.getInstance();
        GenericDao genericDao = factoryDao.getDao(CourseDao.class);
        factoryDao.beginTransaction();
        try{
            log.debug("Query execution");
            list = genericDao.findAll();
        } catch (ExceptionDao e) {
            log.error("Unable to execute query");
            factoryDao.rollback();
        }
        factoryDao.commit();
        log.info("Search all course completed");
        request.setAttribute("en", list);
        return DIRECTORY;
    }
}
