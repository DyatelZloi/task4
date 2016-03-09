package action;

import dao.ExceptionDao;
import dao.FactoryDao;
import dao.GenericDao;
import dao.SheetListDao;
import entity.SheetList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Malkov Nikifor on 02.12.2015.
 */
public class CreateSheetListAction implements Strategy {

    private static final Logger log = LoggerFactory.getLogger(CreateSheetListAction.class);

    private static final String STUDENT_ID = "id-student";
    private static final String COURSE_ID = "id-course";
    private static final String DIRECTORY = "/WEB-INF/index.jsp";

    /**
     * Create a new sheet list
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        log.info("Begin to create a new sheet registration");
        int idStudent = Integer.parseInt(request.getParameter(STUDENT_ID));
        int idCourse = Integer.parseInt(request.getParameter(COURSE_ID));
        SheetList sheetList = new SheetList();
        sheetList.setIdCourse(idCourse);
        sheetList.setIdStudent(idStudent);
        log.debug("Fields are filled");
        FactoryDao factoryDao = FactoryDao.getInstance();
        GenericDao genericDao = factoryDao.getDao(SheetListDao.class);
        factoryDao.beginTransaction();
        try{
            log.debug("Query execution");
            genericDao.create(sheetList);
        } catch (ExceptionDao e){
            log.error("Unable to execute query");
            factoryDao.rollback();
        }
        factoryDao.commit();
        log.info("Create sheer list completed");
        return DIRECTORY;
    }
}
