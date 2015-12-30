package action;

import dao.ExceptionDao;
import dao.FactoryDao;
import dao.GenericDao;
import dao.LecturerDao;
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
public class UpdateTeacherAction implements Strategy {

    private static final Logger log = LoggerFactory.getLogger(UpdateTeacherAction.class);

    private static final String NAME_PARAMETER_NAME = "name";
    private static final String LECTURER_SURNAME = "surname";
    private static final String ID = "id";

    //TODO переименовать класс

    /**
     * Update teacher by id
     *
     * @param request
     * @param response
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        log.info("");
        String name = request.getParameter(NAME_PARAMETER_NAME);
        String surname = request.getParameter(LECTURER_SURNAME);
        int id = Integer.parseInt(request.getParameter(ID));
        Lecturer lecturer = new Lecturer();
        lecturer.setName(name);
        lecturer.setSurname(surname);
        log.info("");
        FactoryDao factoryDao = FactoryDao.getInstance();
        factoryDao.beginTransaction();
        GenericDao genericDao = factoryDao.getDao(LecturerDao.class);
        try {
            log.info("");
            genericDao.update(lecturer, id);
        } catch (ExceptionDao e){
            log.error("");
            factoryDao.rollback();
        }
        factoryDao.commit();
        log.info("");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/update-lecturer.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
