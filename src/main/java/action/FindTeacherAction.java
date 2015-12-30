package action;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import dao.FactoryDao;
import dao.GenericDao;
import dao.LecturerDao;
import entity.Lecturer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by DiZi on 10.12.2015.
 */
public class FindTeacherAction implements Strategy {

    private static final Logger log = LoggerFactory.getLogger(FindTeacherAction.class);

    private static final String ID = "id";

    /**
     * Find teacher by id
     *
     * @param request
     * @param response
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter(ID));

        FactoryDao factoryDao = FactoryDao.getInstance();
        factoryDao.beginTransaction();
        GenericDao genericDao = factoryDao.getDao(LecturerDao.class);
        Lecturer lecturer = (Lecturer) genericDao.find(id);
        factoryDao.commit();

        request.setAttribute("entity",lecturer);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
