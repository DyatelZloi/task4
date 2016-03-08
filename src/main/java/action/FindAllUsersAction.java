package action;

import dao.ExceptionDao;
import dao.FactoryDao;
import dao.GenericDao;
import dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Malkov Nikifor on 25.12.2015.
 */
public class FindAllUsersAction implements Strategy {

    private static final Logger log = LoggerFactory.getLogger(FindCourseAction.class);

    private static final String DIRECTORY = "/WEB-INF/created-users.jsp";

    /**
     * Find all users
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.info("Begin search all users");
        List list = null;
        log.debug("Fields are filled");
        FactoryDao factoryDao = FactoryDao.getInstance();
        GenericDao genericDao = factoryDao.getDao(UserDao.class);
        factoryDao.beginTransaction();
        try{
            log.debug("Query execution");
            list = genericDao.findAll();
        } catch (ExceptionDao e) {
            log.error("Unable to execute query");
            factoryDao.rollback();
        }
        factoryDao.commit();
        log.info("Search all users completed");
        request.setAttribute("createdusers", list);
        return  DIRECTORY;
    }
}