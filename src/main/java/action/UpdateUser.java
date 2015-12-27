package action;

import dao.factory.DaoFactory;
import dao.factory.GenericDao;

import dao.factory.UserDao;
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
public class UpdateUser implements Strategy {

    /**
     *
     */
    private static final Logger log = LoggerFactory.getLogger(UpdateStudent.class);

    /**
     *
     */
    public static final String USER_LOGIN = "login";

    /**
     *
     */
    public static final String USER_PASSWORD = "password";

    /**
     *
     */
    public static final String ID = "id";

    /**
     *
     * @param request
     * @param response
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter(USER_LOGIN);
        String password = request.getParameter(USER_PASSWORD);
        int id = Integer.parseInt(request.getParameter(ID));
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setId(id);
        DaoFactory daoFactory = DaoFactory.getInstance();
        GenericDao genericDao = daoFactory.getDao(UserDao.class);
        genericDao.update(user, id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}