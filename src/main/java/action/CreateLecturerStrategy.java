package action;

import dao.LecturerDao;
import entity.Lecturer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by DiZi on 02.12.2015.
 */
public class CreateLecturerStrategy implements Strategy {

    public static final String NAME_PARAMETER_NAME = "name";
    public static  final String SURNAME_PARAMETER_NAME = "surname";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter(NAME_PARAMETER_NAME);
        String surname = request.getParameter(SURNAME_PARAMETER_NAME);
        Lecturer lecturer = new Lecturer();
        lecturer.setName(name);
        lecturer.setSurname(surname);
        LecturerDao lecturerDao = new LecturerDao();
        lecturerDao.create(lecturer);
        try {
            response.sendRedirect("/lecturer-created.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
