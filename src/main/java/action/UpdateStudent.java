package action;

import dao.factory.DaoFactory;
import dao.factory.GenericDao;
import dao.factory.StudentDao;
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

    /**
     *
     */
    private static final Logger log = LoggerFactory.getLogger(UpdateStudent.class);

    /**
     *
     */
    public static final String NAME_PARAMETER_NAME = "name";

    /**
     *
     */
    public static final String STUDENT_SURNAME = "surname";

    /**
     *
     */
    public static final String ID = "id";

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

        DaoFactory daoFactory = DaoFactory.getInstance();
        daoFactory.beginTransaction();
        GenericDao genericDao = daoFactory.getDao(StudentDao.class);
        genericDao.update(student, id);
        daoFactory.commit();

        RequestDispatcher dispatcher = request.getRequestDispatcher("/update-student.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}