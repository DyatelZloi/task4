package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CourseDao;
import dao.ExceptionDao;
import dao.FactoryDao;
import dao.GenericDao;
import entity.OptionalCourse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Malkov Nikifor on 10.12.2015.
 */
public class FindCourseAction implements Strategy {


    private static final Logger log = LoggerFactory.getLogger(FindCourseAction.class);

    private static final String ID = "id";
    private static final String DIRECTORY = "directory";

    /**
     * Find course by id
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.info("Begin search a course");
        String directory = request.getParameter(DIRECTORY);
        String moveDirectory = "/WEB-INF/" + directory + ".jsp";
        int id = Integer.parseInt(request.getParameter(ID));
        log.debug("Fields are filled");
        FactoryDao factoryDao = FactoryDao.getInstance();
        GenericDao genericDao = factoryDao.getDao(CourseDao.class);
        factoryDao.beginTransaction();
        OptionalCourse optionalCourse = null;
        try{
            log.debug("Query execution");
            optionalCourse = (OptionalCourse) genericDao.find(id);
        } catch (ExceptionDao e) {
            log.error("Unable to execute query");
            factoryDao.rollback();
        }
        factoryDao.commit();
        log.info("Search course completed");
        request.setAttribute("createdcourses", optionalCourse);
        return moveDirectory;
    }
}
