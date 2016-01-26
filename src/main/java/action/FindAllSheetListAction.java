package action;

import dao.ExceptionDao;
import dao.FactoryDao;
import dao.GenericDao;
import dao.SheetListDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Malkov Nikifor on 25.12.2015.
 */
public class FindAllSheetListAction implements Strategy {

    private static final Logger log = LoggerFactory.getLogger(FindCourseAction.class);

    private static final String DIRECTORY = "directory";

    /**
     * Find all registered students
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        log.info("Begin search all sheets registrations");
        String directory = request.getParameter(DIRECTORY);
        String moveDirectory = "/WEB-INF/" + directory + ".jsp";
        List list = null;
        log.debug("Fields are filled");
        FactoryDao factoryDao = FactoryDao.getInstance();
        GenericDao genericDao = factoryDao.getDao(SheetListDao.class);
        factoryDao.beginTransaction();
        try{
            log.debug("Query execution");
            list = genericDao.findAll();
        } catch (ExceptionDao e) {
            log.error("Unable to execute query");
            factoryDao.rollback();
        }
        factoryDao.commit();
        log.info("Search all sheets registrations completed");
        request.setAttribute("createdcourses", list);
        try {
            request.getRequestDispatcher(moveDirectory).forward(request, response);
        } catch (ServletException | IOException e) {
            log.error("Error when redirecting");
            throw new ExceptionAction(e);
        }
    }
}