package action;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import dao.ExceptionDao;
import dao.CourseDao;
import dao.FactoryDao;
import dao.GenericDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by DiZi on 10.12.2015.
 */
public class DeleteCourseAction implements Strategy {

    private static final Logger log = LoggerFactory.getLogger(DeleteCourseAction.class);

    private static final String ID = "id";

    /**
     * Delete course by id
     *
     * @param request
     * @param response
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response){
        log.info("Начинаем удаление курса");
        int id = Integer.parseInt(request.getParameter(ID));
        log.info("Устанавили параметры");
        FactoryDao factoryDao = FactoryDao.getInstance();
        GenericDao genericDao = factoryDao.getDao(CourseDao.class);
        factoryDao.beginTransaction();
        try{
            log.info("Выполнение запроса");
            genericDao.delete(id);
        } catch (ExceptionDao e) {
            log.error("Запрос неудачен");
            factoryDao.rollback();
        }
        factoryDao.commit();
        log.info("Удаление завершено");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
