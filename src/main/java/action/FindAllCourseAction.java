package action;


import dao.CourseDao;
import dao.FactoryDao;
import dao.GenericDao;
import entity.OptionalCourse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by DiZi on 21.12.2015.
 */
public class FindAllCourseAction implements Strategy {

    /**
     *
     */
    private static final Logger log = LoggerFactory.getLogger(FindCourseAction.class);

    //TODO переименовать

    /**
     * Find all courses
     *
     * @param request
     * @param response
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        List<OptionalCourse> list;
        FactoryDao factoryDao = FactoryDao.getInstance();
        factoryDao.beginTransaction();
        GenericDao genericDao = factoryDao.getDao(CourseDao.class);
        list = genericDao.findAll();
        factoryDao.commit();
        request.setAttribute("en", list);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/find-all-course-g.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
