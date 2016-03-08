package action;

import dao.ExceptionDao;
import dao.FactoryDao;
import dao.GenericDao;
import dao.SheetListDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Malkov Nikifor on 05.01.2016.
 */
public class FindAllByCourseId implements Strategy{

    private static final Logger log = LoggerFactory.getLogger(FindByIdTeacherAction.class);

    private static final String ID_COURSE = "id-course";
    private static final String DIRECTORY = "/WEB-INF/registred-students.jsp";

    /**
     * Find all registered students
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.info("Begin search  a course");
        List list = null;
        int idCourse = Integer.parseInt(request.getParameter(ID_COURSE));
        log.debug("Fields are filled");
        FactoryDao factoryDao = FactoryDao.getInstance();
        GenericDao genericDao = factoryDao.getDao(SheetListDao.class);
        factoryDao.beginTransaction();
        try{
            log.debug("Query execution");
            list = genericDao.findAllBy(idCourse);
        } catch (ExceptionDao e) {
            log.error("Unable to execute query");
            factoryDao.rollback();
        }
        factoryDao.commit();
        log.info("Search completed");
        request.setAttribute("liststudents", list);
        return DIRECTORY;
    }

}