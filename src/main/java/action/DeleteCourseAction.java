package action;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import dao.ExceptionDao;
import dao.CourseDao;
import dao.FactoryDao;
import dao.GenericDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Malkov Nikifor on 10.12.2015.
 */
public class DeleteCourseAction implements Strategy {

    private static final Logger log = LoggerFactory.getLogger(DeleteCourseAction.class);

    private static final String ID = "id";
    private static final String DIRECTORY = "/WEB-INF/index.jsp";

    /**
     * Delete a course by id
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response){
        log.info("Begin to delete a course");
        int id = Integer.parseInt(request.getParameter(ID));
        log.debug("Fields are filled");
        FactoryDao factoryDao = FactoryDao.getInstance();
        GenericDao genericDao = factoryDao.getDao(CourseDao.class);
        factoryDao.beginTransaction();
        try{
            log.debug("Query execution");
            genericDao.delete(id);
        } catch (ExceptionDao e) {
            log.error("Unable to execute query");
            factoryDao.rollback();
        }
        factoryDao.commit();
        log.info("Delete a course completed");
        return DIRECTORY;
    }
}
