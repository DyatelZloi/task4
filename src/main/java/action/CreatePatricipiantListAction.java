package action;

import dao.ExceptionDao;
import dao.FactoryDao;
import dao.GenericDao;
import dao.ParticipantListDao;
import entity.ParticipantList;

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
public class CreatePatricipiantListAction implements Strategy {

    private static final Logger log = LoggerFactory.getLogger(CreatePatricipiantListAction.class);

    private static final String STUDENT_ID = "id-student";
    private static final String COURSE_ID = "id-course";
    private static final String SCORE = "score";
    private static final String SHORT_COMMENT = "short-comment";

    /**
     *
     * @param request
     * @param response
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        log.info("");
        String idStudent = request.getParameter(STUDENT_ID);
        String idCourse = request.getParameter(COURSE_ID);
        String score = request.getParameter(SCORE);
        String shortComment = request.getParameter(SHORT_COMMENT);
        ParticipantList participantList = new ParticipantList();
        participantList.setIdCourse(idCourse);
        participantList.setIdStudent(idStudent);
        participantList.setScore(score);
        participantList.setShortComment(shortComment);
        log.info("");
        FactoryDao factoryDao = FactoryDao.getInstance();
        GenericDao genericDao = factoryDao.getDao(ParticipantListDao.class);
        factoryDao.beginTransaction();
        try{
            log.info("");
            genericDao.create(participantList);
        } catch (ExceptionDao e) {
            log.error("");
            factoryDao.rollback();
        }
        factoryDao.commit();
        log.info("");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/list-created.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
