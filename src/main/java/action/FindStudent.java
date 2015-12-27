package action;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import dao.factory.DaoFactory;
import dao.factory.GenericDao;
import dao.factory.StudentDao;
import entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by DiZi on 10.12.2015.
 */
public class FindStudent implements Strategy {

    /**
     *
     */
    private static final Logger log = LoggerFactory.getLogger(FindStudent.class);

    /**
     *
     */
    public static final String ID = "id";

    /**
     * Find student by id
     *
     * @param request
     * @param response
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter(ID));

        DaoFactory daoFactory = DaoFactory.getInstance();
        daoFactory.beginTransaction();
        GenericDao genericDao = daoFactory.getDao(StudentDao.class);
        Student student = (Student) genericDao.find(id);
        daoFactory.commit();

        request.setAttribute("entity", student);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
