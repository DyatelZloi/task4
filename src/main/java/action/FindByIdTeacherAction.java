package action;

import dao.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Malkov Nikifor on 05.01.2016.
 */
public class FindByIdTeacherAction implements Strategy{

    private static final Logger log = LoggerFactory.getLogger(FindByIdTeacherAction.class);

    private static final String ID_TEACHER = "id-teacher";
    private static final String DIRECTORY = "/WEB-INF/created-courses.jsp";

    /**
     * Find all registered students
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.info("Begin search teacher by id");
        List list = null;
        int idTeacher = Integer.parseInt(request.getParameter(ID_TEACHER));
        log.debug("Fields are filled");
        FactoryDao factoryDao = FactoryDao.getInstance();
        GenericDao genericDao = factoryDao.getDao(CourseDao.class);
        factoryDao.beginTransaction();
        try{
            log.debug("Query execution");
            list = genericDao.findAllBy(idTeacher);
        } catch (ExceptionDao e) {
            log.error("Unable to execute query");
            factoryDao.rollback();
        }
        factoryDao.commit();
        log.info("Search teacher by id completed");
        request.setAttribute("listcourse", list);
        return DIRECTORY;
    }
}