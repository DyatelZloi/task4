package action;

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
 * Created by DiZi on 10.12.2015.
 */
public class UpdatePatricipiantListAction implements Strategy {

    private static final Logger log = LoggerFactory.getLogger(UpdatePatricipiantListAction.class);

    private static final String ID_COURSE = "id-course";
    private static final String ID_STUDENT = "id-student";
    private static final String SCORE = "score";
    private static final String SHORT_COMMENT = "short-comment";
    private static final String ID = "id";

    /**
     *
     * @param request
     * @param response
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        int idCourse = Integer.parseInt(request.getParameter(ID_COURSE));
        int idStudent = Integer.parseInt(request.getParameter(ID_STUDENT));
        int score = Integer.parseInt(request.getParameter(SCORE));
        String shortComment = request.getParameter(SHORT_COMMENT);
        int id = Integer.parseInt(request.getParameter(ID));
        ParticipantList participantList = new ParticipantList();
        participantList.setIdStudent(idStudent);
        participantList.setIdCourse(idCourse);
        participantList.setScore(score);
        participantList.setShortComment(shortComment);

        FactoryDao factoryDao = FactoryDao.getInstance();
        factoryDao.beginTransaction();
        GenericDao genericDao = factoryDao.getDao(ParticipantListDao.class);
        genericDao.update(participantList, id);
        factoryDao.commit();

        RequestDispatcher dispatcher = request.getRequestDispatcher("/update-list.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}