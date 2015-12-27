package action;

import dao.factory.DaoFactory;
import dao.factory.GenericDao;
import dao.factory.ParticipantListDao;
import entity.ParticipantList;
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
public class FindAllPatricipianListAction implements Strategy {

    /**
     *
     */
    private static final Logger log = LoggerFactory.getLogger(FindCourse.class);

    /**
     * Find all registered students
     *
     * @param request
     * @param response
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        List<ParticipantList> list = new ArrayList<>();
        DaoFactory daoFactory = DaoFactory.getInstance();
        daoFactory.beginTransaction();
        GenericDao genericDao = daoFactory.getDao(ParticipantListDao.class);
        list = genericDao.findAll();
        daoFactory.commit();
        request.setAttribute("createdcourses", list);
        //TODO переименовать
        RequestDispatcher dispatcher = request.getRequestDispatcher("/created-courses.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}