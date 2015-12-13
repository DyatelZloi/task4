package action;

import dao.StudentDao;
import entity.Student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by DiZi on 02.12.2015.
 */
public class CreateStudentStrategy implements Strategy {

    private static final Logger log = LoggerFactory.getLogger(CreateStudentStrategy.class);

    public static final String NAME_PARAMETER_NAME = "name";

    public static  final String SURNAME_PARAMETER_NAME = "surname";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter(NAME_PARAMETER_NAME);
        String surname = request.getParameter(SURNAME_PARAMETER_NAME);
        Student student = new Student();
        student.setName(name);
        student.setSurname(surname);
        StudentDao studentDao = new StudentDao();
        studentDao.create(student);
        try {
            response.sendRedirect("/student-created.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
