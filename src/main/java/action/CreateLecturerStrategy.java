package action;


import dao.factory.GenericDao;
import dao.factory.DaoFactory;
import dao.factory.LecturerDao;
import entity.Lecturer;

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

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter(NAME_PARAMETER_NAME);
        String surname = request.getParameter(SURNAME_PARAMETER_NAME);
        Lecturer lecturer = new Lecturer();
        DaoFactory daoFactory = DaoFactory.getInstance();
        GenericDao genericDao = daoFactory.getDao(LecturerDao.class);
        lecturer.setName(name);
        lecturer.setSurname(surname);
        genericDao.create(lecturer);
        try {
            response.sendRedirect("/lecturer-created.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
