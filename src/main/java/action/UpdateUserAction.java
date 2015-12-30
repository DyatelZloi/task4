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
import java.io.IOException;

/**
 * Created by DiZi on 10.12.2015.
 */
public class UpdateUserAction implements Strategy {

    private static final Logger log = LoggerFactory.getLogger(UpdateStudentAction.class);

    private static final String USER_LOGIN = "login";
    private static final String USER_PASSWORD = "password";
    private static final String ID = "id";

    /**
     *
     * @param request
     * @param response
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        log.info("");
        String login = request.getParameter(USER_LOGIN);
        String password = request.getParameter(USER_PASSWORD);
        int id = Integer.parseInt(request.getParameter(ID));
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setId(id);
        log.info("");
        FactoryDao factoryDao = FactoryDao.getInstance();
        GenericDao genericDao = factoryDao.getDao(UserDao.class);
        try {
            log.info("");
            genericDao.update(user, id);
        } catch (ExceptionDao e) {
            log.error("");
            factoryDao.rollback();
        }
        factoryDao.commit();
        log.info("");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}