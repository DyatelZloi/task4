package action;

import dao.CourseDao;
import dao.LecturerDao;
import entity.Lecturer;
import entity.OptionalCourse;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by DiZi on 10.12.2015.
 */
public class UpdateLecturer implements Strategy {

    public static final String NAME_PARAMETER_NAME = "name";
    public static final String LECTURER_SURNAME = "surname";
    public static final String ID = "id";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter(NAME_PARAMETER_NAME);
        String surname = request.getParameter(LECTURER_SURNAME);
        int id = Integer.parseInt(request.getParameter(ID));
        Lecturer lecturer = new Lecturer();
        lecturer.setName(name);
        lecturer.setSurname(surname);
        LecturerDao lecturerDao = new LecturerDao();
        lecturerDao.update(lecturer, id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/update-lecturer.jsp");
        dispatcher.forward(request, response);
        try {
            response.sendRedirect("/update-lecturer.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
