package action;


import dao.ExceptionDao;
import dao.GenericDao;
import dao.FactoryDao;
import dao.LecturerDao;
import entity.Lecturer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import entity.FactoryEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by DiZi on 02.12.2015.
 */
public class CreateTeacherAction implements Strategy {

    private static final Logger log = LoggerFactory.getLogger(CreateTeacherAction.class);

    private static final String NAME_PARAMETER_NAME = "name";
    private static  final String SURNAME_PARAMETER_NAME = "surname";

    /**
     * Add lecturer
     *
     * @param request
     * @param response
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        log.info("Начинаем создавать преподавателя");
        String name = request.getParameter(NAME_PARAMETER_NAME);
        String surname = request.getParameter(SURNAME_PARAMETER_NAME);
        FactoryEntity factoryEntity = FactoryEntity.getInstance();
        Lecturer lecturer = (Lecturer) factoryEntity.getEntity(Lecturer.class);
        lecturer.setName(name);
        lecturer.setSurname(surname);
        FactoryDao factoryDao = FactoryDao.getInstance();
        factoryDao.beginTransaction();
        log.info("Присвоили поля");
        GenericDao genericDao = factoryDao.getDao(LecturerDao.class);
        factoryDao.beginTransaction();
        try{
            log.info("Новый преподователь добавлен в таблицу");
            genericDao.create(lecturer);
        } catch (ExceptionDao e) {
            log.error("Ошибка при создании преподователя");
            factoryDao.rollback();
        }
        factoryDao.commit();
        log.info("Создание нового преподователя завешено");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/lecturer-created.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
