package action;


import dao.factory.GenericDao;
import dao.factory.DaoFactory;
import dao.factory.LecturerDao;
import entity.Lecturer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by DiZi on 02.12.2015.
 */
public class CreateLecturerStrategy implements Strategy {

    private static final Logger log = LoggerFactory.getLogger(CreateLecturerStrategy.class);

    public static final String NAME_PARAMETER_NAME = "name";
    public static  final String SURNAME_PARAMETER_NAME = "surname";

    /**
     * Add lecturer
     *
     * @param request
     * @param response
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter(NAME_PARAMETER_NAME);
        String surname = request.getParameter(SURNAME_PARAMETER_NAME);
        Lecturer lecturer = new Lecturer();
        DaoFactory daoFactory = DaoFactory.getInstance();
        daoFactory.beginTransaction();
        GenericDao genericDao = daoFactory.getDao(LecturerDao.class);
        lecturer.setName(name);
        lecturer.setSurname(surname);
        genericDao.create(lecturer);
        daoFactory.commit();
        RequestDispatcher dispatcher = request.getRequestDispatcher("/lecturer-created.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
