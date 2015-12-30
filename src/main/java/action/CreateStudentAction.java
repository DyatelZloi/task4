package action;


import dao.ExceptionDao;
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
 * Created by DiZi on 02.12.2015.
 */
public class CreateStudentAction implements Strategy {

    private static final Logger log = LoggerFactory.getLogger(CreateStudentAction.class);

    private static final String NAME_PARAMETER_NAME = "name";
    private static  final String SURNAME_PARAMETER_NAME = "surname";

    /**
     * Add student
     *
     * @param request
     * @param response
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        //TODO
        log.info("начинаем создание нового студента");
        String name = request.getParameter(NAME_PARAMETER_NAME);
        String surname = request.getParameter(SURNAME_PARAMETER_NAME);
        Student student = new Student();
        student.setName(name);
        student.setSurname(surname);
        log.info("Присвоили поля студенту");
        FactoryDao factoryDao = FactoryDao.getInstance();
        factoryDao.beginTransaction();
        GenericDao genericDao = factoryDao.getDao(StudentDao.class);
        try{
            log.info("Создали нового студента");
            genericDao.create(student);
        } catch (ExceptionDao e){
            log.error("Ошибка при создании студента");
            factoryDao.rollback();
        }
        factoryDao.commit();
        log.info("Создание нового студента завершено");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
