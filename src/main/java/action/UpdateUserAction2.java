package action;

import dao.ExceptionDao;
import dao.FactoryDao;
import dao.GenericDao;
import dao.UserDao;
import entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Malkov Nikifor on 06.01.2016.
 */
public class UpdateUserAction2 implements Strategy {

    private static final Logger log = LoggerFactory.getLogger(UpdateUserAction2.class);

    private static final String USER_LOGIN = "login";
    private static final String USER_PASSWORD = "password";
    private static final String ID = "id";
    public static final String USER_EMAIL = "email";
    public static final String USER_NAME = "name";
    public static final String USER_SURNAME = "surname";
    private static final String DIRECTORY = "directory";

    /**
     * Update a user by id
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        log.info("Begin update a user");
        String directory = request.getParameter(DIRECTORY);
        String moveDirectory = "/WEB-INF/" + directory + ".jsp";
        int id = Integer.parseInt(request.getParameter(ID));
        String login = request.getParameter(USER_LOGIN);
        String password = request.getParameter(USER_PASSWORD);
        String email = request.getParameter(USER_EMAIL);
        String name = request.getParameter(USER_NAME);
        String surname = request.getParameter(USER_SURNAME);
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setEmail(email);
        user.setName(name);
        user.setSurname(surname);
        user.setId(id);
        log.debug("Fields are filled");
        FactoryDao factoryDao = FactoryDao.getInstance();
        GenericDao genericDao = factoryDao.getDao(UserDao.class);
        factoryDao.beginTransaction();
        try {
            log.debug("Query execution");
            genericDao.update2(user, id);
            HttpSession session = request.getSession(false);
            session.setAttribute("user", user);
        } catch (ExceptionDao e) {
            log.error("Error executing query");
            factoryDao.rollback();
        }
        factoryDao.commit();
        log.info("User update completed");
        try {
            request.getRequestDispatcher(moveDirectory).forward(request, response);
        } catch (ServletException | IOException e) {
            log.error("Error when redirecting");
            throw new ExceptionAction(e);
        }
    }
}