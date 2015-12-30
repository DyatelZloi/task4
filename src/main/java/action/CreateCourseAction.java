package action;



import dao.ExceptionDao;
import dao.CourseDao;
import dao.GenericDao;
import dao.FactoryDao;
import entity.OptionalCourse;

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
public class CreateCourseAction implements Strategy {

    private static final Logger log = LoggerFactory.getLogger(CreateCourseAction.class);

    private static final String NAME_PARAMETER_NAME = "name";
    private static final String COURSE_DESCRIPTION_PARAMETER_NAME = "course-description";

    /**
     * Add course
     *
     * @param request
     * @param response
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        log.info("Начинаем создавать новый курс");
        String name = request.getParameter(NAME_PARAMETER_NAME);
        String courseDescription = request.getParameter(COURSE_DESCRIPTION_PARAMETER_NAME);
        FactoryEntity factoryEntity = FactoryEntity.getInstance();
        OptionalCourse course = (OptionalCourse) factoryEntity.getEntity(OptionalCourse.class);
        course.setName(name);
        course.setCourseDescription(courseDescription);
        log.info("Поля курса заполнены");
        FactoryDao factoryDao = FactoryDao.getInstance();
        GenericDao genericDao = factoryDao.getDao(CourseDao.class);
        factoryDao.beginTransaction();
        try{
            log.info("Новый курс добавлен в таблицу");
            genericDao.create(course);
        } catch (ExceptionDao e){
            log.error("Невозможно создать курс");
            factoryDao.rollback();
        }
        factoryDao.commit();
        log.info("Создание курса завершено");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}