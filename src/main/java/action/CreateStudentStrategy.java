package action;


import dao.factory.DaoFactory;
import dao.factory.GenericDao;
import dao.factory.StudentDao;
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

    /**
     *
     */
    private static final Logger log = LoggerFactory.getLogger(CreateStudentStrategy.class);

    /**
     *
     */
    public static final String NAME_PARAMETER_NAME = "name";

    /**
     *
     */
    public static  final String SURNAME_PARAMETER_NAME = "surname";

    /**
     * Add student
     *
     * @param request
     * @param response
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        //TODO
        String name = request.getParameter(NAME_PARAMETER_NAME);
        String surname = request.getParameter(SURNAME_PARAMETER_NAME);
        Student student = new Student();
        student.setName(name);
        student.setSurname(surname);

        DaoFactory daoFactory = DaoFactory.getInstance();
        daoFactory.beginTransaction();
        GenericDao genericDao = daoFactory.getDao(StudentDao.class);
        genericDao.create(student);
        daoFactory.commit();

        try {
            response.sendRedirect("/student-created.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
