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

    private static final Logger log = LoggerFactory.getLogger(UpdateStudent.class);

    public static final String USER_LOGIN = "login";
    public static final String USER_PASSWORD = "password";
    public static final String ID = "id";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        dispatcher.forward(request, response);
        try {
            response.sendRedirect("/index.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}