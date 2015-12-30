package action;



import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import dao.CourseDao;
import dao.FactoryDao;
import dao.GenericDao;
import entity.OptionalCourse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by DiZi on 10.12.2015.
 */
public class FindCourseAction implements Strategy {


    private static final Logger log = LoggerFactory.getLogger(FindCourseAction.class);

    private static final String ID = "id";

    /**
     * Find course by id
     *
     * @param request
     * @param response
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter(ID));

        FactoryDao factoryDao = FactoryDao.getInstance();
        factoryDao.beginTransaction();
        GenericDao genericDao = factoryDao.getDao(CourseDao.class);
        OptionalCourse optionalCourse = (OptionalCourse) genericDao.find(id);
        factoryDao.commit();

        request.setAttribute("createdcourses", optionalCourse);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/created-courses.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
