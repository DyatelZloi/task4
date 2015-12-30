package action;

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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DiZi on 25.12.2015.
 */
public class FindAllUsersAction implements Strategy {

    private static final Logger log = LoggerFactory.getLogger(FindCourseAction.class);

    /**
     * Find all users
     *
     * @param request
     * @param response
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        List<User> list = new ArrayList<>();
        FactoryDao factoryDao = FactoryDao.getInstance();
        factoryDao.beginTransaction();
        GenericDao genericDao = factoryDao.getDao(UserDao.class);
        list = genericDao.findAll();
        factoryDao.commit();
        request.setAttribute("createdcourses", list);
        //TODO
        RequestDispatcher dispatcher = request.getRequestDispatcher("/created-courses.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}