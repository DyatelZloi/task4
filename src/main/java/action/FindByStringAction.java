package action;

import dao.ExceptionDao;
import dao.FactoryDao;
import dao.GenericDao;
import dao.UserDao;
import entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Malkov Nikifor on 17.12.2015.
 */
public class FindByStringAction implements Strategy{

    private static final Logger log = LoggerFactory.getLogger(FindByStringAction.class);

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String DIRECTORY = "directory";

    /**
     * Find user by string (login)
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        log.info("Begin log in");
        String directory = request.getParameter(DIRECTORY);
        String moveDirectory = "/WEB-INF/" + directory + ".jsp";
        HttpSession session = request.getSession(false);
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        log.debug("Fields are filled");
        FactoryDao factoryDao = FactoryDao.getInstance();
        GenericDao genericDao = factoryDao.getDao(UserDao.class);
        factoryDao.beginTransaction();
        User user = null;
        try{
            log.debug("Query execution");
            user = (User) genericDao.findBy(login);
        } catch (ExceptionDao e) {
            log.error("Unable to execute query");
            factoryDao.rollback();
        }
        factoryDao.commit();
        log.info("Search completed");
        if (user.getPassword().equals(password)){
            session.setAttribute("user", user);
        }
        try {
            request.getRequestDispatcher(moveDirectory).forward(request, response);
        } catch (ServletException | IOException e) {
            log.error("Error when redirecting");
            throw new ExceptionAction(e);
        }
    }
}