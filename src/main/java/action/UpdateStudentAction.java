package action;

import dao.FactoryDao;
import dao.GenericDao;
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
public class UpdateStudentAction implements Strategy {

    private static final Logger log = LoggerFactory.getLogger(UpdateStudentAction.class);

    private static final String NAME_PARAMETER_NAME = "name";
    private static final String STUDENT_SURNAME = "surname";
    private static final String ID = "id";

    /**
     *
     * @param request
     * @param response
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter(NAME_PARAMETER_NAME);
        String surname = request.getParameter(STUDENT_SURNAME);
        int id = Integer.parseInt(request.getParameter(ID));
        Student student = new Student();
        student.setName(name);
        student.setSurname(surname);

        FactoryDao factoryDao = FactoryDao.getInstance();
        factoryDao.beginTransaction();
        GenericDao genericDao = factoryDao.getDao(StudentDao.class);
        genericDao.update(student, id);
        factoryDao.commit();

        RequestDispatcher dispatcher = request.getRequestDispatcher("/update-student.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}