package action;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import dao.factory.Dao;
import dao.factory.DaoFactory;
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
        Dao dao = daoFactory.getDao(dao.factory.UserDao.class);
        request.setAttribute("entity",dao.find(id));
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
        try {
            response.sendRedirect("/index.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}