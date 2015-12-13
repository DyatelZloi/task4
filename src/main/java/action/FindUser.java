package action;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import dao.factory.GenericDao;
import dao.factory.DaoFactory;
import dao.factory.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by DiZi on 10.12.2015.
 */
public class FindUser implements Strategy {

    private static final Logger log = LoggerFactory.getLogger(FindUser.class);

    public static final String ID = "id";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter(ID));
        DaoFactory daoFactory = DaoFactory.getInstance();
        GenericDao genericDao = daoFactory.getDao(UserDao.class);
        request.setAttribute("entity", genericDao.find(id));
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
        try {
            response.sendRedirect("/index.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}