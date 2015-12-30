package action;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import dao.ExceptionDao;
import dao.FactoryDao;
import dao.GenericDao;
import dao.LecturerDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by DiZi on 10.12.2015.
 */
public class DeleteTeacherAction implements Strategy {

    private static final Logger log = LoggerFactory.getLogger(DeleteTeacherAction.class);

    private static final String ID = "id";

    /**
     * Delete lecturer by id
     *
     * @param request
     * @param response
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        log.info("Подготовка к удалению учителя");
        int id = Integer.parseInt(request.getParameter(ID));
        FactoryDao factoryDao = FactoryDao.getInstance();
        GenericDao genericDao = factoryDao.getDao(LecturerDao.class);
        factoryDao.beginTransaction();
        try{
            log.info("Учитель удалён");
            genericDao.delete(id);
        } catch (ExceptionDao e){
            log.error("Не удалось удалить");
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