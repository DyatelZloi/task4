package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ExceptionDao;
import dao.GenericDao;
import dao.FactoryDao;
import dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Malkov Nikifor on 10.12.2015.
 */
public class FindUserAction implements Strategy {

    private static final Logger log = LoggerFactory.getLogger(FindUserAction.class);

    private static final String ID = "id";
    private static final String DIRECTORY = "directory";

    /**
     * Find user by id
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.info("Begin search a user");
        String directory = request.getParameter(DIRECTORY);
        String moveDirectory = "/WEB-INF/" + directory + ".jsp";
        int id = Integer.parseInt(request.getParameter(ID));
        log.debug("Fields are filled");
        FactoryDao factoryDao = FactoryDao.getInstance();
        GenericDao genericDao = factoryDao.getDao(UserDao.class);
        factoryDao.beginTransaction();
        try{
            log.debug("Query execution");
            request.setAttribute("entity", genericDao.find(id));
        } catch (ExceptionDao e) {
            log.error("Unable to execute query");
            factoryDao.rollback();
        }
        factoryDao.commit();
        log.info("Search user completed");
        return moveDirectory;
    }
}