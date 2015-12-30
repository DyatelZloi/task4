package action;

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
 * Created by DiZi on 17.12.2015.
 */
public class FindByStringAction implements Strategy{

    private static final Logger log = LoggerFactory.getLogger(FindByStringAction.class);

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";

    /**
     * Find user by string (login)
     *
     * @param request
     * @param response
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        FactoryDao factoryDao = FactoryDao.getInstance();
        factoryDao.beginTransaction();
        GenericDao genericDao = factoryDao.getDao(UserDao.class);
        User user = (User) genericDao.findBy(login);
        factoryDao.commit();
        if (user.getPassword().equals(password)){
            request.setAttribute("entity", user);
            session.setAttribute("login", user.getLogin());
            session.setAttribute("role", user.getRole());
        }
        try {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}