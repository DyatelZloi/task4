package action;

import dao.factory.DaoFactory;
import dao.factory.GenericDao;
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
 * Created by DiZi on 10.12.2015.
 */
public class UpdateLecturer implements Strategy {

    /**
     *
     */
    private static final Logger log = LoggerFactory.getLogger(UpdateLecturer.class);

    /**
     *
     */
    public static final String NAME_PARAMETER_NAME = "name";

    /**
     *
     */
    public static final String LECTURER_SURNAME = "surname";

    /**
     *
     */
    public static final String ID = "id";

    //TODO переименовать класс

    /**
     * Update teacher by id
     *
     * @param request
     * @param response
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter(NAME_PARAMETER_NAME);
        String surname = request.getParameter(LECTURER_SURNAME);
        int id = Integer.parseInt(request.getParameter(ID));
        Lecturer lecturer = new Lecturer();
        lecturer.setName(name);
        lecturer.setSurname(surname);

        DaoFactory daoFactory = DaoFactory.getInstance();
        daoFactory.beginTransaction();
        GenericDao genericDao = daoFactory.getDao(LecturerDao.class);
        genericDao.update(lecturer, id);
        daoFactory.commit();

        RequestDispatcher dispatcher = request.getRequestDispatcher("/update-lecturer.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
