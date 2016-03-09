package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ExceptionDao;
import dao.FactoryDao;
import dao.GenericDao;
import dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Malkov Nikifor on 10.12.2015.
 */
public class DeleteUserAction implements Strategy {

    private static final Logger log = LoggerFactory.getLogger(DeleteUserAction.class);

    private static final String ID = "id";
    private static final String DIRECTORY = "/WEB-INF/index.jsp";

    /**
     * Delete a user by id
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.info("Begin to delete a sheet user");
        int id = Integer.parseInt(request.getParameter(ID));
        log.debug("Fields are filled");
        FactoryDao factoryDao = FactoryDao.getInstance();
        GenericDao genericDao = factoryDao.getDao(UserDao.class);
        factoryDao.beginTransaction();
        try{
            log.debug("Query execution");
            genericDao.delete(id);
        } catch (ExceptionDao e) {
            log.error("Unable to execute query");
            factoryDao.rollback();
        }
        factoryDao.commit();
        log.info("Delete user completed");
        return DIRECTORY;
    }
}