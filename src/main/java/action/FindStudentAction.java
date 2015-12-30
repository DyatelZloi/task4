package action;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import dao.FactoryDao;
import dao.GenericDao;
import dao.StudentDao;
import entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by DiZi on 10.12.2015.
 */
public class FindStudentAction implements Strategy {

    private static final Logger log = LoggerFactory.getLogger(FindStudentAction.class);

    private static final String ID = "id";

    /**
     * Find student by id
     *
     * @param request
     * @param response
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter(ID));

        FactoryDao factoryDao = FactoryDao.getInstance();
        factoryDao.beginTransaction();
        GenericDao genericDao = factoryDao.getDao(StudentDao.class);
        Student student = (Student) genericDao.find(id);
        factoryDao.commit();

        request.setAttribute("entity", student);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
