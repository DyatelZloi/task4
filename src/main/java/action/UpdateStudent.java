package action;

import dao.StudentDao;
import entity.Student;

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
public class UpdateStudent implements Strategy {

    private static final Logger log = LoggerFactory.getLogger(UpdateStudent.class);

    public static final String NAME_PARAMETER_NAME = "name";
    public static final String STUDENT_SURNAME = "surname";
    public static final String ID = "id";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter(NAME_PARAMETER_NAME);
        String surname = request.getParameter(STUDENT_SURNAME);
        int id = Integer.parseInt(request.getParameter(ID));
        Student student = new Student();
        student.setName(name);
        student.setSurname(surname);
        StudentDao studentDao = new StudentDao();
        studentDao.update(student, id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/update-student.jsp");
        dispatcher.forward(request, response);
        try {
            response.sendRedirect("/update-student.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}