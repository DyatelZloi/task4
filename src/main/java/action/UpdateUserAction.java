package action;

import dao.ExceptionDao;
import dao.FactoryDao;
import dao.GenericDao;

import dao.UserDao;
import entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Malkov Nikifor on 10.12.2015.
 */
public class UpdateUserAction implements Strategy {

    private static final Logger log = LoggerFactory.getLogger(UpdateUserAction.class);

    public static final String ROLE = "role";
    private static final String ID = "id";
    private static final String DIRECTORY = "directory";

    /**
     * Update a user by id
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.info("Begin update a user");
        String directory = request.getParameter(DIRECTORY);
        String moveDirectory = "/WEB-INF/" + directory + ".jsp";
        int id = Integer.parseInt(request.getParameter(ID));
        String role = request.getParameter(ROLE);
        User user = new User();
        user.setRole(role);
        user.setId(id);
        log.debug("Fields are filled");
        FactoryDao factoryDao = FactoryDao.getInstance();
        GenericDao genericDao = factoryDao.getDao(UserDao.class);
        factoryDao.beginTransaction();
        try {
            log.debug("Query execution");
            genericDao.update(user, id);
        } catch (ExceptionDao e) {
            log.error("Unable to execute query");
            factoryDao.rollback();
        }
        factoryDao.commit();
        log.info("User update completed");
        return moveDirectory;
    }
}